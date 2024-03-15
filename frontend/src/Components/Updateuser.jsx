import axios from "axios"
import { useState, useEffect } from "react";
import '../styles/updateuser.css'
const Updateuser = () => {
    let [id,setid]=useState("")
    let [email, setemail] = useState("")
    let[password,setpassword]=useState("")
    let[name,setname]=useState("")
    let[phone,setphone]=useState("")
    let[age,setage]=useState("")
    let[gender,setgender]=useState("")

    let ip = JSON.parse(localStorage.getItem("User"));
    
    useEffect(() => {
        setid(ip.id);
        setemail(ip.email);
        setpassword(ip.password);
        setname(ip.name);
        setphone(ip.phone);
        setage(ip.age);
        setgender(ip.gender);
    }, []);
    let edituser=(e)=>{
        e.preventDefault();
        let newuser={
            id,
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
      
        <div className="updateuser">
            <h4>Update User</h4>
             <form action="" onSubmit={edituser}>
             <label>ID</label>
                <input type="number" placeholder="Enter The ID" value={id} onChange={(e) => setid(e.target.value)} disabled/>
                <label htmlFor="">Name</label>
                <input type="text" placeholder="Enter The Name" value={name} onChange={(e) => { setname(e.target.value) }}required/>
                <label htmlFor="">Phone No</label>
                <input type="tel" placeholder="Enter The phone" value={phone} onChange={(e) => { setphone(e.target.value) }}required/>
                <label htmlFor="">Age</label>
                <input type="number" placeholder="Enter The Age" value={age} onChange={(e) => { setage(e.target.value) }}required/>
                <label htmlFor="">Email</label>
                <input type="email" placeholder="Enter The email"  value={email} onChange={(e) => { setemail(e.target.value) }} required/>
                <label htmlFor="">Password</label>
                <input type="text" placeholder="Enter The password" value={password} onChange={(e) => { setpassword(e.target.value) }}required />
                <label htmlFor="">Gender</label>
                       <div className="gen">
                            <input type="radio" name="gender" value="male" checked={gender === "male"} onChange={(e) => setgender(e.target.value)} required />
                            Male
                         <input type="radio" name="gender" value="female" checked={gender === "female"} onChange={(e) => setgender(e.target.value)} required />
                            Female
                           <input type="radio" name="gender" value="other" checked={gender === "other"} onChange={(e) => setgender(e.target.value)} required />
                            Other
                            </div>
                <button className="btn btn-outline-info" id='btn2'type="submit">Update</button>
            </form>
        </div>
     );
}
 
export default Updateuser;