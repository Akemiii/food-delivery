package org.example.validator;

import lombok.RequiredArgsConstructor;
import org.example.domain.ProductDomain;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    public void CheckNegativePrice(BigDecimal price){
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("O valor do produto não pode ser negativo");
    }

    public void CheckValidBody(ProductDomain domain){
        if(!nonNull(domain.getName()) && !nonNull(domain.getDescription()) && !nonNull(domain.getImage()))
            throw new IllegalArgumentException("O body não pode ser vazio");

        if(domain.getName().isEmpty() && domain.getImage().isEmpty() && domain.getDescription().isEmpty())
            throw new IllegalArgumentException("O body não pode ser vazio");
    }

    public ProductDomain updateProductDomain(ProductDomain domain, ProductDomain updateDomain){
        if (nonNull(updateDomain.getName()) && !updateDomain.getName().isEmpty())
            domain.setName(updateDomain.getName());

        if (nonNull(updateDomain.getDescription()) && !updateDomain.getDescription().isEmpty())
            domain.setDescription(updateDomain.getDescription());

        if (nonNull(updateDomain.getPrice())){
            CheckNegativePrice(updateDomain.getPrice());
            domain.setPrice(updateDomain.getPrice());
        }
        return  domain;
    }

}
