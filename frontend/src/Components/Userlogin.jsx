import Form from 'react-bootstrap/Form';
import '../styles/Userlogin.css'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { Link } from 'react-router-dom';
const Userlogin = () => {
  let [email, setemail] = useState("")
  let [password, setpassword] = useState("")
  let navigate=useNavigate()
  function verifyuserbyemail(e) {
    e.preventDefault();
    axios.post(`http://localhost:8080/users/verify-by-email?email=${email}&password=${password}`)
    .then((res)=>{
      console.log(res.data.body);
    localStorage.setItem("User",JSON.stringify(res.data.body))
    alert("login successfull")
    navigate('/userhome')
    })
    .catch((rej)=>{
      console.log(rej);
      alert("login failed")
    })
  }
    return ( 
        <div className="userlogin">
             <Form>
      <Form.Group className="mb-3" controlId="formGroupEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" placeholder="Enter email" value={email} onChange={(e) => { setemail(e.target.value) }}/>
      </Form.Group>
      <Form.Group className="mb-3" controlId="formGroupPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" placeholder="Password" value={password} onChange={(e) => { setpassword(e.target.value) }} />
      </Form.Group>
      <Form.Group>
        <button className='btn btn-primary mx-5' onClick={verifyuserbyemail}>Sign in</button>
        <button className='btn btn-danger mx-5'><Link to="/usersignup" id='signup' >Sign up</Link></button>
      </Form.Group>
    </Form>
        </div>
     );
}
export default Userlogin;