package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.api.dto.response.restaurant.RestaurantCatalogResponse;

import java.util.List;
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
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "catalogMenu")
    private List<Product> products;
}
