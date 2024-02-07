package org.example.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.api.dto.request.Restaurant.CreateRestaurantRequest;
import org.example.api.dto.request.Restaurant.UpdateRestaurantStatusRequest;
import org.example.api.dto.request.UpdateProductRequest;
import org.example.api.dto.response.ProductResponse;
import org.example.api.dto.response.RestaurantResponse;
import org.example.domain.RestaurantDomain;
import org.example.factory.RestaurantDomainFactory;
import org.example.service.RestaurantService;
import org.example.util.ObjectMapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;
    private final ObjectMapperUtil objectMapperUtil;
    private final RestaurantDomainFactory domainFactory;

    @GetMapping
    public List<RestaurantResponse> getAll() {
        return objectMapperUtil.mapAll(service.getAllRestaurants(), RestaurantResponse.class);
    }

    @GetMapping("{restaurantId}")
    public RestaurantResponse get(@PathVariable UUID restaurantId) {
        final var product = service.findById(restaurantId);

        return objectMapperUtil.map(product, RestaurantResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponse create(@RequestBody @Validated CreateRestaurantRequest request) {
        final var restaurant = service.create(
                domainFactory.toCreate(request)
        );

        return objectMapperUtil.map(restaurant, RestaurantResponse.class);
    }

    @PutMapping("{restaurantId}/status")
    public RestaurantResponse update(@PathVariable UUID restaurantId,  @RequestBody @Validated UpdateRestaurantStatusRequest request) {

        final var restaurant = service.updateStatus(
                domainFactory.toUpdate(restaurantId, request)
        );

        return objectMapperUtil.map(restaurant, RestaurantResponse.class);
    }

}
