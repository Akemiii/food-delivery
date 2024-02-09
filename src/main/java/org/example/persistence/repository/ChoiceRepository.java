package org.example.persistence.repository;

import org.example.persistence.entity.Choice;
import org.example.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChoiceRepository extends JpaRepository<Choice, UUID> {
    List<Choice> findByProduct_ProductId(UUID id);
}
