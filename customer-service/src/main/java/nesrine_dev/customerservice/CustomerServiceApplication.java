package nesrine_dev.customerservice;

import nesrine_dev.customerservice.Repository.CustomerRepository;
import nesrine_dev.customerservice.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder().name("adam").email("adam@gmail.com").build());
            customerRepository.save(Customer.builder().name("assil").email("assil@gmail.com").build());
            customerRepository.save(Customer.builder().name("yacine").email("yacine@gmail.com").build());
            customerRepository.findAll().forEach(c -> {
                System.out.println("=======================");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
            });
        };
    }
}
