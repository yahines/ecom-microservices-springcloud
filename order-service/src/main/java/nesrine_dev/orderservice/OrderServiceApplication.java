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
import java.util.ArrayList;
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
//            List<Product> allProducts;
//            try {
//                allProducts = productRestClient.getAllByProducts();
//            } catch (Exception e) {
//                System.err.println("[order-service] Could not seed data – inventory-mono unavailable: " + e.getMessage());
//                return;
//            }
            List<String> productIds = List.of("P01","P02","P03");
            for (int i=0; i<5; i++) {
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDateTime.now())
                        .state(OrderState.PENDING)
                        .build();
                Order savedOrder = orderRepository.save(order);

                productIds.forEach(pi->{
                    ProductItem productItem = ProductItem.builder()
                            .productId(pi)
                            .quantity(new Random().nextInt(20))
                            .price(Math.random()*6000+100)
                            .order(savedOrder)
                            .build();
                    productItemRepository.save(productItem);
                });
            }
        };
    }

}
