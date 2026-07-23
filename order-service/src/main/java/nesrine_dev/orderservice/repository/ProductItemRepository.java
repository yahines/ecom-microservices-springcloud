package nesrine_dev.orderservice.repository;

import nesrine_dev.orderservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}
