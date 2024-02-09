package org.example.api.dto.response.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.api.dto.response.choice.ChoiceResponse;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCatalogResponse {
//    product
    private UUID productId;
    private String name;
    private String description;
    private String image;
    private boolean needChoices;
    List<ChoiceResponse> choices;


}
