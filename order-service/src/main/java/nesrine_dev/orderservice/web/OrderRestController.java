package nesrine_dev.orderservice.web;

import nesrine_dev.orderservice.entities.Order;
import nesrine_dev.orderservice.repository.OrderRepository;
import nesrine_dev.orderservice.restClients.ProductRestClient;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nesrine
 * controller exposing services only in molithiq app
 **/
@RestController
@RequestMapping("/api")
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductRestClient productRestClient;

    public OrderRestController(OrderRepository orderRepository, ProductRestClient productRestClient) {
        this.orderRepository = orderRepository;
        this.productRestClient = productRestClient;
    }

    @GetMapping("/orders")
    public List<Order> findAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(o -> {
            o.getProductItems().forEach(item -> {
                item.setProduct(productRestClient.findProductById(item.getProductId()));
            });
        });
        return allOrders;
    }

    @GetMapping("/orders/{id}")
    public Order findOrderById(@PathVariable String id) {
        Order order = orderRepository.findById(id).get();
        order.getProductItems().forEach(pi -> {
            pi.setProduct(productRestClient.findProductById(pi.getProductId()));
        });
        return order;
    }
}
