package org.example.api.dto.request.Restaurant;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.persistence.entity.Category;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateRestaurantRequest {

    @NotNull
    @NotNull
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
    private Category category;
}
