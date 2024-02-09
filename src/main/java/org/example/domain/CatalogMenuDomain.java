package org.example.domain;

import lombok.*;
import org.example.persistence.entity.Restaurant;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogMenuDomain {
    private UUID id;
    private String name;
//    private Restaurant restaurant;
}
