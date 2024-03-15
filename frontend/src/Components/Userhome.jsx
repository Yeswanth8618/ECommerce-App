import { Route,Routes } from "react-router-dom";
import Usernavbar from "./Usernavbar";
import Updateuser from "./Updateuser";
const Userhome = () => {
    return ( 
        <div className="userhome">
            <Usernavbar/>
            <Routes>
            <Route path="/updateuser" element={<Updateuser/>}/>
           </Routes>
        </div>
     );
}
export default Userhome;