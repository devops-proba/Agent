package uns.ac.rs.shoppingservice.mapper;

import uns.ac.rs.shoppingservice.domain.OrderItem;
import uns.ac.rs.shoppingservice.dto.ItemDTO;

public class ItemMapper extends AbstractMapper {
	
//	public static ProductDTO fromEntity(Product product) {
//		return new ProductDTO(product.getId(),product.getName(), product.getPrice(), product.getQuantity(), new String(product.getPicture(), StandardCharsets.UTF_8));
//	}
//		
	public static OrderItem toEntity(ItemDTO itemDTO) {
		OrderItem item = new OrderItem();
		item.setQuantity(itemDTO.getQuantity());
		item.setTotalPrice(itemDTO.getTotalPrice());
		return item;
	}
}
