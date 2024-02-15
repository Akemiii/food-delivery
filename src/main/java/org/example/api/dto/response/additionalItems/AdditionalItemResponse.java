package org.example.api.dto.response.additionalItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalItemResponse {
    private UUID id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
}
