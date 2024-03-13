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
import or.jsp.SpringBoot.Exception.MerchantNotFoundException;
import or.jsp.SpringBoot.Exception.NullPointerException;
import or.jsp.SpringBoot.dao.MerchantDao;
import or.jsp.SpringBoot.dto.ResponseStructure;
import or.jsp.SpringBoot.model.Merchant;
import or.jsp.SpringBoot.util.AccountStatus;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantdao;

	@Autowired
	private ECommerceApplicationMailService emailservice;

	public ResponseEntity<ResponseStructure<Merchant>> savemerchant(Merchant merchant, HttpServletRequest request) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(45));
		structure.setBody(merchantdao.savemerchant(merchant));
		String message = emailservice.WelcomeMail(merchant, request);
		structure.setMessage("merchant saved" + " " + message);
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure> updatemerchant(Merchant merchant) {
		Optional<Merchant> recMerchant = merchantdao.findbyid(merchant.getId());
		ResponseStructure<Merchant> structure = new ResponseStructure();
		if (recMerchant.isPresent()) {
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setPhone(merchant.getPhone());
			structure.setMessage("Merchant updated");
			structure.setBody(merchantdao.savemerchant(merchant));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure> findbyid(int id) {
		Optional<Merchant> recMerchant = merchantdao.findbyid(id);
		ResponseStructure<Merchant> structure = new ResponseStructure();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant updated");
			structure.setBody(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure> deletebyid(int id) {
		Optional<Merchant> recMerchant = merchantdao.findbyid(id);
		ResponseStructure<String> structure = new ResponseStructure();
		if (recMerchant.isPresent()) {
			merchantdao.deletebyid(id);
			structure.setMessage("Merchant deleted");
			structure.setBody("deleted successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure> findall() {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure();
		List<Merchant> recMerchant = merchantdao.findall();

		if (recMerchant.size() > 0) {
			structure.setMessage("Merchant find");
			structure.setBody(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK);
		}
		throw new NullPointerException(null);
	}

	public ResponseEntity<ResponseStructure> verifyMerchant(long phone, String password) {
		Optional<Merchant> recMerchant = merchantdao.verifyMerchant(phone, password);
		ResponseStructure<Merchant> structure = new ResponseStructure();
		if (recMerchant.isPresent()) {
			structure.setMessage("method find");
			structure.setBody(recMerchant.get());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED);
		}
		throw new InvalidCredentialsException("wrong phone or password");
	}

	public ResponseEntity<ResponseStructure> verifyMerchantbyemail(String email, String password) {
		Optional<Merchant> recMerchant = merchantdao.verifyMerchantbyemail(email, password);
		ResponseStructure<Merchant> structure = new ResponseStructure();
		if (recMerchant.isPresent()) {
			structure.setMessage("method find");
			structure.setBody(recMerchant.get());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED);
		}
		throw new InvalidCredentialsException("wrong email or password");
	}

	public ResponseEntity<ResponseStructure> findbyname(String name) {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure();
		List<Merchant> recMerchant = merchantdao.findbyname(name);
		if (recMerchant.size() > 0) {
			structure.setMessage("Merchant find");
			structure.setBody(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK);
		}
		throw new MerchantNotFoundException(name);

	}

	public ResponseEntity<ResponseStructure<String>> findByToken(String token) {
		Optional<Merchant> recMerchant = merchantdao.FindByToken(token);
		ResponseStructure<String> structure = new ResponseStructure();
		if (recMerchant.isPresent()) {
			Merchant merchant=recMerchant.get();
			merchant.setStatus(AccountStatus.ACTIVE.toString());
			merchant.setToken(null);
			merchantdao.savemerchant(merchant);
			structure.setBody("merchant found");
			structure.setMessage("account verified");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
		}
		throw new MerchantNotFoundException("Invalid url");
	}
	}


