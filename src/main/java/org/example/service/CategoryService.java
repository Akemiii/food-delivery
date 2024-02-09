package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.CategoryDomain;
import org.example.persistence.entity.Category;
import org.example.persistence.repository.CategoryRepository;
import org.example.util.ObjectMapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final ObjectMapperUtil objectMapperUtil;

    public List<CategoryDomain> getAllCategories() {
        return objectMapperUtil.mapAll(repository.findAll(), CategoryDomain.class);
    }

    public CategoryDomain create(final CategoryDomain categoryDomain) {
        final var category = objectMapperUtil.map(categoryDomain, Category.class);

        return objectMapperUtil.map(repository.save(category), CategoryDomain.class);
    }
}
