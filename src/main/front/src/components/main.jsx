import { Link } from "react-router-dom";

export function Main({ loggedInMemberId }) {
  return (
    <div>
      <h1>칼로리 계산해드려요~</h1>
      <ul>
        <li>
          <Link to='/member/register'>회원가입</Link>
        </li>
        <li>
          <Link to='/member/login'>로그인</Link>
        </li>
        <li>
          <Link to='/board/list'>게시판</Link>
        </li>
      </ul>
    </div>
  );
}
