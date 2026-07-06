package nesrine_dev.billingservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String id;
    private String name;
    private double price;
    public int quantity;
}
