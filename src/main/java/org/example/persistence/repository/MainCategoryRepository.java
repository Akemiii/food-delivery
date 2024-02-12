package org.example.persistence.repository;

import org.example.persistence.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MainCategoryRepository extends JpaRepository<MainCategory, UUID> {
}
