import Form from 'react-bootstrap/Form';
import '../styles/Merchantlogin.css'
import axios from 'axios';
import {  useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { Link } from 'react-router-dom';
const Merchantlogin = () => {
  let [email, setemail] = useState("")
  console.log(email);
  let [password, setpassword] = useState("")
  console.log(password);
  let navigate=useNavigate();
  function verifyMerchantbyemail(e) {
    e.preventDefault();
    axios.post(`http://localhost:8080/merchants/verify-by-email?email=${email}&password=${password}`)
    .then((res)=>{
    console.log(res.data.body);
    localStorage.setItem("Merchant",JSON.stringify(res.data.body))
    alert("login successfull")
    navigate('/merchanthome')
    })
    .catch((rej)=>{
      console.log(rej);
      alert("login failed")
    })
  }
  return (
    <div className="merchantlogin">
      <Form>
        <Form.Group className="mb-3" controlId="formGroupEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control type="email" value={email} onChange={(e) => { setemail(e.target.value) }} placeholder="Enter email" />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formGroupPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" value={password} onChange={(e) => { setpassword(e.target.value) }} placeholder="Password" />
        </Form.Group>
        <Form.Group>
          <button className='btn btn-primary mx-5' onClick={verifyMerchantbyemail}>Sign in</button>
          <button className='btn btn-danger mx-5'><Link to="/merchantsignup" id='signup'>Sign up</Link></button>
        </Form.Group>
      </Form>
    </div>
  );
}
export default Merchantlogin;