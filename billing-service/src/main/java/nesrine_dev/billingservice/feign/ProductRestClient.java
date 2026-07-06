package nesrine_dev.billingservice.feign;

import nesrine_dev.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("inventory-service")
public interface ProductRestClient {
    @GetMapping("/api/products/{id}")
    Product findProductById(@PathVariable String id);

    @GetMapping("/api/products")
    PagedModel<Product> getAllProducts();
}
