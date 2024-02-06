package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name="orders_products")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class order_products {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "orders_products_id")
    private UUID orderProductsId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private String comment;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
