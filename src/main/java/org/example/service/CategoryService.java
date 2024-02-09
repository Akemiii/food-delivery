package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.CategoryDomain;
import org.example.persistence.repository.CategoryRepository;
import org.example.util.ObjectMapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final ObjectMapperUtil objectMapperUtil;

    public List<CategoryDomain> getAllCategories(){
        return objectMapperUtil.mapAll(repository.findAll(), CategoryDomain.class);
    }

}
