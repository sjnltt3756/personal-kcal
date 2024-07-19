import React, { useState, useEffect } from "react";
import { viewMember, updateMember } from "../api/fetchMember";
import { useParams } from "react-router-dom";

const ViewMember = () => {
  const { id } = useParams(); // URL에서 회원 ID 파라미터 추출
  const [member, setMember] = useState(null); // 회원 정보 상태
  const [error, setError] = useState(null); // 에러 상태
  const [editing, setEditing] = useState(false); // 수정 모드 상태
  const [updateDTO, setUpdateDTO] = useState({
    nickname: "",
    height: "",
    weight: "",
    age: "",
  }); // 수정할 정보 상태

  useEffect(() => {
    const fetchMemberData = async () => {
      try {
        const memberData = await viewMember(id); // API 호출하여 회원 정보 가져오기
        setMember(memberData); // 회원 정보 설정
        setUpdateDTO({
          nickname: memberData.nickname,
          height: memberData.height,
          weight: memberData.weight,
          age: memberData.age,
        });
      } catch (error) {
        setError("Error fetching member data."); // 에러 발생 시 처리
      }
    };

    if (id) {
      fetchMemberData(); // 회원 ID가 변경될 때마다 실행
    }
  }, [id]);

  const handleEditClick = () => {
    setEditing(true);
  };

  const handleSaveClick = async () => {
    try {
      const updatedMember = await updateMember(id, updateDTO);
      setMember(updatedMember);
      setEditing(false);
      alert("회원 정보가 수정되었습니다.");
    } catch (error) {
      setError("회원 정보 수정에 실패했습니다.");
    }
  };

  const handleCancelClick = () => {
    setEditing(false);
    // 수정 모드 취소 시 원래의 데이터를 되돌림
    setUpdateDTO({
      nickname: member.nickname,
      height: member.height,
      weight: member.weight,
      age: member.age,
    });
  };

  if (error) return <p>{error}</p>;
  if (!member) return <p>Loading...</p>;

  return (
    <div>
      <h2>회원 정보</h2>
      {editing ? (
        <div>
          <label>닉네임:</label>
          <input
            type='text'
            value={updateDTO.nickname}
            onChange={(e) => setUpdateDTO({ ...updateDTO, nickname: e.target.value })}
          />
          <br />
          <label>키:</label>
          <input
            type='text'
            value={updateDTO.height}
            onChange={(e) => setUpdateDTO({ ...updateDTO, height: e.target.value })}
          />
          <br />
          <label>몸무게:</label>
          <input
            type='text'
            value={updateDTO.weight}
            onChange={(e) => setUpdateDTO({ ...updateDTO, weight: e.target.value })}
          />
          <br />
          <label>나이:</label>
          <input
            type='text'
            value={updateDTO.age}
            onChange={(e) => setUpdateDTO({ ...updateDTO, age: e.target.value })}
          />
          <br />
          <br />
          <button onClick={handleSaveClick}>저장</button>
          <button onClick={handleCancelClick}>취소</button>
        </div>
      ) : (
        <div>
          <p>닉네임: {member.nickname}</p>
          <p>성별: {member.gender}</p>
          <p>키: {member.height}cm</p>
          <p>몸무게: {member.weight}kg</p>
          <p>나이: {member.age}</p>
          {member.kcalDto && (
            <>
              <p>다이어트 칼로리: {member.kcalDto.dietKcal}Kcal</p>
              <p>유지 칼로리: {member.kcalDto.maintainKcal}Kcal</p>
              <p>린매스업 칼로리: {member.kcalDto.massUpKcal}Kcal</p>
              <p>벌크업 칼로리: {member.kcalDto.bulkUpKcal}Kcal</p>
            </>
          )}
          <button onClick={handleEditClick}>수정</button>
        </div>
      )}
    </div>
  );
};

export default ViewMember;
