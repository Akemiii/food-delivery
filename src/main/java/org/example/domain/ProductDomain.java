package org.example.domain;

import lombok.*;
import org.example.api.dto.response.choice.ChoiceResponse;
import org.example.persistence.entity.Category;
import org.example.persistence.entity.Choice;
import org.example.persistence.entity.Restaurant;
import org.example.util.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private Restaurant restaurant;
    private LocalDateTime cratedAt;
    private boolean needChoices;
    private ProductStatus status;
    List<Choice> choices;
}
