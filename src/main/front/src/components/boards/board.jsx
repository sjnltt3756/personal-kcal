import React, { useEffect, useState } from "react";
import { createBoard, getBoardList, viewBoard, updateBoard, deleteBoard } from "../../api/fetchBoard";

const BoardComponent = () => {
  const [boards, setBoards] = useState([]);
  const [board, setBoard] = useState(null);
  const [newBoard, setNewBoard] = useState({
    title: "",
    content: "",
    nickname: "",
  });

  // 게시글 목록 조회
  const fetchBoardList = async () => {
    const data = await getBoardList(0, 10); // 페이지 번호와 크기 설정
    setBoards(data.content);
  };

  // 게시글 작성
  const handleCreateBoard = async () => {
    await createBoard(newBoard);
    fetchBoardList(); // 목록 갱신
  };

  // 특정 게시글 조회
  const handleViewBoard = async (boardId) => {
    const data = await viewBoard(boardId);
    setBoard(data);
  };

  // 게시글 수정
  const handleUpdateBoard = async (boardId) => {
    const updatedBoard = {
      title: "Updated Title",
      content: "Updated Content",
    };
    await updateBoard(boardId, updatedBoard);
    fetchBoardList(); // 목록 갱신
  };

  // 게시글 삭제
  const handleDeleteBoard = async (boardId) => {
    await deleteBoard(boardId);
    fetchBoardList(); // 목록 갱신
  };

  useEffect(() => {
    fetchBoardList();
  }, []);

  return (
    <div>
      <h1>Board List</h1>
      <ul>
        {boards.map((board) => (
          <li key={board.id}>
            {board.title}
            <button onClick={() => handleViewBoard(board.id)}>View</button>
            <button onClick={() => handleUpdateBoard(board.id)}>Update</button>
            <button onClick={() => handleDeleteBoard(board.id)}>Delete</button>
          </li>
        ))}
      </ul>

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
      <input
        type='text'
        placeholder='Nickname'
        value={newBoard.nickname}
        onChange={(e) => setNewBoard({ ...newBoard, nickname: e.target.value })}
      />
      <button onClick={handleCreateBoard}>Create</button>

      {board && (
        <div>
          <h1>View Board</h1>
          <h2>{board.title}</h2>
          <p>{board.content}</p>
          <p>By: {board.nickname}</p>
        </div>
      )}
    </div>
  );
};

export default BoardComponent;
