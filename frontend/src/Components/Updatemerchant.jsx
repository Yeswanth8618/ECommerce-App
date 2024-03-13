import { useState } from "react"
import axios from 'axios'
import '../styles/updatemerchant.css'
const Updatemerchant = () => {
     let [id,setid]=useState("")
     let [email, setemail] = useState("")
     let[password,setpassword]=useState("")
     let[name,setname]=useState("")
     let[phone,setphone]=useState("")
     let[gst_number,setgst_number]=useState("")
     
     const Submit = (e) => {
         e.preventDefault();
         const updatemerchant = {
              id,
             email,
             password,
             name,
             phone,
             gst_number
         };
         axios.put('http://localhost:8080/merchants', updatemerchant)
             .then((response) => {
                 console.log(response);
                 alert("Merchant updated successfully");
             })
             .catch((error) => {
                 console.error(error);
                 alert("Failed to update merchant");
             });
     };
    return ( 
        <div className="updatemerchant">
          <h4>Update Merchant</h4>
               <form action="" onSubmit={Submit}>
               <label htmlFor="">ID</label>
                <input type="number" placeholder="Enter The ID" value={id} onChange={(e) => { setid(e.target.value) }}required/>    
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
                <button className="btn btn-outline-info" id='btn2' type='submit' >Register</button>
            </form>
        </div>
     );
}
 
export default Updatemerchant;