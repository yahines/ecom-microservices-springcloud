package nesrine_dev.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import nesrine_dev.billingservice.model.Product;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private int quantity;
    private double unitPrice;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill;
    @Transient
    private Product product;
}
