package org.example.domain;

import lombok.*;
import org.example.persistence.entity.ProductCategory;
import org.example.persistence.entity.Restaurant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private ProductCategory productCategory;
    private Restaurant restaurant;
    private LocalDateTime cratedAt;
}
