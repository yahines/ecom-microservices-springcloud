package nesrine_dev.inventorymono.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder @ToString
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    public int quantity;
}
