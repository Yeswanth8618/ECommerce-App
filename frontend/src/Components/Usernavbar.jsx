import Dropdown from 'react-bootstrap/Dropdown';
import PersonIcon from '@mui/icons-material/Person';
import { Link } from "react-router-dom";
const Usernavbar = () => {
    return ( 
        <div className="usernavbar">
 <nav>
            <div className="logo2">
                <h5>🆂🅷🅾🅿🅿🅴🆁🆂🅲🅰🆁🆃</h5>
            </div>
            {/* <div className="option2">
                <Link to="/merchanthome/productview">𝐕𝐢𝐞𝐰 𝐏𝐫𝐨𝐝𝐮𝐜𝐭𝐬</Link>
            </div> */}
            <div className="account2">
            <Dropdown>
      <Dropdown.Toggle variant="secondary" id="dropdown-basic">
       <PersonIcon/> Account
      </Dropdown.Toggle>

      <Dropdown.Menu>
        <Dropdown.Item href="/userhome/updateuser">Edit user</Dropdown.Item>
        <Dropdown.Item href="/">Logout</Dropdown.Item>
        {/* <Dropdown.Item href="#/action-3">Something else</Dropdown.Item> */}
      </Dropdown.Menu>
    </Dropdown>
            </div>
        </nav>
        </div>
     );
}
 
export default Usernavbar;
