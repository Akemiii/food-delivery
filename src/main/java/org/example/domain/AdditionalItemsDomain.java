package org.example.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalItemsDomain {
    private UUID id;
    private ChoiceDomain choice;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
}
