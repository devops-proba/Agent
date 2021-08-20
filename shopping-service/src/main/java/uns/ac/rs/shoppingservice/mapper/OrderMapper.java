package uns.ac.rs.shoppingservice.mapper;

import uns.ac.rs.shoppingservice.domain.Order;
import uns.ac.rs.shoppingservice.dto.OrderDTO;


public class OrderMapper extends AbstractMapper {
	
//	public static ProductDTO fromEntity(Product product) {
//		return new ProductDTO(product.getId(),product.getName(), product.getPrice(), product.getQuantity(), new String(product.getPicture(), StandardCharsets.UTF_8));
//	}
//		
	public static Order toEntity(OrderDTO orderDTO) {
		Order order = new Order();
		order.setAdderss(orderDTO.getAddress());
		order.setFirstName(order.getFirstName());
		order.setLastName(order.getLastName());
		order.setTotalPrice(orderDTO.getTotalPrice());
		return order;
		
	}
	
//	public static List<ProductDTO> fromEntityList(List<Product> products) {
//		List<ProductDTO> productDTOs = new ArrayList<>();
//		for(Product product:products) {
//			productDTOs.add(new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getQuantity(), new String(product.getPicture(), StandardCharsets.UTF_8)));
//		}
//		return productDTOs;
//	}
}