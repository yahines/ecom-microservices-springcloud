package nesrine_dev.inventoryservice;

import nesrine_dev.inventoryservice.entities.Product;
import nesrine_dev.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                .id(UUID.randomUUID().toString())
                .name("Computer")
                .price(3500)
                .quantity(11)
                .build());
            productRepository.save(Product.builder()
                .id(UUID.randomUUID().toString())
                .name("Printer")
                .price(1800)
                .quantity(10)
                .build());
            productRepository.save(Product.builder()
                .id(UUID.randomUUID().toString())
                .name("Smartphone")
                .price(4500)
                .quantity(81)
                .build());
            productRepository.findAll().forEach(System.out::println);
        };
    }

}
