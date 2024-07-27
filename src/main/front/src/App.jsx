import "./App.css";
import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./components/members/login";
import Register from "./components/members/register";
import ViewMember from "./components/members/viewMember";
import { Main } from "./components/main";
import BoardComponent from "./components/boards/board";
const App = () => {
  return (
    <div>
      <Routes>
        <Route index element={<Main />} />
        <Route path='member/login' element={<Login />} />
        <Route path='member/register' element={<Register />} />
        <Route path='member/view/:id' element={<ViewMember />} />
        <Route path='board/list' element={<BoardComponent />} />
      </Routes>
    </div>
  );
};

export default App;
