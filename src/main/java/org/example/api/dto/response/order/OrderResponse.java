package org.example.api.dto.response.order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.api.dto.response.product.ProductResponse;
import org.example.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private UUID orderId;
    private String address;
    private String name;
    private String phoneNumber;
    private String totalValue;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private List<ProductResponse> products;
}
