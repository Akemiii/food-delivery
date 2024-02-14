package org.example.factory;

import org.example.api.dto.request.Restaurant.CreateCatalogRequest;
import org.example.api.dto.request.Restaurant.CreateRestaurantRequest;
import org.example.api.dto.request.Restaurant.UpdateRestaurantRequest;
import org.example.api.dto.request.Restaurant.UpdateRestaurantStatusRequest;
import org.example.domain.CatalogMenuDomain;
import org.example.domain.RestaurantDomain;
import org.example.util.RestaurantStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RestaurantDomainFactory {
    public RestaurantDomain toCreate(final CreateRestaurantRequest request) {
        return RestaurantDomain.builder()
                .name(request.getName())
                .description(request.getDescription())
                .status(RestaurantStatus.CLOSED)
                .delivery_tax(request.getDelivery_tax())
                .city(request.getCity())
                .state(request.getState())
                .neighborhood(request.getNeighborhood())
                .street(request.getStreet())
                .number(request.getNumber())
                .complement(request.getComplement())
                .reference(request.getReference())
                .build();
    }

    public CatalogMenuDomain toCreateCatalog(final CreateCatalogRequest request) {
        return CatalogMenuDomain.builder()
                .name(request.getName())
                .itens(request.getItens())
                .build();
    }


    public RestaurantDomain toUpdateDetails(final UUID restaurantId, final UpdateRestaurantRequest request) {
        return RestaurantDomain.builder()
                .restaurantId(restaurantId)
                .name(request.getName())
                .description(request.getDescription())
                .delivery_tax(request.getDelivery_tax())
                .city(request.getCity())
                .state(request.getState())
                .neighborhood(request.getNeighborhood())
                .street(request.getStreet())
                .number(request.getNumber())
                .complement(request.getComplement())
                .reference(request.getReference())
                .build();
    }

    public RestaurantDomain toUpdateStatus(final UUID restaurantId, final UpdateRestaurantStatusRequest request) {


        return RestaurantDomain.builder()
                .restaurantId(restaurantId)
                .status(request.getStatus())
                .build();
    }


}
