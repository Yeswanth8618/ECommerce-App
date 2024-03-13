import { Link } from "react-router-dom";
import '../styles/merchantnavbar.css'
import Dropdown from 'react-bootstrap/Dropdown';
import PersonIcon from '@mui/icons-material/Person';
const Merchantnavbar = () => {
    return ( 
        <nav>
            <div className="logo">
                <h5>🆂🅷🅾🅿🅿🅴🆁🆂🅲🅰🆁🆃</h5>
            </div>
            <div className="option">
                <Link to="/merchanthome/productview">𝐕𝐢𝐞𝐰 𝐏𝐫𝐨𝐝𝐮𝐜𝐭𝐬</Link>
            </div>
            <div className="account">
            <Dropdown>
      <Dropdown.Toggle variant="secondary" id="dropdown-basic">
       <PersonIcon/> Account
      </Dropdown.Toggle>

      <Dropdown.Menu>
        <Dropdown.Item href="/merchanthome/updatemerchant">Update merchant</Dropdown.Item>
        <Dropdown.Item href="/">Logout</Dropdown.Item>
        {/* <Dropdown.Item href="#/action-3">Something else</Dropdown.Item> */}
      </Dropdown.Menu>
    </Dropdown>
            </div>
        </nav>
     );
}
 
export default Merchantnavbar;