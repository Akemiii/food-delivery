package org.example.factory;

import org.example.api.dto.request.Restaurant.CreateRestaurantRequest;
import org.example.api.dto.request.Restaurant.UpdateRestaurantStatusRequest;
import org.example.domain.RestaurantDomain;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RestaurantDomainFactory {
    public RestaurantDomain toCreate(final CreateRestaurantRequest request) {
        return RestaurantDomain.builder()
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

    public RestaurantDomain toUpdate(final UUID restaurantId, final UpdateRestaurantStatusRequest request){


        return RestaurantDomain.builder()
                .restaurantId(restaurantId)
                .status(request.getStatus())
                .build();
    }


}
