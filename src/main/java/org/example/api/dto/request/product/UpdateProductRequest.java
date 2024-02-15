package org.example.api.dto.request.product;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.CategoryDomain;
import org.example.domain.ChoiceDomain;
import org.example.util.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class UpdateProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private CategoryDomain category;
    private boolean needChoices;
    private ProductStatus status;
    private List<ChoiceDomain> choices;
}
