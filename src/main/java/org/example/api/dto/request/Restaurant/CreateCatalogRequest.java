package org.example.api.dto.request.Restaurant;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.CatalogMenuDomain;
import java.util.List;

@Getter
@Setter
public class CreateCatalogRequest {
    private List<CatalogMenuDomain> menu;
}
