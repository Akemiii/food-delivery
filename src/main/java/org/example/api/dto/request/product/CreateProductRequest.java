package org.example.api.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.CategoryDomain;
import org.example.domain.ChoiceDomain;
import org.example.persistence.entity.Category;
import org.example.util.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CreateProductRequest {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
    private String image;
    private CategoryDomain category;
    private ProductStatus status;
    private boolean needChoices;
    private List<ChoiceDomain> choices;
}
