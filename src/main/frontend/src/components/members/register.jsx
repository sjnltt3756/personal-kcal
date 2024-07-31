import React, { useState } from "react";
import { registerMember } from "../../api/fetchMember";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, Form, FormGroup, Label, Input, Button, Alert } from "reactstrap";
import "../../css/register.css";

const Register = () => {
  const [registerDTO, setRegisterDTO] = useState({
    nickname: "",
    gender: "",
    height: "",
    weight: "",
    age: "",
  });
  const [error, setError] = useState({});
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setRegisterDTO({ ...registerDTO, [name]: value });
    setError({ ...error, [name]: "" });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newErrors = {};

    Object.keys(registerDTO).forEach((key) => {
      if (!registerDTO[key]) {
        newErrors[key] = `${key} 입력해주세요.`;
      }
    });

    if (Object.keys(newErrors).length > 0) {
      setError(newErrors);
      return;
    }

    try {
      const registeredMember = await registerMember(registerDTO);
      console.log("Registered member:", registeredMember);
      setSuccess(true);
      navigate("/member/login");
    } catch (err) {
      setError({ form: "Registration failed. Please try again." });
    }
  };

  const goToMainPage = () => {
    navigate("/"); // 메인 페이지로 이동
  };
  return (
    <Container className='form-container mt-5'>
      <Row className='justify-content-center'>
        <Col md='12'>
          <h2 className='text-center mb-4'>회원가입</h2>
          <Form onSubmit={handleSubmit}>
            <FormGroup>
              <Label for='nickname'>닉네임:</Label>
              <Input
                type='text'
                name='nickname'
                id='nickname'
                value={registerDTO.nickname}
                onChange={handleChange}
                required
              />
              {error.nickname && <p className='error-message'>{error.nickname}</p>}
            </FormGroup>
            <FormGroup>
              <Label>성별:</Label>
              <div>
                <FormGroup check inline>
                  <Label check>
                    <Input
                      type='radio'
                      name='gender'
                      value='남성'
                      checked={registerDTO.gender === "남성"}
                      onChange={handleChange}
                    />{" "}
                    남성
                  </Label>
                </FormGroup>
                <FormGroup check inline>
                  <Label check>
                    <Input
                      type='radio'
                      name='gender'
                      value='여성'
                      checked={registerDTO.gender === "여성"}
                      onChange={handleChange}
                    />{" "}
                    여성
                  </Label>
                </FormGroup>
              </div>
              {error.gender && <p className='error-message'>{error.gender}</p>}
            </FormGroup>
            <FormGroup>
              <Label for='height'>키:</Label>
              <Input
                type='text'
                name='height'
                id='height'
                value={registerDTO.height}
                onChange={handleChange}
                required
              />
              {error.height && <p className='error-message'>{error.height}</p>}
            </FormGroup>
            <FormGroup>
              <Label for='weight'>몸무게:</Label>
              <Input
                type='text'
                name='weight'
                id='weight'
                value={registerDTO.weight}
                onChange={handleChange}
                required
              />
              {error.weight && <p className='error-message'>{error.weight}</p>}
            </FormGroup>
            <FormGroup>
              <Label for='age'>나이:</Label>
              <Input type='text' name='age' id='age' value={registerDTO.age} onChange={handleChange} required />
              {error.age && <p className='error-message'>{error.age}</p>}
            </FormGroup>
            <Button color='primary' type='submit' block>
              회원가입
            </Button>
            <br />
            <Button color='secondary' onClick={goToMainPage} block>
              메인 화면으로
            </Button>
          </Form>
          {error.form && (
            <Alert color='danger' className='mt-3'>
              {error.form}
            </Alert>
          )}
          {success && (
            <Alert color='success' className='mt-3'>
              회원가입 성공!
            </Alert>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default Register;
