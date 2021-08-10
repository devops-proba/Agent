package uns.ac.rs.agent.integration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import uns.ac.rs.agent.domain.Product;
import uns.ac.rs.agent.dto.ProductDTO;
import uns.ac.rs.agent.mapper.ProductMapper;
import uns.ac.rs.agent.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
    private ProductRepository productRepository;
	
	@Test
	public void createProduct() {
		Product prod = new Product(1L, "name", 200.20, 100, "picture");
		ProductDTO prodDTO = ProductMapper.fromEntity(prod);
		when(productRepository.findOneByName(prod.getName())).thenReturn(null);	
		when(productRepository.save(eq(prod))).thenReturn(prod);	
		HttpEntity<ProductDTO> httpEntity =  new HttpEntity<>(prodDTO, new HttpHeaders());;
		ResponseEntity<Product> responseEntity = restTemplate.exchange("/product/create", HttpMethod.POST, httpEntity, Product.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
