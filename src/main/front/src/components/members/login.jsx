// src/components/Login.js
import React, { useState } from "react";
import { loginMember } from "../../api/fetchMember";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { memberState } from "../../recoil/atom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, Form, FormGroup, Label, Input, Button, Alert } from "reactstrap";

const Login = () => {
  const [loginDTO, setLoginDTO] = useState({ nickname: "" });
  const navigate = useNavigate();
  const [loggedInMember, setLoggedInMember] = useRecoilState(memberState); // Recoil 상태 사용
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoginDTO({ ...loginDTO, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const loggedInMember = await loginMember(loginDTO);
      console.log("Logged in member:", loggedInMember);
      setLoggedInMember(loggedInMember); // Recoil 상태 업데이트
      console.log("recoil", loggedInMember);
      navigate(`/member/view/${loggedInMember.id}`);
    } catch (error) {
      setError("Login failed. Please check your credentials.");
    }
  };

  const goToMainPage = () => {
    navigate("/");
  };

  return (
    <Container className='mt-5'>
      <Row className='justify-content-center'>
        <Col md='6'>
          <h2 className='text-center mb-4'>로그인</h2>
          <Form onSubmit={handleSubmit}>
            <FormGroup>
              <Label for='nickname'>닉네임</Label>
              <Input
                type='text'
                name='nickname'
                id='nickname'
                value={loginDTO.nickname}
                onChange={handleChange}
                required
              />
            </FormGroup>
            <Button color='primary' type='submit' block>
              로그인
            </Button>
            <br />
            <Button color='secondary' onClick={goToMainPage} block>
              메인 화면으로
            </Button>
          </Form>
          {error && (
            <Alert color='danger' className='mt-3'>
              {error}
            </Alert>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default Login;
