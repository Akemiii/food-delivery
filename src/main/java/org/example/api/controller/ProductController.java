package org.example.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.api.dto.request.product.CreateProductRequest;
import org.example.api.dto.request.product.UpdateProductImageRequest;
import org.example.api.dto.request.product.UpdateProductRequest;
import org.example.api.dto.request.product.UpdateProductStatusRequest;
import org.example.api.dto.response.product.ProductImageResponse;
import org.example.api.dto.response.product.ProductResponse;
import org.example.api.dto.response.product.ProductStatusResponse;
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


    @Operation(summary = "Get a list of products")
    @GetMapping
    public List<ProductResponse> getAll() {
        return objectMapperUtil.mapAll(service.getAllProducts(), ProductResponse.class);
    }

    @Operation(summary = "Get a product by its id")
    @GetMapping("{productId}")
    public ProductResponse get(@PathVariable UUID productId) {
        return objectMapperUtil.map(service.findById(productId), ProductResponse.class);
    }


    @Operation(summary = "Get a list of products by categoryId")
    @GetMapping("category/{categoryId}")
    public List<ProductResponse> getByCategory(@PathVariable UUID categoryId) {
        return objectMapperUtil.mapAll(service.getAllProductsByCategoryId(categoryId), ProductResponse.class);
    }

    @Operation(summary = "Get a list of products by products name")
    @GetMapping("search/{name}")
    public List<ProductResponse> getAllByProductName(@PathVariable String name) {
        return objectMapperUtil.mapAll(service.getAllByProductByName(name), ProductResponse.class);
    }

    @Operation(summary = "Get a list of products by restaurants name")
    @GetMapping("restaurant/search/{restaurantName}")
    public List<ProductResponse> getAllByRestaurantName(@PathVariable String restaurantName) {
        return objectMapperUtil.mapAll(service.getAllByRestaurantByName(restaurantName), ProductResponse.class);
    }

    @Operation(summary = "Get a list of products by restaurants id")
    @GetMapping("restaurant/{restaurantId}")
    public List<ProductResponse> getAllByRestaurant(@PathVariable UUID restaurantId) {
        return objectMapperUtil.mapAll(service.getAllProductsByRestaurantId(restaurantId), ProductResponse.class);
    }

    @Operation(summary = "Get a list of products by category title")
    @GetMapping("category/search/{categoryTitle}")
    public List<ProductResponse> getAllByCategoryTitle(@PathVariable String categoryTitle) {
        return objectMapperUtil.mapAll(service.getAllByCategoryByTitle(categoryTitle), ProductResponse.class);
    }

    @Operation(summary = "Create a product to specific restaurant by restaurants id")
    @PostMapping("restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@PathVariable UUID restaurantId, @RequestBody @Validated CreateProductRequest request) {
        final var product = service.create(restaurantId, productDomainFactory.toCreate(request));

        return objectMapperUtil.map(product, ProductResponse.class);
    }

    @Operation(summary = "Update products details by its id")
    @PutMapping("{productId}/details")
    public ProductResponse updateDetails(@PathVariable UUID productId, @RequestBody @Validated UpdateProductRequest request) {
        final var product = service.update(productDomainFactory.toUpdateDetails(productId, request));

        return objectMapperUtil.map(product, ProductResponse.class);
    }

    @Operation(summary = "update product image by its id")
    @PutMapping("{productId}/image")
    public ProductImageResponse updateImage(@PathVariable UUID productId, @RequestBody @Validated UpdateProductImageRequest request) {
        final var product = service.updateImage(productDomainFactory.toUpdateImage(productId, request));

        return objectMapperUtil.map(product, ProductImageResponse.class);
    }

    @Operation(summary = "update product status by its id")
    @PutMapping("{productId}/status")
    public ProductStatusResponse updateStatus(@PathVariable UUID productId, @RequestBody @Validated UpdateProductStatusRequest request) {
        final var product = service.updateStatus(productDomainFactory.toUpdateStatus(productId, request));

        return objectMapperUtil.map(product, ProductStatusResponse.class);
    }


    @Operation(summary = "delete product by its id")
    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID productId) {
        service.delete(productId);
    }

}
