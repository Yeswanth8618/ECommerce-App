package or.jsp.SpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import or.jsp.SpringBoot.Exception.IdNotFoundException;
import or.jsp.SpringBoot.Exception.MerchantNotFoundException;
import or.jsp.SpringBoot.Exception.NullPointerException;
import or.jsp.SpringBoot.Exception.ProductNotFoundException;
import or.jsp.SpringBoot.dao.MerchantDao;
import or.jsp.SpringBoot.dao.ProductDao;
import or.jsp.SpringBoot.dto.ResponseStructure;
import or.jsp.SpringBoot.model.Merchant;
import or.jsp.SpringBoot.model.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		Optional<Merchant> recMechant = merchantDao.findbyid(merchant_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recMechant.isPresent()) {
			Merchant merchant = recMechant.get();
			merchant.getProduct().add(product);
			product.setMerchant(merchant);
			structure.setBody(productDao.saveProduct(product));
			structure.setMessage("Product Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new MerchantNotFoundException("cannot add Product as Merchant Id is Invalid");
	}
	public ResponseEntity<ResponseStructure> updateproduct(Product product) {
		Optional<Product> recMerchant = productDao.findById(product.getId());
		ResponseStructure<Product> structure = new ResponseStructure();
		if (recMerchant.isPresent()) {
			Product dbMerchant = recMerchant.get();
			dbMerchant.setBrand(product.getBrand());
			dbMerchant.setCategory(product.getCategory());
			dbMerchant.setCost(product.getCost());
			dbMerchant.setDescription(product.getDescription());
			dbMerchant.setImage_url(product.getImage_url());
			structure.setMessage("Product updated");
			structure.setBody(productDao.updateProduct(product));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByMerchantId(int merchant_id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByMerchantId(merchant_id);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products Found for entered Merchant Id");
		}
		structure.setBody(products);
		structure.setMessage("List of Products for Merchant Id");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Product>>findbyid(int id){ 
		Optional<Product>recProduct=productDao.findById(id);
		ResponseStructure<Product>structure=new ResponseStructure<>();
		if(recProduct.isPresent()) {
			structure.setBody(recProduct.get());
			structure.setMessage("product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("product id not found");
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findbybrand(String brand) {
		ResponseStructure<List<Product>> structure = new ResponseStructure();
		List<Product> recMerchant = productDao.findByBrand(brand);
		if (recMerchant.size() > 0) {
			structure.setMessage("Product found");
			structure.setBody(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException(brand);

	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findbycategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure();
		List<Product> recMerchant = productDao.findByCategory(category);
		if (recMerchant.size() > 0) {
			structure.setMessage("Product found");
			structure.setBody(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException(category);

	}
	public ResponseEntity<ResponseStructure> findall() {
		ResponseStructure<List<Product>> structure = new ResponseStructure();
		List<Product> recMerchant=productDao.findAll();

		if (recMerchant.size() > 0) {
			structure.setMessage("Products found");
			structure.setBody(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK);
		}
		throw new NullPointerException(null);
	}


}
