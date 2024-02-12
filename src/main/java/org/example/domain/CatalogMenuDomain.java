package org.example.domain;

import lombok.*;
import org.example.api.dto.request.product.CreateProductRequest;
import org.example.persistence.entity.Restaurant;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogMenuDomain {
    private UUID catalogId;
    private String name;
    private List<CreateProductRequest> itens;
}
