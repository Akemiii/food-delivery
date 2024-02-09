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

        if (nonNull(domain.getName()) && !domain.getName().isEmpty()) restaurantDomain.setName(domain.getName());

        if (nonNull(domain.getDescription()) && !domain.getDescription().isEmpty())
            restaurantDomain.setDescription(domain.getDescription());

        if (nonNull(domain.getDelivery_tax())) restaurantDomain.setDelivery_tax(domain.getDelivery_tax());

        if (nonNull(domain.getCity()) && !domain.getCity().isEmpty())
            restaurantDomain.setCity(domain.getCity());

        if (nonNull(domain.getState()) && !domain.getState().isEmpty())
            restaurantDomain.setState(domain.getState());

        if (nonNull(domain.getNeighborhood()) && !domain.getNeighborhood().isEmpty())
            restaurantDomain.setNeighborhood(domain.getNeighborhood());

        if (nonNull(domain.getStreet()) && !domain.getStreet().isEmpty())
            restaurantDomain.setStreet(domain.getStreet());

        if (nonNull(domain.getNumber()) && !domain.getNumber().isEmpty())
            restaurantDomain.setNumber(domain.getNumber());

        if (nonNull(domain.getComplement()) && !domain.getComplement().isEmpty())
            restaurantDomain.setComplement(domain.getComplement());

        if (nonNull(domain.getReference()) && !domain.getReference().isEmpty())
            restaurantDomain.setReference(domain.getReference());

        if (nonNull(domain.getCategory()))
            restaurantDomain.setCategory(domain.getCategory());

        final var restaurant = repository.save(objectMapperUtil.map(restaurantDomain, Restaurant.class));

        return objectMapperUtil.map(restaurant, RestaurantDomain.class);
    }

    private <T> void updateIfNotNull(T newValue, Consumer<T> setter){

    }

}
