import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { viewBoard } from "../../api/fetchBoard";
import { writeReply, deleteReply } from "../../api/fetchReply";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  Container,
  Row,
  Col,
  Card,
  CardBody,
  CardTitle,
  CardSubtitle,
  CardText,
  Button,
  Form,
  FormGroup,
  Input,
  ListGroup,
  ListGroupItem,
  Alert,
} from "reactstrap";

const BoardView = () => {
  const { boardId } = useParams();
  const [board, setBoard] = useState(null);
  const [newReply, setNewReply] = useState("");
  const [error, setError] = useState(null);

  const fetchBoard = async () => {
    try {
      const data = await viewBoard(boardId);
      setBoard(data);
    } catch (error) {
      console.error("Failed to fetch board:", error);
      setError("게시글을 가져오는 데 실패했습니다.");
    }
  };

  const handleReplySubmit = async (e) => {
    e.preventDefault();
    try {
      await writeReply({ boardId, content: newReply });
      setNewReply(""); // Clear the input field
      fetchBoard(); // Refresh the board data to include the new reply
    } catch (error) {
      console.error("Failed to write reply:", error);
      setError("댓글을 작성하는 데 실패했습니다.");
    }
  };

  const handleReplyDelete = async (replyId) => {
    try {
      await deleteReply(replyId);
      fetchBoard(); // Refresh the board data to exclude the deleted reply
    } catch (error) {
      console.error("Failed to delete reply:", error);
      setError("댓글을 삭제하는 데 실패했습니다.");
    }
  };

  useEffect(() => {
    fetchBoard();
  }, [boardId]);

  if (error) return <Alert color='danger'>{error}</Alert>;
  if (!board) return <div>Loading...</div>;

  return (
    <Container className='mt-5'>
      <Row className='justify-content-center'>
        <Col md='8'>
          <Card>
            <CardBody>
              <CardTitle tag='h1'>{board.title}</CardTitle>
              <CardSubtitle className='mb-2 text-muted'>작성자: {board.nickname}</CardSubtitle>
              <CardText>{board.content}</CardText>
              <Form onSubmit={handleReplySubmit}>
                <FormGroup>
                  <Input
                    type='textarea'
                    name='reply'
                    id='reply'
                    value={newReply}
                    onChange={(e) => setNewReply(e.target.value)}
                    placeholder='댓글을 작성하세요'
                    required
                  />
                </FormGroup>
                <Button color='primary' type='submit'>
                  댓글 작성
                </Button>
              </Form>
              <ListGroup className='mt-4'>
                {board.replies && board.replies.length > 0 ? (
                  board.replies.map((reply) => (
                    <ListGroupItem key={reply.id}>
                      {reply.replyContent}&nbsp;
                      <Button
                        color='danger'
                        size='sm'
                        className='float-right'
                        onClick={() => handleReplyDelete(reply.id)}
                      >
                        삭제
                      </Button>
                    </ListGroupItem>
                  ))
                ) : (
                  <ListGroupItem>댓글이 없습니다.</ListGroupItem>
                )}
              </ListGroup>
              <Button color='primary' className='mt-4' onClick={() => window.history.back()}>
                목록으로 돌아가기
              </Button>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default BoardView;
