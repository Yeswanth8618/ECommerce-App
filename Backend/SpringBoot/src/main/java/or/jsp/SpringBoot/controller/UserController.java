package or.jsp.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import or.jsp.SpringBoot.dto.ResponseStructure;
import or.jsp.SpringBoot.model.User;
import or.jsp.SpringBoot.service.UserService;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userservice;
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveuser(@RequestBody User user,HttpServletRequest request) {
		return userservice.saveuser(user,request);
	}
	@PutMapping 
	public ResponseEntity<ResponseStructure> updateuser(@RequestBody User user) {
		return userservice.updateuser(user); 
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure> findbyid(@PathVariable(name = "id")int id) {
		return userservice.findbyid(id);
	}
	
	@DeleteMapping
	(value = "/{id}")
	public ResponseEntity<ResponseStructure> deletebyid(@PathVariable(name = "id")int id) {
		return userservice.deletebyid(id); 
	}
	@GetMapping
	public ResponseEntity<ResponseStructure> findall(){
     return userservice.findall();
	}
	

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure> verifyuser(@RequestParam long phone, @RequestParam String password) {
		return userservice.verifyuser(phone, password);
	}
	
	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure> verifyuserbyemail(@RequestParam String email, @RequestParam String password) {
		return userservice.verifyuserbyemail(email, password);
	}
	
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure> findbyname(@PathVariable String name){
	return userservice.findbyname(name);	
	}	
	@GetMapping("/Activate")
	public ResponseEntity<ResponseStructure<String>> findByToken(@RequestParam String token) {
		return userservice.findByToken(token);
	}
}
