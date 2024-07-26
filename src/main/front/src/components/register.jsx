import React, { useState } from "react";
import { registerMember } from "../api/fetchMember";
import { useNavigate } from "react-router-dom";

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

  return (
    <div>
      <h2>회원가입</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>닉네임 : </label>
          <input type='text' name='nickname' value={registerDTO.nickname} onChange={handleChange} />
          {error.nickname && <p style={{ color: "red" }}>{error.nickname}</p>}
        </div>
        <br />
        <div>
          <label>성별 : </label>
          <input
            type='radio'
            name='gender'
            value='남성'
            checked={registerDTO.gender === "남성"}
            onChange={handleChange}
          />{" "}
          남성
          <input
            type='radio'
            name='gender'
            value='여성'
            checked={registerDTO.gender === "여성"}
            onChange={handleChange}
          />{" "}
          여성
          {error.gender && <p style={{ color: "red" }}>{error.gender}</p>}
        </div>
        <br />
        <div>
          <label>키 : </label>
          <input type='text' name='height' value={registerDTO.height} onChange={handleChange} />
          {error.height && <p style={{ color: "red" }}>{error.height}</p>}
        </div>
        <br />
        <div>
          <label>몸무게 : </label>
          <input type='text' name='weight' value={registerDTO.weight} onChange={handleChange} />
          {error.weight && <p style={{ color: "red" }}>{error.weight}</p>}
        </div>
        <br />
        <div>
          <label>나이 : </label>
          <input type='text' name='age' value={registerDTO.age} onChange={handleChange} />
          {error.age && <p style={{ color: "red" }}>{error.age}</p>}
        </div>
        <button type='submit'>Register</button>
      </form>
      {error.form && <p style={{ color: "red" }}>{error.form}</p>}
      {success && <p>Registration successful!</p>}
    </div>
  );
};

export default Register;
