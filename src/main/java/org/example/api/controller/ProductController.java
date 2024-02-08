package org.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.request.product.CreateProductRequest;
import org.example.api.dto.request.product.UpdateProductImageRequest;
import org.example.api.dto.request.product.UpdateProductRequest;
import org.example.api.dto.response.product.ProductResponse;
import org.example.factory.ProductDomainFactory;
import org.example.service.ProductService;
import org.example.util.ObjectMapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ObjectMapperUtil objectMapperUtil;
    private final ProductDomainFactory productDomainFactory;

    @GetMapping
    public List<ProductResponse> getAll() {
        return objectMapperUtil.mapAll(service.getAllProducts(), ProductResponse.class);
    }

    @GetMapping("{productId}")
    public ProductResponse get(@PathVariable UUID productId) {
        final var product = service.findById(productId);

        return objectMapperUtil.map(product, ProductResponse.class);
    }

    @GetMapping("category/{categoryId}")
    public List<ProductResponse> getByCategory(@PathVariable UUID categoryId){

    }


    @PostMapping("restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Validated CreateProductRequest request) {
        final var product = service.create(
                productDomainFactory.toCreate(request)
        );

        return objectMapperUtil.map(product, ProductResponse.class);
    }

    @PutMapping("{productId}/details")
    public ProductResponse updateDetails(@PathVariable UUID productId, UpdateProductRequest request) {
        final var product = service.update(
                productDomainFactory.toUpdateDetails(productId, request)
        );

        return objectMapperUtil.map(product, ProductResponse.class);
    }

    @PutMapping("{productId}/image")
    public ProductResponse updateImage(@PathVariable UUID productId, UpdateProductImageRequest request) {
        final var product = service.updateImage(
                productDomainFactory.toUpdateImage(productId, request)
        );

        return objectMapperUtil.map(product, ProductResponse.class);
    }


    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID productId) {
        service.delete(productId);
    }

}
