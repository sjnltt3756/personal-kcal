import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { viewBoard } from "../../api/fetchBoard";

const BoardView = () => {
  const { boardId } = useParams();
  const [board, setBoard] = useState(null);

  const fetchBoard = async () => {
    const data = await viewBoard(boardId);
    setBoard(data);
  };

  useEffect(() => {
    fetchBoard();
  }, [boardId]);

  if (!board) return <div>Loading...</div>;

  return (
    <div>
      <h1>{board.title}</h1>
      <p>{board.content}</p>
    </div>
  );
};

export default BoardView;
