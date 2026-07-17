package nesrine_dev.inventorymono.repository;

import nesrine_dev.inventorymono.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
