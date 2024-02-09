package org.example.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.api.dto.request.CreateCategoryRequest;
import org.example.api.dto.response.category.CategoryResponse;
import org.example.factory.CategoryDomainFactory;
import org.example.service.CategoryService;
import org.example.util.ObjectMapperUtil;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final ObjectMapperUtil objectMapperUtil;
    private final CategoryDomainFactory domainFactory;
    private final CategoryService service;

    @Operation(summary = "Get a list of category")
    @GetMapping
    public List<CategoryResponse> getAll() {
        return objectMapperUtil.mapAll(service.getAllCategories(), CategoryResponse.class);
    }

    //TOdo:: get category by name

    @Operation(summary = "Create category")
    @PostMapping
    public CategoryResponse addCategory(@RequestBody @Valid CreateCategoryRequest request) {
        final var category = service.create(domainFactory.toCreate(request));

        return objectMapperUtil.map(category, CategoryResponse.class);
    }
    //TODO:: update category
    //todo:: delete category


}
