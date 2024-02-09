package org.example.api.dto.request.Restaurant;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateRestaurantRequest {
    private String name;
    private String description;
    private BigDecimal delivery_tax;
    private String city;
    private String state;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    private String reference;
}
