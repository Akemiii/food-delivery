package org.example.api.dto.request.product;

import lombok.Getter;
import lombok.Setter;
import org.example.persistence.entity.Category;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
}
