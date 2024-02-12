package org.example.persistence.repository;

import org.example.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByCategory_CategoryId(UUID categoryId);
    List<Product> findByRestaurant_RestaurantId(UUID restaurantId);
    List<Product> findByName(String name);
    List<Product> findByRestaurant_name(String name);
    List<Product> findByCategory_title(String title);
}
