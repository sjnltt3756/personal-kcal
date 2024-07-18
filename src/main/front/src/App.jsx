import "./App.css";
import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./components/login";
import Register from "./components/register";
import ViewMember from "./components/viewMember";
import { Main } from "./components/main";
const App = () => {
  return (
    <div>
      <Routes>
        <Route index element={<Main />} />
        <Route path='member/login' element={<Login />} />
        <Route path='member/register' element={<Register />} />
        <Route path='member/view/:id' element={<ViewMember />} />
      </Routes>
    </div>
  );
};

export default App;
