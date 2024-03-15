package or.jsp.SpringBoot.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import or.jsp.SpringBoot.dto.ResponseStructure;

public class SpringBootExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(InvalidCredentialsException.class)
public ResponseEntity<ResponseStructure<String>> handleICE(InvalidCredentialsException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setBody("The given credentials are not correct check");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
public ResponseEntity<ResponseStructure<String>> handleICE(IdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setBody("cannot find merchant");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(UserNotFoundException exception){
			ResponseStructure<String> structure=new ResponseStructure<>();
			structure.setBody("User not found");
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(MerchantNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(MerchantNotFoundException exception){
			ResponseStructure<String> structure=new ResponseStructure<>();
			structure.setBody("Merchant not found");
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(NullPointerException exception){
			ResponseStructure<String> structure=new ResponseStructure<>();
			structure.setBody("The table is empty");
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(ProductNotFoundException exception){
			ResponseStructure<String> structure=new ResponseStructure<>();
			structure.setBody("Product not found");
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
}
