package org.example.api.dto.request.Restaurant;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.util.RestaurantStatus;

@Getter
@Setter
public class UpdateRestaurantStatusRequest {

    @NotNull
    private RestaurantStatus status;
}
