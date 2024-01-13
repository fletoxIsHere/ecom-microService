package org.sid.orderservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.enums.OrderStatus;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "orders")
@Data @NoArgsConstructor @AllArgsConstructors
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Date createdAt;
    private OrderStatus status;

    private Long customerId;

    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;

}
