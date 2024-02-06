package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name="order_products")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class order_products {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_products_id")
    private UUID orderProductsId;
    @JoinColumn(name = "order_id")
    private UUID orderId;
    @JoinColumn(name = "product_id")
    private UUID product_id;
    private Integer quantity;
    private String comment;
}
