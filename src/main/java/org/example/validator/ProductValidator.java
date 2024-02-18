package org.example.validator;

import lombok.AllArgsConstructor;
import org.example.api.dto.request.CreateAdditionalItemRequest;
import org.example.api.dto.request.CreateChoiceRequest;
import org.example.api.dto.request.product.UpdateProductRequest;
import org.example.domain.ProductDomain;
import org.example.persistence.entity.AdditionalItems;
import org.example.persistence.entity.Category;
import org.example.persistence.entity.Choice;
import org.example.persistence.entity.Product;
import org.example.util.ObjectMapperUtil;
import org.example.util.ProductStatus;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class ProductValidator {

    private final ObjectMapperUtil objectMapperUtil;

    public void CheckNegativePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("O valor do produto não pode ser negativo");
    }

    public void CheckValidBody(ProductDomain domain) {
        if (!nonNull(domain.getName()) && !nonNull(domain.getDescription()) && !nonNull(domain.getImage()) && !nonNull(domain.getChoices()))
            throw new IllegalArgumentException("O body não pode ser vazio");
    }

    public ProductDomain updateProductDomain(ProductDomain domain, ProductDomain updateDomain) {
        if (nonNull(updateDomain.getName()) && !updateDomain.getName().isEmpty())
            domain.setName(updateDomain.getName());

        if (nonNull(updateDomain.getDescription()) && !updateDomain.getDescription().isEmpty())
            domain.setDescription(updateDomain.getDescription());

        if (nonNull(updateDomain.getPrice())) {
            CheckNegativePrice(updateDomain.getPrice());
            domain.setPrice(updateDomain.getPrice());
        }

        if (nonNull(updateDomain.getCategory())) domain.setCategory(updateDomain.getCategory());

        if (nonNull(updateDomain.getStatus())) domain.setStatus(updateDomain.getStatus());

        if (nonNull(updateDomain.getChoices())) domain.setChoices(updateDomain.getChoices());


        domain.setNeedChoices(updateDomain.isNeedChoices());

        return domain;
    }
}
