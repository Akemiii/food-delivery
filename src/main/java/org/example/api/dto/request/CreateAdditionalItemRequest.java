package org.example.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateAdditionalItemRequest {
    private UUID id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
}
