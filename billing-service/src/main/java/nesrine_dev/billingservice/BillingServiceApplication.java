package nesrine_dev.billingservice;

import nesrine_dev.billingservice.entities.Bill;
import nesrine_dev.billingservice.entities.ProductItem;
import nesrine_dev.billingservice.feign.CustomerRestClient;
import nesrine_dev.billingservice.feign.ProductRestClient;
import nesrine_dev.billingservice.model.Customer;
import nesrine_dev.billingservice.model.Product;
import nesrine_dev.billingservice.repository.BillRepository;
import nesrine_dev.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BillRepository billRepository,
                           ProductItemRepository productItemRepository,
                           CustomerRestClient customerRestClient,
                           ProductRestClient productRestClient) {

        Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
        Collection<Product> products = productRestClient.getAllProducts().getContent();

        return args -> {
            customers.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .customerId(customer.getId())
                        .build();
                billRepository.save(bill);

                products.forEach(product -> {
                    ProductItem productItem = ProductItem.builder()
                            .bill(bill)
                            .productId(product.getId())
                            .quantity(1+new Random().nextInt(10))
                            .unitPrice(product.getPrice())
                            .build();
                    productItemRepository.save(productItem);
                });
            });
            billRepository.findAll().forEach(bill -> {
                System.out.println("=======================");
                System.out.println(bill.getId());
                System.out.println(bill.getCustomerId());
                System.out.println(bill.getBillingDate());
            });
        };
    }

}
