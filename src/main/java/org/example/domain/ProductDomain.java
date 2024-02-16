package org.example.domain;

import lombok.*;
import org.example.util.ProductStatus;
import java.math.BigDecimal;
import java.util.List;
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
    private CategoryDomain category;
    private RestaurantDomain restaurant;
    private boolean needChoices;
    private ProductStatus status;
    private List<ChoiceDomain> choices;
}
