package org.example.persistence.repository;

import org.example.persistence.entity.CatalogMenu;
import org.example.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CatalogMenuRepository extends JpaRepository<CatalogMenu, UUID> {
    List<CatalogMenu> findByRestaurant_RestaurantId(UUID categoryId);
}
