package org.example.api.dto.request.Restaurant;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.CategoryDomain;
import org.example.persistence.entity.Category;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateRestaurantRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private BigDecimal delivery_tax;
    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String neighborhood;
    @NotEmpty
    private String street;
    @NotEmpty
    private String number;
    @NotEmpty
    private String complement;
    @NotEmpty
    private String reference;
    private CategoryDomain category;
}
