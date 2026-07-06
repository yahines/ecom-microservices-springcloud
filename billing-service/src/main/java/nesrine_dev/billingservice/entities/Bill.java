package nesrine_dev.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import nesrine_dev.billingservice.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Bill {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private long customerId;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems = new ArrayList<>();
    //cet attribut existe mais nest pas persistant en bdd
    @Transient
    private Customer customer;
}
