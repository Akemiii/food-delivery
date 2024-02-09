package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.domain.RestaurantDomain;
import org.example.persistence.entity.Restaurant;
import org.example.persistence.repository.RestaurantRepository;
import org.example.util.ObjectMapperUtil;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;
    private final ObjectMapperUtil objectMapperUtil;

    public List<RestaurantDomain> getAllRestaurants() {
        return objectMapperUtil.mapAll(repository.findAll(), RestaurantDomain.class);
    }

    @SneakyThrows
    public RestaurantDomain findById(UUID restaurantId) {
        return repository.findById(restaurantId).map(objectMapperUtil.mapFn(RestaurantDomain.class)).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public RestaurantDomain create(final RestaurantDomain restaurantDomain) {

        final var restaurant = objectMapperUtil.map(restaurantDomain, Restaurant.class);

        return objectMapperUtil.map(repository.save(restaurant), RestaurantDomain.class);
    }

    public RestaurantDomain updateStatus(final RestaurantDomain domain) {
        var restaurantDomain = findById(domain.getRestaurantId());

        restaurantDomain.setStatus(domain.getStatus());

        final var restaurant = repository.save(objectMapperUtil.map(restaurantDomain, Restaurant.class));

        return objectMapperUtil.map(restaurant, RestaurantDomain.class);
    }

    public RestaurantDomain updateDetails(final RestaurantDomain domain) {
        var restaurantDomain = findById(domain.getRestaurantId());

        updateIfNotNull(domain.getName(), restaurantDomain::setName);
        updateIfNotNull(domain.getDescription(), restaurantDomain::setDescription);
        updateIfNotNull(domain.getDelivery_tax(), restaurantDomain::setDelivery_tax);
        updateIfNotNull(domain.getCity(), restaurantDomain::setCity);
        updateIfNotNull(domain.getState(), restaurantDomain::setState);
        updateIfNotNull(domain.getNeighborhood(), restaurantDomain::setNeighborhood);
        updateIfNotNull(domain.getStreet(), restaurantDomain::setStreet);
        updateIfNotNull(domain.getNumber(), restaurantDomain::setNumber);
        updateIfNotNull(domain.getComplement(), restaurantDomain::setComplement);
        updateIfNotNull(domain.getReference(), restaurantDomain::setReference);
        updateIfNotNull(domain.getCategory(), restaurantDomain::setCategory);

        final var restaurant = repository.save(objectMapperUtil.map(restaurantDomain, Restaurant.class));

        return objectMapperUtil.map(restaurant, RestaurantDomain.class);
    }

    private <T> void updateIfNotNull(T newValue, Consumer<T> setter){
        if(nonNull(newValue))
            setter.accept(newValue);
    }

}
