package org.example.api.dto.request.Restaurant;

import lombok.Getter;
import lombok.Setter;
import org.example.api.dto.request.product.CreateProductRequest;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateCatalogRequest {
    private UUID catalogId;
    private String name;
    private List<CreateProductRequest> itens;
}
