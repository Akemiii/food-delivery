package org.example.api.dto.response.product;

import lombok.*;
import org.example.api.dto.response.choice.ChoiceResponse;
import org.example.domain.CategoryDomain;
import org.example.util.ProductStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private UUID productId;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private boolean needChoices;
    private CategoryDomain category;
    private ProductStatus status;
    private List<ChoiceResponse> choices;

}
