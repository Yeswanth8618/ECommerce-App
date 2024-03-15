package or.jsp.SpringBoot.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import or.jsp.SpringBoot.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByBrand(String brand);
	
	@Query("select p from Product p where p.merchant.id=?1")
	public List<Product> findByMerchantId(int merchant_id);
	
	public  List<Product>findByCategory(String category);
}
