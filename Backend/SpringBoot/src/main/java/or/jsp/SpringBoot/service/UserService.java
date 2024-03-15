package or.jsp.SpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import or.jsp.SpringBoot.Exception.IdNotFoundException;
import or.jsp.SpringBoot.Exception.InvalidCredentialsException;
import or.jsp.SpringBoot.Exception.NullPointerException;
import or.jsp.SpringBoot.Exception.UserNotFoundException;
import or.jsp.SpringBoot.dao.UserDao;
import or.jsp.SpringBoot.dto.ResponseStructure;
import or.jsp.SpringBoot.model.User;
import or.jsp.SpringBoot.util.AccountStatus;

@Service
public class UserService {
	@Autowired
	private UserDao userdao;
	@Autowired
	private ECommerceApplicationMailService usermail;
	
	public ResponseEntity<ResponseStructure<User>>saveuser(User user,HttpServletRequest request){
		ResponseStructure<User>structure=new ResponseStructure<>();
		user.setStatus(AccountStatus.IN_ACTIVE.toString());
		user.setToken(RandomString.make(45));
		structure.setBody(userdao.saveuser(user));
		String message=usermail.WelcomeMailuser(user, request);
		structure.setMessage("user saved"+" "+message);
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.CREATED);
		
	}
	public ResponseEntity<ResponseStructure> updateuser(User user) {
		Optional<User>recuser=userdao.findbyid(user.getId());
		ResponseStructure<User> structure=new ResponseStructure();
		if(recuser.isPresent()) {
			User dbuser=recuser.get();
			dbuser.setEmail(user.getEmail());
			dbuser.setName(user.getName());
			dbuser.setPassword(user.getPassword());
			dbuser.setPhone(user.getPhone());
			dbuser.setGender(user.getGender());
			structure.setMessage("user updated");
			structure.setBody(userdao.saveuser(user));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED) ;
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure> findbyid(int id) {
		Optional<User>recuser=userdao.findbyid(id);
		ResponseStructure<User> structure=new ResponseStructure();
		if(recuser.isPresent()) {
			structure.setMessage("user updated");
			structure.setBody(recuser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ; 
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure> deletebyid(int id) {
		Optional<User>recuser=userdao.findbyid(id);
		ResponseStructure<String> structure=new ResponseStructure();
		if(recuser.isPresent()) {
		    userdao.deletebyid(id);
			structure.setMessage("user deleted");
			structure.setBody("deleted successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		throw new IdNotFoundException(); 
	}
	
	public ResponseEntity<ResponseStructure> findall(){
		ResponseStructure<List<User>> structure=new ResponseStructure();
		List<User>recuser=userdao.findall();
		
		if(recuser.size()>0) {
			structure.setMessage("user find");
			structure.setBody(recuser);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		throw new NullPointerException(null);
	}
	public ResponseEntity<ResponseStructure> verifyuser( long phone, String password) {
		Optional<User>recuser=userdao.verifyuser(phone, password);
		ResponseStructure<User> structure=new ResponseStructure();
		if(recuser.isPresent()) {
			structure.setMessage("user find");
			structure.setBody(recuser.get());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED) ; 
		}
		throw new InvalidCredentialsException("wrong phone or password");
	}
	
	public ResponseEntity<ResponseStructure> verifyuserbyemail( String email, String password) {
		Optional<User>recuser=userdao.verifyuserbyemail(email, password);
		ResponseStructure<User> structure=new ResponseStructure();
		if(recuser.isPresent()) {
			structure.setMessage("user find");
			structure.setBody(recuser.get());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED) ; 
		}
		throw new InvalidCredentialsException("wrong email or password");
	}
	
	public ResponseEntity<ResponseStructure> findbyname(String name){
		ResponseStructure<List<User>> structure=new ResponseStructure();
		List<User>recuser=userdao.findbyname(name);
		if(recuser.size()>0) {
			structure.setMessage("user find");
			structure.setBody(recuser);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		throw new UserNotFoundException(name);
	}
	public ResponseEntity<ResponseStructure<String>> findByToken(String token) {
		Optional<User> recuser = userdao.findByToken(token);
		ResponseStructure<String> structure = new ResponseStructure();
		if (recuser.isPresent()) {
			User user=recuser.get();
			user.setStatus(AccountStatus.ACTIVE.toString());
			user.setToken(null);
			userdao.saveuser(user);
			structure.setBody("user found");
			structure.setMessage("account verified");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
		}
		throw new UserNotFoundException("Invalid url");
	}
}
