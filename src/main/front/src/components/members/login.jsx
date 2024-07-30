import React, { useState } from "react";
import { loginMember } from "../../api/fetchMember";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, Form, FormGroup, Label, Input, Button, Alert } from "reactstrap";

const Login = () => {
  const [loginDTO, setLoginDTO] = useState({ nickname: "" });
  const navigate = useNavigate();
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoginDTO({ ...loginDTO, [name]: value }); // name 속성을 이용하여 동적으로 상태 업데이트
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const loggedInMember = await loginMember(loginDTO);
      console.log("Logged in member:", loggedInMember);
      navigate(`/member/view/${loggedInMember.id}`);
    } catch (error) {
      setError("Login failed. Please check your credentials.");
    }
  };

  const goToMainPage = () => {
    navigate("/"); // 메인 페이지로 이동
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
