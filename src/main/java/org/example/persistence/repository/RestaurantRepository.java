package org.example.persistence.repository;

import org.example.persistence.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    List<Restaurant> findByMainCategory_id(UUID categoryId);

    List<Restaurant> findByMainCategory_title(String title);

}
