package uns.ac.rs.agent.mapper;

import uns.ac.rs.agent.domain.Product;
import uns.ac.rs.agent.dto.ProductDTO;

public class ProductMapper extends AbstractMapper {
	
	public static ProductDTO fromEntity(Product product) {
		return new ProductDTO(product.getName(), product.getPrice(), product.getQuantity(), product.getPicture());
	}
	
	public static Product toEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setPicture(productDTO.getPicture());
		return product;	
		
	}
	
	
}
