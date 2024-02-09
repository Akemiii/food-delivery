package org.example.api.controller;


import lombok.RequiredArgsConstructor;
import org.example.api.dto.response.category.CategoryResponse;
import org.example.factory.CategoryDomainFactory;
import org.example.service.CategoryService;
import org.example.util.ObjectMapperUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final ObjectMapperUtil objectMapperUtil;
    private final CategoryDomainFactory domainFactory;
    private final CategoryService service;

    @GetMapping
    public List<CategoryResponse> getAll(){
        return objectMapperUtil.mapAll(service.getAllCategories(), CategoryResponse.class);
    }
    //TOdo:: get category by name
    //TODO:: get all restaurants by category_id
    //TODO:: add category
    //TODO:: update category
    //todo:: delete category


}
