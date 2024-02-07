package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.api.dto.request.Restaurant.CreateRestaurantRequest;
import org.example.api.dto.response.RestaurantResponse;
import org.example.domain.ProductDomain;
import org.example.persistence.entity.Product;
import org.example.persistence.entity.Restaurant;
import org.example.persistence.repository.RestaurantRepository;
import org.example.util.ObjectMapperUtil;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;
    private final ObjectMapperUtil objectMapperUtil;

    public List<RestaurantResponse> getAllRestaurants() {
        return objectMapperUtil.mapAll(
                repository.findAll(), RestaurantResponse.class
        );
    }

    @SneakyThrows
    public RestaurantResponse findById(UUID restaurantId) {
        return repository.findById(restaurantId)
                .map(objectMapperUtil.mapFn(RestaurantResponse.class))
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public RestaurantResponse create(final CreateRestaurantRequest createRestaurantRequest) {
        final var restaurant = objectMapperUtil.map(createRestaurantRequest, Restaurant.class);

        return objectMapperUtil.map(
                repository.save(restaurant), RestaurantResponse.class
        );
    }

}
