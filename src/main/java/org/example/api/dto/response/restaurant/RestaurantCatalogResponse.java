package org.example.api.dto.response.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.api.dto.response.CatalogMenuResponse;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCatalogResponse {
    private List<CatalogMenuResponse> menu;
}
