import '../styles/merchantsignup.css'
import { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
const Merchantsignup = () => {
    let [email, setemail] = useState("")
    let[password,setpassword]=useState("")
    let[name,setname]=useState("")
    let[phone,setphone]=useState("")
    let[gst_number,setgst_number]=useState("")
    let nav=useNavigate()
    const handleSubmit = (e) => {
        e.preventDefault();
        const saveMerchant = {
            email,
            password,
            name,
            phone,
            gst_number
        };
        axios.post('http://localhost:8080/merchants', saveMerchant)
            .then((response) => {
                console.log(response);
                alert("Merchant added successfully");
                nav('/merchant')
            })
            .catch((error) => {
                console.error(error);
                alert("Failed to add merchant");
            });
    };
    return ( 
        <div className="all">
        <div className="back">

        </div>
        <div className="merchantsignup">
            <form action="" onSubmit={handleSubmit}>
                <label htmlFor="">Name</label>
                <input type="text" placeholder="Enter The Name" value={name} onChange={(e) => { setname(e.target.value) }}required/>
                <label htmlFor="">Gst_Number</label>
                <input type="text" placeholder="Enter The Gst_Number"value={gst_number} onChange={(e) => { setgst_number(e.target.value) }}required/>
                <label htmlFor="">Phone No</label>
                <input type="tel" placeholder="Enter The phone" value={phone} onChange={(e) => { setphone(e.target.value) }}required/>
                <label htmlFor="">Email</label>
                <input type="email" placeholder="Enter The email"  value={email} onChange={(e) => { setemail(e.target.value) }} required/>
                <label htmlFor="">Password</label>
                <input type="password" placeholder="Enter The password" value={password} onChange={(e) => { setpassword(e.target.value) }}required />
                <button className="btn btn-outline-info" id='btn' type='submit' >Register</button>
            </form>
        </div>
        </div>
     );
}
export default Merchantsignup;