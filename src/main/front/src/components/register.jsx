import React, { useState } from "react";
import { registerMember } from "../api/fetchMember";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [registerDTO, setRegisterDTO] = useState({ nickname: "", gender: "", height: "", weight: "", age: "" });
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setRegisterDTO({ ...registerDTO, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const registeredMember = await registerMember(registerDTO);
      console.log("Registered member:", registeredMember);
      setSuccess(true);
      navigate("/member/login");
    } catch (error) {
      setError("Registration failed. Please try again.");
    }
  };

  return (
    <div>
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>닉네임:</label>
          <input type='text' name='nickname' value={registerDTO.nickname} onChange={handleChange} />
        </div>
        <div>
          <label>성별:</label>
          <input type='gender' name='gender' value={registerDTO.gender} onChange={handleChange} />
        </div>
        <div>
          <label>키:</label>
          <input type='height' name='height' value={registerDTO.height} onChange={handleChange} />
        </div>
        <div>
          <label>몸무게:</label>
          <input type='weight' name='weight' value={registerDTO.weight} onChange={handleChange} />
        </div>
        <div>
          <label>나이:</label>
          <input type='age' name='age' value={registerDTO.age} onChange={handleChange} />
        </div>
        <button type='submit'>Register</button>
      </form>
      {error && <p>{error}</p>}
      {success && <p>Registration successful!</p>}
    </div>
  );
};

export default Register;
