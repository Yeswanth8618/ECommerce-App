import logo from './logo.svg';
import './App.css';
import { BrowserRouter,Route,Routes } from 'react-router-dom';
import Landingpage from './Components/Landingpage';
import Merchantlogin from './Components/Merchantlogin';
import Userlogin from './Components/Userlogin';
import 'bootstrap/dist/css/bootstrap.min.css';
import Merchantsignup from './Components/Merchantsignup';
import Usersignup from './Components/Usersignup';
import Merchanthome from './Components/Merchanthome';
import Userhome from './Components/Userhome';
import Error from './Components/Error';
import Protect from './Components/Protect';
import Protect2 from './Components/Protect2';

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path='/' element={<Landingpage/>}/>
      <Route path="/*" element={<Error/>}/>
      <Route path='/merchant' element={<Merchantlogin/>}/>
      <Route path='/user' element={<Userlogin/>}/>
      <Route path='/merchantsignup' element={<Merchantsignup/>}/>
      <Route path='/usersignup' element={<Usersignup/>}/>
      <Route path='/merchanthome/*' element={<Protect Child={Merchanthome}/>}/>
      <Route path='/userhome/*' element={<Protect2 Child={Userhome}/>}/>
    </Routes>
    </BrowserRouter>
  );
}

export default App;
