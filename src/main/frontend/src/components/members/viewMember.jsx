import React, { useState, useEffect } from "react";
import { viewMember, updateMember } from "../../api/fetchMember";
import { Navigate, useNavigate, useParams } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, Form, FormGroup, Label, Input, Button, Alert } from "reactstrap";

const ViewMember = () => {
  const { id } = useParams(); // URL에서 회원 ID 파라미터 추출
  const [member, setMember] = useState(null); // 회원 정보 상태
  const [error, setError] = useState(null); // 에러 상태
  const [editing, setEditing] = useState(false); // 수정 모드 상태
  const navigate = useNavigate();
  const [updateDTO, setUpdateDTO] = useState({
    nickname: "",
    gender: "",
    height: "",
    weight: "",
    age: "",
  }); // 수정할 정보 상태

  useEffect(() => {
    const fetchMemberData = async () => {
      try {
        const memberData = await viewMember(id); // API 호출하여 회원 정보 가져오기
        console.log(memberData);
        setMember(memberData); // 회원 정보 설정
        setUpdateDTO({
          nickname: memberData.nickname || "",
          gender: memberData.gender || "",
          height: memberData.height || "",
          weight: memberData.weight || "",
          age: memberData.age || "",
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
    if (member) {
      setEditing(true);
    } else {
      setError("Cannot edit. Member data is not loaded yet.");
    }
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
      nickname: member.nickname || "",
      gender: member.gender || "",
      height: member.height || "",
      weight: member.weight || "",
      age: member.age || "",
    });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUpdateDTO({ ...updateDTO, [name]: value });
    setError("");
  };

  const goToMainPage = () => {
    navigate("/"); // 메인 페이지로 이동
  };

  if (error) return <Alert color='danger'>{error}</Alert>;
  if (!member) return <p>Loading...</p>;

  return (
    <Container className='form-container mt-5'>
      <Row className='justify-content-center'>
        <Col md='12'>
          <h2 className='text-center mb-4'>회원 정보</h2>
          {editing ? (
            <Form>
              <FormGroup>
                <Label for='nickname'>닉네임:</Label>
                <Input type='text' name='nickname' id='nickname' value={updateDTO.nickname} onChange={handleChange} />
                {error?.nickname && <p className='error-message'>{error.nickname}</p>}
              </FormGroup>
              <FormGroup>
                <Label for='gender'>성별:</Label>
                <div>
                  <Input
                    type='text'
                    name='gender'
                    id='gender'
                    value={updateDTO.gender}
                    onChange={handleChange}
                    readOnly
                  />
                  {error?.gender && <p className='error-message'>{error.gender}</p>}
                </div>
              </FormGroup>
              <FormGroup>
                <Label for='height'>키:</Label>
                <Input type='text' name='height' id='height' value={updateDTO.height} onChange={handleChange} />
                {error?.height && <p className='error-message'>{error.height}</p>}
              </FormGroup>
              <FormGroup>
                <Label for='weight'>몸무게:</Label>
                <Input type='text' name='weight' id='weight' value={updateDTO.weight} onChange={handleChange} />
                {error?.weight && <p className='error-message'>{error.weight}</p>}
              </FormGroup>
              <FormGroup>
                <Label for='age'>나이:</Label>
                <Input type='text' name='age' id='age' value={updateDTO.age} onChange={handleChange} />
                {error?.age && <p className='error-message'>{error.age}</p>}
              </FormGroup>
              <Button color='primary' onClick={handleSaveClick} block>
                저장
              </Button>
              <br />
              <Button color='secondary' onClick={handleCancelClick} block>
                취소
              </Button>
            </Form>
          ) : (
            <div>
              <p>닉네임: {member.nickname}</p>
              <p>성별: {member.gender}</p>
              <p>키: {member.height}cm</p>
              <p>몸무게: {member.weight}kg</p>
              <p>나이: {member.age}</p>
              {member.kcal && (
                <>
                  <p>다이어트 칼로리: {member.kcal.dietKcal}Kcal</p>
                  <p>유지 칼로리: {member.kcal.maintainKcal}Kcal</p>
                  <p>린매스업 칼로리: {member.kcal.massUpKcal}Kcal</p>
                  <p>벌크업 칼로리: {member.kcal.bulkUpKcal}Kcal</p>
                </>
              )}
              <Button color='primary' onClick={handleEditClick} block>
                수정
              </Button>
              <br />
              <Button color='secondary' onClick={goToMainPage} block>
                메인 화면으로
              </Button>
            </div>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default ViewMember;
