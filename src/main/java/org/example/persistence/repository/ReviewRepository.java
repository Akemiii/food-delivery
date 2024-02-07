package org.example.persistence.repository;

import org.example.persistence.entity.Restaurant;
import org.example.persistence.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findByRestaurant_RestaurantId(UUID restaurantId);
}
