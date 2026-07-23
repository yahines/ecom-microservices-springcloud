package nesrine_dev.orderservice.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder @ToString
public class Product {
    private String id;
    private String name;
    private double price;
    public int quantity;
}
