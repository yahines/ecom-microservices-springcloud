package nesrine_dev.orderservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter  @ToString @Builder
public class Order {
    @Id
    private String id;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
}
