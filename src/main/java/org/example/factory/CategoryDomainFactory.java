package org.example.factory;

import org.example.api.dto.request.CreateCategoryRequest;
import org.example.domain.CategoryDomain;
import org.springframework.stereotype.Component;

@Component
public class CategoryDomainFactory {
    public CategoryDomain toCreate(final CreateCategoryRequest request){
        return CategoryDomain.builder()
                .title(request.getTitle())
                .build();
    }

}
