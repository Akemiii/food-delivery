package org.example.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.api.dto.response.product.ProductCatalogResponse;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogMenuResponse {
    private UUID id;
    private String name;

    List<ProductCatalogResponse> itens;
}
