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
import { RecoilRoot } from "recoil";
const App = () => {
  return (
    <div>
      <RecoilRoot>
        <Routes>
          <Route index element={<Main />} />
          <Route path='member/login' element={<Login />} />
          <Route path='member/register' element={<Register />} />
          <Route path='member/view/:id' element={<ViewMember />} />
          <Route path='board/list' element={<BoardList />} />
          <Route path='board/write' element={<BoardWrite />} />
          <Route path='board/update:boardId' element={<BoardUpdate />} />
          <Route path='board/view/:boardId' element={<BoardView />} />
        </Routes>
      </RecoilRoot>
    </div>
  );
};

export default App;
