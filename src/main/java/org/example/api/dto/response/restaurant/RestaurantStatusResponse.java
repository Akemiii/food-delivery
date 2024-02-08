package org.example.api.dto.response.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.util.RestaurantStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantStatusResponse {

    private RestaurantStatus status;
}
