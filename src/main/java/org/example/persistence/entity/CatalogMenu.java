package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Table(name="catalog_menu")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatalogMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
}
