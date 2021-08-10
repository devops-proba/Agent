package uns.ac.rs.agent.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import uns.ac.rs.agent.domain.Product;
import uns.ac.rs.agent.dto.ProductDTO;
import uns.ac.rs.agent.exception.InvalidDataException;
import uns.ac.rs.agent.mapper.ProductMapper;
import uns.ac.rs.agent.repository.ProductRepository;
import uns.ac.rs.agent.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIT {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private ProductService productService;
	
	@MockBean
    private ProductRepository productRepository;
	
	@Test
	public void createProduct() throws InvalidDataException {
		ProductDTO prodDTO = new ProductDTO("name", 200.20, 100, "picture");
		Product prod = ProductMapper.toEntity(prodDTO);
		when(productRepository.findOneByName(prodDTO.getName())).thenReturn(Optional.empty());	
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(prod);	
		assertEquals(prod, productService.createProduct(prodDTO));
//		HttpEntity<ProductDTO> httpEntity =  new HttpEntity<>(prodDTO, new HttpHeaders());;
//		ResponseEntity<Product> responseEntity = restTemplate.exchange("/product/create", HttpMethod.POST, httpEntity, Product.class);
//		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
