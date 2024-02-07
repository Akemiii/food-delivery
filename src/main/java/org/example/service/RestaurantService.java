package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.api.dto.request.Restaurant.CreateRestaurantRequest;
import org.example.api.dto.response.RestaurantResponse;
import org.example.domain.ProductDomain;
import org.example.domain.RestaurantDomain;
import org.example.persistence.entity.Product;
import org.example.persistence.entity.Restaurant;
import org.example.persistence.repository.RestaurantRepository;
import org.example.util.ObjectMapperUtil;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;
    private final ObjectMapperUtil objectMapperUtil;

    public List<RestaurantDomain> getAllRestaurants() {
        return objectMapperUtil.mapAll(
                repository.findAll(), RestaurantDomain.class
        );
    }

    @SneakyThrows
    public RestaurantDomain findById(UUID restaurantId) {
        return repository.findById(restaurantId)
                .map(objectMapperUtil.mapFn(RestaurantDomain.class))
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public RestaurantDomain create(final RestaurantDomain restaurantDomain) {

        final var restaurant = objectMapperUtil.map(restaurantDomain, Restaurant.class);

        return objectMapperUtil.map(
                repository.save(restaurant), RestaurantDomain.class
        );
    }

    public RestaurantDomain updateStatus(final RestaurantDomain domain) {
        var restaurantDomain = findById(domain.getRestaurantId());

        if(!domain.getStatus().equals("OPEN") || !domain.getStatus().equals("CLOSED"))
            throw new IllegalArgumentException("O status fornecido é inválido. Use OPEN ou CLOSED.");

        restaurantDomain.setStatus(domain.getStatus());

        final var restaurant = repository.save(objectMapperUtil.map(restaurantDomain, Restaurant.class));

        return objectMapperUtil.map(restaurant, RestaurantDomain.class);
    }

}
