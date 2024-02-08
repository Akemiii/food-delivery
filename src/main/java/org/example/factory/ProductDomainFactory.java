package org.example.factory;

import org.example.api.dto.request.product.CreateProductRequest;
import org.example.api.dto.request.product.UpdateProductRequest;
import org.example.domain.ProductDomain;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductDomainFactory {

    public ProductDomain toCreate(final CreateProductRequest request) {
        return ProductDomain.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .build();
    }

    public ProductDomain toUpdate(final UUID productId, final UpdateProductRequest request){
        return ProductDomain.builder()
                .productId(productId)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .build();
    }
}
