import React, { useEffect, useState } from "react";
import { getBoardList, deleteBoard } from "../../api/fetchBoard";
import { Link } from "react-router-dom";

const BoardList = () => {
  const [boards, setBoards] = useState([]);

  const fetchBoardList = async () => {
    const data = await getBoardList(0, 10);

    setBoards(data.content);
  };

  useEffect(() => {
    fetchBoardList();
  }, []);

  const handleDeleteBoard = async (boardId) => {
    await deleteBoard(boardId);
    fetchBoardList();
  };

  return (
    <div>
      <h1>Board List</h1>
      <ul>
        {boards.map((board) => (
          <li key={board.id}>
            <Link to={`/boardView/${board.id}`}>{board.title}</Link>
            <button onClick={() => handleDeleteBoard(board.id)}>Delete</button>
          </li>
        ))}
      </ul>
      <Link to='/boardWrite'>
        <button>Create Board</button>
      </Link>
    </div>
  );
};

export default BoardList;
