import { Link } from "react-router-dom";

export function Main({ loggedInMemberId }) {
  return (
    <div>
      <h1>Main Page</h1>
      <ul>
        <li>
          <Link to='/member/register'>Register</Link>
        </li>
        <li>
          <Link to='/member/login'>Login</Link>
        </li>
      </ul>
    </div>
  );
}
