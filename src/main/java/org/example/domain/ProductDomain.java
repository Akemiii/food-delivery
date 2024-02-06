package org.example.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDomain {
    private UUID productId;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private Integer product_category_id;
    private Integer restaurant_id;
}
