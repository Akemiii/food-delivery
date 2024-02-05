package org.example.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDomain {
    private Long productId;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private Integer product_category_id;
    private Integer restaurant_id;
}
