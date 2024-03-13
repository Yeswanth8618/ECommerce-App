import {Link} from "react-router-dom"
import '../styles/Landingpage.css'
import img from "../images/mrbean.png"
const Landingpage = () => {
    return ( 
        <div className="landingpage">
              <h1 style={{color:"red"}}>E-Commerce Application</h1>
        <div className="link">
        <Link to="/merchant"><img src="https://clipart-library.com/images/kTKo7BB8c.png" alt="" /><h1 style={{color:"red"}}>Merchant</h1></Link>
        <Link to="/user"><img src={img} alt="" /><h1 style={{color:"red"}}>User</h1></Link>
        </div>
        </div>
     );
}
export default Landingpage;