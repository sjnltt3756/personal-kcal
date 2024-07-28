// boardWrite.jsx
import React, { useState } from "react";
import { createBoard } from "../../api/fetchBoard";

const BoardWrite = () => {
  const [newBoard, setNewBoard] = useState({
    title: "",
    content: "",
    nickname: "",
  });

  const handleCreateBoard = async () => {
    await createBoard(newBoard);
  };

  return (
    <div>
      <h1>Create Board</h1>
      <input
        type='text'
        placeholder='Title'
        value={newBoard.title}
        onChange={(e) => setNewBoard({ ...newBoard, title: e.target.value })}
      />
      <input
        type='text'
        placeholder='Content'
        value={newBoard.content}
        onChange={(e) => setNewBoard({ ...newBoard, content: e.target.value })}
      />
      <button onClick={handleCreateBoard}>Create</button>
    </div>
  );
};

export default BoardWrite;
