import { useState, useEffect } from "react";
import axios from 'axios';
import '../styles/updatemerchant.css'

const Updatemerchant = () => {
    let [id, setId] = useState("");
    let [email, setEmail] = useState("");
    let [password, setPassword] = useState("");
    let [name, setName] = useState("");
    let [phone, setPhone] = useState("");
    let [gstNumber, setGstNumber] = useState("");

    const updateMerchant = {
        id,
        email,
        password,
        name,
        phone,
        gst_number: gstNumber
    };
    
    let ip = JSON.parse(localStorage.getItem("Merchant"));
    
    useEffect(() => {
        setId(ip.id);
        setEmail(ip.email);
        setPassword(ip.password);
        setName(ip.name);
        setPhone(ip.phone);
        setGstNumber(ip.gst_number);
    }, []);

    const submit = (e) => {
        e.preventDefault();
        axios.put('http://localhost:8080/merchants', updateMerchant)
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
            <form onSubmit={submit}>
                <label>ID</label>
                <input type="number" placeholder="Enter The ID" value={id} onChange={(e) => setId(e.target.value)} disabled/>    
                <label>Name</label>
                <input type="text" placeholder="Enter The Name" value={name} onChange={(e) => setName(e.target.value)} required/>
                <label>GST Number</label>
                <input type="text" placeholder="Enter The GST Number" value={gstNumber} onChange={(e) => setGstNumber(e.target.value)} required/>
                <label>Phone No</label>
                <input type="tel" placeholder="Enter The phone" value={phone} onChange={(e) => setPhone(e.target.value)} required/>
                <label>Email</label>
                <input type="email" placeholder="Enter The email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
                <label>Password</label>
                <input type="password" placeholder="Enter The password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                <button className="btn btn-outline-info" id='btn2' type='submit'>Update</button>
            </form>
        </div>
    );
}

export default Updatemerchant;
