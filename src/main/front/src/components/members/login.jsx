import React, { useState } from "react";
import { loginMember } from "../../api/fetchMember";
import { useNavigate } from "react-router-dom";

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

  return (
    <div>
      <h2>로그인</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input type='text' name='nickname' value={loginDTO.nickname} onChange={handleChange} />{" "}
        </div>
        <button type='submit'>Login</button>
      </form>
      {error && <p>{error}</p>}
    </div>
  );
};

export default Login;
