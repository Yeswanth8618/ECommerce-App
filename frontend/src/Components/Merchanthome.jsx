import Merchantnavbar from "./Merchantnavbar";
import { Route,Routes } from "react-router-dom";
import Productview from "./Productview";
import Updatemerchant from "./Updatemerchant";
import Addproducts from "./Addproducts";
const Merchanthome = () => {
    return ( 
      <div className="merchanthome">
         <Merchantnavbar/>
           <Routes>
            <Route path="/productview" element={<Productview/>}/>
            <Route path="/updatemerchant" element={<Updatemerchant/>}/>
            <Route path="/addproducts" element={<Addproducts/>}/>
           </Routes>
           </div>
     );
}
export default Merchanthome;