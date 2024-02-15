package org.example.persistence.repository;

import org.example.persistence.entity.AdditionalItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdditionalItemsRepository extends JpaRepository<AdditionalItems, UUID> {
}
