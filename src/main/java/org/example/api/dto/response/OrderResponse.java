package org.example.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.persistence.entity.Product;
import org.example.persistence.entity.Restaurant;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private UUID orderId;
    private Restaurant restaurant;
    private String address;
    private String name;
    private String phoneNumber;
    private String totalValue;
    private Boolean status;
    private LocalDateTime createdAt;
    private List<Product> products;
}
