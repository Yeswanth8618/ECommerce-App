package or.jsp.SpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import or.jsp.SpringBoot.dto.ResponseStructure;
import or.jsp.SpringBoot.model.Product;
import or.jsp.SpringBoot.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductService productservice;
	@PostMapping( "/{merchant_id}")
	public ResponseEntity<ResponseStructure<Product>> saveproduct(@RequestBody Product product,@PathVariable int merchant_id) {
		return productservice.saveProduct(product, merchant_id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure> updatemerchant(@RequestBody Product product) {
		return productservice.updateproduct(product);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> findbyid(@PathVariable(name = "id") int id) {
		return productservice.findbyid(id);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure> findall() {
		return productservice.findall();
	}
	@GetMapping("/{merchant_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int merchant_id) {
		return productservice.findProductsByMerchantId(merchant_id);
	}
	@GetMapping("/find-by-category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable String category) {
		return productservice.findbycategory(category);
	}
	@GetMapping("/find-by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable String brand) {
		return productservice.findbybrand(brand);
	}

}
