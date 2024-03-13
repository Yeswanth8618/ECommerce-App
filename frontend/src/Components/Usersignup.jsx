import { useState } from "react"
import '../styles/usersignin.css'
import axios from "axios"
const Usersignup = () => {
    let [email, setemail] = useState("")
    let[password,setpassword]=useState("")
    let[name,setname]=useState("")
    let[phone,setphone]=useState("")
    let[age,setage]=useState("")
    let[gender,setgender]=useState("")
    let verifyuser=(e)=>{
        e.preventDefault();
        let newuser={
            email,
            password,
            name,
            phone,
            age,
            gender
        }
        axios.post(`http://localhost:8080/users`,newuser)
        .then((res)=>{
            console.log(res);
            alert("user saved successfully")
        })
        .catch((error)=>{
            console.log(error);
            alert("user save failed")
        });
    }   ;
    return ( 
        <div className="all2">
        <div className="back2">
        </div>
        <div className="usersignin">
        <form action="" onSubmit={verifyuser}>
                <label htmlFor="">Name</label>
                <input type="text" placeholder="Enter The Name" value={name} onChange={(e) => { setname(e.target.value) }}required/>
                <label htmlFor="">Phone No</label>
                <input type="tel" placeholder="Enter The phone" value={phone} onChange={(e) => { setphone(e.target.value) }}required/>
                <label htmlFor="">Age</label>
                <input type="number" placeholder="Enter The Age" value={age} onChange={(e) => { setage(e.target.value) }}required/>
                <label htmlFor="">Email</label>
                <input type="email" placeholder="Enter The email"  value={email} onChange={(e) => { setemail(e.target.value) }} required/>
                <label htmlFor="">Password</label>
                <input type="password" placeholder="Enter The password" value={password} onChange={(e) => { setpassword(e.target.value) }}required />
                <label htmlFor="">Gender</label>
                       <div className="gen">
                            <input type="radio" name="gender" value="male" checked={gender === "male"} onChange={(e) => setgender(e.target.value)} required />
                            Male
                         <input type="radio" name="gender" value="female" checked={gender === "female"} onChange={(e) => setgender(e.target.value)} required />
                            Female
                           <input type="radio" name="gender" value="other" checked={gender === "other"} onChange={(e) => setgender(e.target.value)} required />
                            Other
                            </div>
                <button className="btn btn-outline-info" id='btn'type="submit">Register</button>
            </form>
        </div>
        </div>
     );
}
 
export default Usersignup;