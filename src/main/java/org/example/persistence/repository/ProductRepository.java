package org.example.persistence.repository;

import org.example.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByCategory_CategoryId(UUID categoryId);
    List<Product> findByRestaurant_RestaurantId(UUID categoryId);

    List<Product> findByName(String name);
}
