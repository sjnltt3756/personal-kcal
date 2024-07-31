// boardUpdate.jsx
import React, { useState } from "react";
import { viewBoard } from "../../api/fetchBoard";

const BoardUpdate = ({ boardId }) => {
  const [board, setBoard] = useState(null);

  const handleViewBoard = async () => {
    const data = await viewBoard(boardId);
    setBoard(data);
  };

  return (
    <div>
      <h1>View Board</h1>
      {board && (
        <>
          <h2>{board.title}</h2>
          <p>{board.content}</p>
          <p>By: {board.nickname}</p>
        </>
      )}
      <button onClick={handleViewBoard}>View</button>
    </div>
  );
};

export default BoardUpdate;
