import React from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, ListGroup, ListGroupItem } from "reactstrap";
import "../css/main.css";

export function Main({ loggedInMemberId }) {
  return (
    <Container className='mt-5'>
      <Row className='justify-content-center'>
        <Col md='6'>
          <h1 className='text-center mb-4'>칼로리 계산해드려요~</h1>
          <div className='list-group-container'>
            <ListGroup>
              <ListGroupItem className='text-center'>
                <Link to='/member/register' className='text-decoration-none'>
                  회원가입
                </Link>
              </ListGroupItem>
              <ListGroupItem className='text-center'>
                <Link to='/member/login' className='text-decoration-none'>
                  로그인
                </Link>
              </ListGroupItem>
              <ListGroupItem className='text-center'>
                <Link to='/board/list' className='text-decoration-none'>
                  게시판
                </Link>
              </ListGroupItem>
            </ListGroup>
          </div>
        </Col>
      </Row>
    </Container>
  );
}

export default Main;
