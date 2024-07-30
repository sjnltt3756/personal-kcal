import React, { useEffect, useState } from "react";
import { getBoardList, deleteBoard } from "../../api/fetchBoard";
import { Link, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Table, Button, Container, Row, Col } from "reactstrap";

const BoardList = () => {
  const [boards, setBoards] = useState([]);
  const navigate = useNavigate();
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
  const goToMainPage = () => {
    navigate("/"); // 메인 페이지로 이동
  };

  return (
    <Container>
      <Row>
        <Col>
          <h1 className='my-4'>게시판</h1>
          <Table striped>
            <thead>
              <tr>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>삭제</th>
              </tr>
            </thead>
            <tbody>
              {boards.map((board) => (
                <tr key={board.id}>
                  <td>
                    <Link to={`/board/view/${board.id}`}>{board.title}</Link>
                  </td>
                  <td>{board.nickname}</td>
                  <td>{board.viewCount}</td>
                  <td>
                    <Button color='danger' onClick={() => handleDeleteBoard(board.id)}>
                      Delete
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
          <Link to='/board/write'>
            <Button color='primary' className='mt-4'>
              게시글 작성
            </Button>
            <br />
          </Link>
          <Button color='secondary' className='mt-4' onClick={goToMainPage}>
            메인 화면으로
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default BoardList;
