package org.example.api.dto.request.Restaurant;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.persistence.entity.Category;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateRestaurantRequest {

    @NotNull
    @NotEmpty
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
