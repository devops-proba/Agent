package uns.ac.rs.agent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uns.ac.rs.agent.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findOneByName(String name);
}
