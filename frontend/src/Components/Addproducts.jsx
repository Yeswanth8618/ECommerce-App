import { useState } from "react";
import axios from "axios";
import '../styles/addproducts.css'
const Addproducts = () => {
    let [name, setname] = useState("")
    let[brand,setbrand]=useState("")
    let[category,setcategory]=useState("")
    let[description,setdescription]=useState("")
    let[cost,setcost]=useState("")
    let[image_url,setimage_url]=useState("")
    
    let data={name,brand,category,description,cost,image_url};
    let ip=JSON.parse(localStorage.getItem("Merchant"));
    let addproducts=(e)=>{
        e.preventDefault()
        axios.post(`http://localhost:8080/products/${ip.id}`,data)
        .then((response) => {
            console.log(response);
            alert("Product added successfully");
        })
        .catch((error) => {
            console.error(error);
            alert("Failed to add Product");
        });
    }
    return ( 
        <div className="addproducts">
            <form action="" onSubmit={addproducts}>
                <label htmlFor="">Name</label>
                <input type="text" required placeholder="Enter The Name" value={name} onChange={(e) => { setname(e.target.value) }}/>
                <label htmlFor="">Brand</label>
                <input type="text" required placeholder="Enter The Brand" value={brand} onChange={(e) => { setbrand(e.target.value) }}/>
                <label htmlFor="">Category</label>
                <input type="text" required placeholder="Enter The Category" value={category} onChange={(e) => { setcategory(e.target.value) }}/>
                <label htmlFor="">Description</label>
                <input type="text" required placeholder="Enter The Description" value={description} onChange={(e) => { setdescription(e.target.value) }}/>
                <label htmlFor="">Cost</label>
                <input type="text" required placeholder="Enter The Cost" value={cost} onChange={(e) => { setcost(e.target.value) }}/>
                <label htmlFor="">Image_Url</label>
                <input type="text" required placeholder="Enter The Image_url" value={image_url} onChange={(e) => { setimage_url(e.target.value) }}/>
                <button type="submit">Submit</button>
            </form>
        </div>
     );
}
 
export default Addproducts;