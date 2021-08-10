package uns.ac.rs.agent.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.agent.domain.Product;
import uns.ac.rs.agent.dto.ProductDTO;
import uns.ac.rs.agent.exception.InvalidDataException;
import uns.ac.rs.agent.mapper.ProductMapper;
import uns.ac.rs.agent.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductDTO> getAllProducts() {
		System.out.println("get all products");
		return ProductMapper.fromEntityList(productRepository.findAll(), ProductMapper::fromEntity);
	}

	public Product  createProduct(ProductDTO productDTO) throws InvalidDataException {
		if (productDTO.getName().isEmpty()) {
			throw new InvalidDataException("Name must not be empty!");
		} else if(Stream.of(productDTO.getName(), productDTO.getPrice(), productDTO.getQuantity(), productDTO.getPicture()).anyMatch(Objects::isNull)) {
			throw new InvalidDataException("Some data is missing");
		}
		
		String name = productDTO.getName();
		System.out.println(name);
		Product prod = productRepository.findOneByName(name).orElse(null);
		if (prod != null) {
			System.out.println("exception");
			throw new InvalidDataException("The specified name is already taken!");
		}
		Product saveProd = ProductMapper.toEntity(productDTO);

		return productRepository.save(saveProd);
	}

}
