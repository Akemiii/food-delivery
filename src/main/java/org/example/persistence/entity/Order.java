package org.example.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name="category")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID orderId;
    @JoinColumn(name = "restaurant_id")
    private UUID restaurantId;
    private String address;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "total_value")
    private String totalValue;
    private Boolean status;
}
