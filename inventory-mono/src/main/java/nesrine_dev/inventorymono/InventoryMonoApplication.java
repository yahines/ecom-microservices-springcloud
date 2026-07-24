package nesrine_dev.inventorymono;

import nesrine_dev.inventorymono.entities.Product;
import nesrine_dev.inventorymono.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryMonoApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryMonoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .id("P01")
//                    .id(UUID.randomUUID().toString())
                    .name("Computer")
                    .price(3500)
                    .quantity(11)
                    .build());
            productRepository.save(Product.builder()
                    .id("P02")
//                    .id(UUID.randomUUID().toString())
                    .name("Printer")
                    .price(1800)
                    .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .id("P03")
//                    .id(UUID.randomUUID().toString())
                    .name("Smartphone")
                    .price(4500)
                    .quantity(81)
                    .build());
            productRepository.findAll().forEach(System.out::println);
        };
    }

}
