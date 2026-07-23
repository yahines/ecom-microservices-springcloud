package nesrine_dev.orderservice;

import nesrine_dev.orderservice.entities.Order;
import nesrine_dev.orderservice.entities.OrderState;
import nesrine_dev.orderservice.entities.ProductItem;
import nesrine_dev.orderservice.model.Product;
import nesrine_dev.orderservice.repository.OrderRepository;
import nesrine_dev.orderservice.repository.ProductItemRepository;
import nesrine_dev.orderservice.restClients.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(OrderRepository orderRepository,
                           ProductItemRepository productItemRepository,
                           ProductRestClient productRestClient
                           ) {
        return args -> {
            List<Product> allProducts;
            try {
                allProducts = productRestClient.getAllByProducts();
            } catch (Exception e) {
                System.err.println("[order-service] Could not seed data – inventory-mono unavailable: " + e.getMessage());
                return;
            }
            for (int i=0; i<5; i++) {
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDateTime.now())
                        .state(OrderState.PENDING)
                        .build();
                Order savedOrder = orderRepository.save(order);

                allProducts.forEach(p->{
                    ProductItem productItem = ProductItem.builder()
                            .productId(p.getId())
                            .quantity(new Random().nextInt(20))
                            .price(p.getPrice())
                            .order(savedOrder)
                            .build();
                    productItemRepository.save(productItem);
                });
            }
        };
    }

}
