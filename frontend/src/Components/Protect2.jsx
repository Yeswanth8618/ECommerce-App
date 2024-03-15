import { Navigate } from "react-router-dom";
const Protect2 = ({Child}) => {
    let x=localStorage.getItem("User")
    let verify2=()=>{
        if(x==null){
            return false
        }
        else{
            return true;
        }
    }
    return ( 
        <div>
   {verify2()?<Child/>: <Navigate to="/user"/>}
        </div>
     );
}
 
export default Protect2; 