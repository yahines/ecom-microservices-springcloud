package nesrine_dev.inventorymono.web;

import nesrine_dev.inventorymono.entities.Product;
import nesrine_dev.inventorymono.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author Nesrine
 * controller exposing services only in molithiq app
 **/
@RestController
@RequestMapping("/monoApi")
public class ProductRestController {
    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/products/{id}")
    public Product findById(@PathVariable String id) {
        return productRepository.findById(id).get();
    }

    @GetMapping("/auth")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }
}
