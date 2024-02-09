package org.example.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalItensDomain {
    private UUID id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
}
