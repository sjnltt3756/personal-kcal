import React, { useState, useEffect } from "react";
import { viewMember } from "../api/fetchMember";
import { useParams } from "react-router-dom";

const ViewMember = ({ memberId }) => {
  const { id } = useParams(); // URL에서 회원 ID 파라미터 추출

  const [member, setMember] = useState(null); // 회원 정보 상태
  const [error, setError] = useState(null); // 에러 상태

  useEffect(() => {
    const fetchMemberData = async () => {
      try {
        const memberData = await viewMember(id); // API 호출하여 회원 정보 가져오기
        setMember(memberData); // 회원 정보 설정
      } catch (error) {
        setError("Error fetching member data."); // 에러 발생 시 처리
      }
    };

    if (id) {
      fetchMemberData(); // 회원 ID가 변경될 때마다 실행
    }
  }, [id]);

  if (error) return <p>{error}</p>;
  if (!member) return <p>Loading...</p>;

  return (
    <div>
      <h2>Member Details</h2>
      <p>닉네임: {member.nickname}</p>
      <p>성별: {member.gender}</p>
      <p>키: {member.height}cm</p>
      <p>몸무게: {member.weight}kg</p>
      <p>나이: {member.age}</p>
      <p>다이어트 칼로리: {member.kcalDto.dietKcal}Kcal</p>
      <p>유지 칼로리: {member.kcalDto.maintainKcal}Kcal</p>
      <p>린매스업 칼로리: {member.kcalDto.massUpKcal}Kcal</p>
      <p>벌크업 칼로리: {member.kcalDto.bulkUpKcal}Kcal</p>
    </div>
  );
};

export default ViewMember;
