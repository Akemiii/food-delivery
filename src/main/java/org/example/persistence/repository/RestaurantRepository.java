package org.example.persistence.repository;

import org.example.persistence.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
}
