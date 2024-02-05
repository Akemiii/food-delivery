package org.example.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    public void CheckNegativePrice(BigDecimal price){
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("O valor do produto não pode ser negativo");
    }

}
