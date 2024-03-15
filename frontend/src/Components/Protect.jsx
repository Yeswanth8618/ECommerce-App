import { Navigate } from "react-router-dom";
const Protect = ({Child}) => {
    let x=localStorage.getItem("Merchant")
    let verify=()=>{
        if(x==null){
            return false
        }
        else{
            return true;
        }
    }
    return ( 
        <div>
            {verify()?<Child/>: <Navigate to="/merchant"/>}
        </div>
     );
}
 
export default Protect;