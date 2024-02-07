package org.example.domain;

import lombok.*;
import org.example.persistence.entity.Product;
import org.example.persistence.entity.Restaurant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDomain {

    private UUID orderId;
    private Restaurant restaurant;
    private String address;
    private String name;
    private String phoneNumber;
    private String totalValue;
    private Boolean status;
    private LocalDateTime createdAt;
    private Collection<Product> products;
}
