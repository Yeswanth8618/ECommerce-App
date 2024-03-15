package or.jsp.SpringBoot.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name,brand,category,description;
	@Column(nullable = false)
	private double cost;
	@Column(nullable = false)
	private String image_url;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="merchant_id")
	private Merchant merchant;
}
