import "./App.css";
import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./components/members/login";
import Register from "./components/members/register";
import ViewMember from "./components/members/viewMember";
import { Main } from "./components/main";
import BoardWrite from "./components/boards/boardWrite";
import BoardUpdate from "./components/boards/boardUpdate";
import BoardList from "./components/boards/boardList";
import BoardView from "./components/boards/boardView";
const App = () => {
  return (
    <div>
      <Routes>
        <Route index element={<Main />} />
        <Route path='member/login' element={<Login />} />
        <Route path='member/register' element={<Register />} />
        <Route path='member/view/:id' element={<ViewMember />} />
        <Route path='board/list' element={<BoardList />} />
        <Route path='board/write' element={<BoardWrite />} />
        <Route path='board/update:id' element={<BoardUpdate />} />
        <Route path='board/view/:id' element={<BoardView />} />
      </Routes>
    </div>
  );
};

export default App;
