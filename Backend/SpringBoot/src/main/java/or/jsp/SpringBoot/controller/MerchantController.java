package or.jsp.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import or.jsp.SpringBoot.dto.ResponseStructure;
import or.jsp.SpringBoot.model.Merchant;
import or.jsp.SpringBoot.service.MerchantService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/merchants")
public class MerchantController {
	@Autowired
	private MerchantService merchantservice;

	@PostMapping
	public ResponseEntity<ResponseStructure<Merchant>> savemerchant(@RequestBody Merchant merchant,
			HttpServletRequest request) {
		return merchantservice.savemerchant(merchant, request);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure> updatemerchant(@RequestBody Merchant merchant) {
		return merchantservice.updatemerchant(merchant);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure> findbyid(@PathVariable(name = "id") int id) {
		return merchantservice.findbyid(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure> deletebyid(@PathVariable(name = "id") int id) {
		return merchantservice.deletebyid(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure> findall() {
		return merchantservice.findall();
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure> verifyMerchant(@RequestParam long phone, @RequestParam String password) {
		return merchantservice.verifyMerchant(phone, password);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure> verifyMerchantbyemail(@RequestParam String email,
			@RequestParam String password) {
		return merchantservice.verifyMerchantbyemail(email, password);
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure> findbyname(@PathVariable String name) {
		return merchantservice.findbyname(name);
	}

    @GetMapping("/Activate")
     public ResponseEntity<ResponseStructure<String>> FindByToken(@RequestParam String token) {
	    return merchantservice.findByToken(token); 
    }  

}
