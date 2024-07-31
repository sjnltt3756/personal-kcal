import React, { useState } from "react";
import { createBoard } from "../../api/fetchBoard";
import { Container, Table, FormGroup, Input, Button } from "reactstrap";
import { Link } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { memberState } from "../../recoil/atom";

const BoardWrite = () => {
  const loggedInMember = useRecoilValue(memberState); // Recoil 상태 사용

  const [newBoard, setNewBoard] = useState({
    title: "",
    content: "",
    nickname: "",
  });

  const handleCreateBoard = async () => {
    await createBoard(newBoard);
  };

  return (
    <Container className='mt-5'>
      <h1 className='mb-4'>게시글 작성</h1>
      <Table bordered>
        <tbody>
          <tr>
            <th scope='row'>제목</th>
            <td>
              <FormGroup>
                <Input
                  type='text'
                  placeholder='Title'
                  value={newBoard.title}
                  onChange={(e) => setNewBoard({ ...newBoard, title: e.target.value })}
                />
              </FormGroup>
            </td>
          </tr>
          <tr>
            <th scope='row'>내용</th>
            <td>
              <FormGroup>
                <Input
                  type='textarea'
                  placeholder='Content'
                  value={newBoard.content}
                  onChange={(e) => setNewBoard({ ...newBoard, content: e.target.value })}
                />
              </FormGroup>
            </td>
          </tr>
        </tbody>
      </Table>
      <Button color='primary' onClick={handleCreateBoard}>
        작성
      </Button>
      &nbsp;
      <Link to='/board/list'>
        <Button color='primary'>목록</Button>
      </Link>
    </Container>
  );
};

export default BoardWrite;
