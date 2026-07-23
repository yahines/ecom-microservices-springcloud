package nesrine_dev.orderservice.restClients;

import nesrine_dev.orderservice.entities.ProductItem;
import nesrine_dev.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8082", name="inventory-mono")
public interface ProductRestClient {

    @GetMapping("monoApi/products")
    List<Product> getAllByProducts();

    @GetMapping("monoApi/products/{id}")
    Product findProductById(@PathVariable String id);

}
