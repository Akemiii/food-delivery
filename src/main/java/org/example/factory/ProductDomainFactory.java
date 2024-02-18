package org.example.factory;

import lombok.AllArgsConstructor;
import org.example.api.dto.request.product.CreateProductRequest;
import org.example.api.dto.request.product.UpdateProductImageRequest;
import org.example.api.dto.request.product.UpdateProductRequest;
import org.example.api.dto.request.product.UpdateProductStatusRequest;
import org.example.domain.AdditionalItemsDomain;
import org.example.domain.ChoiceDomain;
import org.example.domain.ProductDomain;
import org.example.persistence.entity.Category;
import org.example.persistence.entity.Choice;
import org.example.persistence.entity.Product;
import org.example.util.ObjectMapperUtil;
import org.example.util.ProductStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ProductDomainFactory {

    private final ObjectMapperUtil objectMapperUtil;
    private final ChoiceModelFactory choiceModelFactory;

    public ProductDomain toCreate(final CreateProductRequest request) {
        return ProductDomain.builder()
                .productId(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .category(request.getCategory())
                .status(Optional.ofNullable(request.getStatus()).orElse(ProductStatus.ACTIVE))
                .choices(objectMapperUtil.mapAll(request.getChoices(), ChoiceDomain.class))
                .needChoices(request.isNeedChoices()).build();
    }

    public ProductDomain toUpdateDetails(final UUID productId, final UpdateProductRequest request) {
        return ProductDomain.builder()
                .productId(productId)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .status(request.getStatus())
                .needChoices(request.isNeedChoices())
                .choices(objectMapperUtil.mapAll(request.getChoices(), ChoiceDomain.class))
                .build();
    }

    public List<ChoiceDomain> buildChoiceDomain(final ProductDomain productDomain) {
        return productDomain.getChoices().stream()
                .map(choiceDomain -> ChoiceDomain.builder()
                        .choiceId(Optional.ofNullable(choiceDomain.getChoiceId()).orElse(UUID.randomUUID()))
                        .name(choiceDomain.getName())
                        .min(choiceDomain.getMin())
                        .max(choiceDomain.getMax())
                        .product(productDomain)
                        .build()).toList();
    }

    public List<AdditionalItemsDomain> buildAdditionalItemsDomain(final ChoiceDomain choiceDomain, final Choice choice) {
        return choiceDomain.getAdditionalItems().stream()
                .map(additionalItemsDomain -> AdditionalItemsDomain.builder()
                        .id(Optional.ofNullable(additionalItemsDomain.getId()).orElse(UUID.randomUUID()))
                        .name(additionalItemsDomain.getName())
                        .description(additionalItemsDomain.getDescription())
                        .image(additionalItemsDomain.getImage())
                        .price(additionalItemsDomain.getPrice())
                        .choice(objectMapperUtil.map(choice, ChoiceDomain.class))
                        .build()).toList();
    }


    public ProductDomain toUpdateImage(final UUID productId, final UpdateProductImageRequest request) {
        return ProductDomain.builder().productId(productId).image(request.getImage()).build();
    }

    public ProductDomain toUpdateStatus(final UUID productId, final UpdateProductStatusRequest request) {
        return ProductDomain.builder().productId(productId).status(request.getStatus()).build();
    }

    public ProductDomain build(final UUID productId, final UpdateProductRequest request) {
        return ProductDomain.builder()
                .productId(productId)
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .needChoices(request.isNeedChoices())
                .status(request.getStatus())
                .choices(
                        objectMapperUtil
                                .mapAll(request.getChoices(),
                                        ChoiceDomain.class))
                .build();
    }

    public Product updateProduct(Product existingProduct, UpdateProductRequest updateRequest) {

        String newName = Optional.ofNullable(updateRequest.getName()).orElse(existingProduct.getName());
        String newDescription = Optional.ofNullable(updateRequest.getDescription())
                .orElse(existingProduct.getDescription());
        BigDecimal newPrice = Optional.ofNullable(updateRequest.getPrice()).orElse(existingProduct.getPrice());

        Category newCategory = Optional.ofNullable(updateRequest.getCategory())
                .map(category -> objectMapperUtil.map(category, Category.class))
                .orElse(existingProduct.getCategory());

        boolean newNeedChoices = Optional.of(updateRequest.isNeedChoices()).orElse(existingProduct.isNeedChoices());
        ProductStatus newStatus = Optional.ofNullable(updateRequest.getStatus()).orElse(existingProduct.getStatus());

        List<Choice> existingChoices = choiceModelFactory.getChoices(existingProduct, updateRequest);

        existingProduct.setChoices(existingChoices);
        existingProduct.setName(newName);
        existingProduct.setDescription(newDescription);
        existingProduct.setPrice(newPrice);
        existingProduct.setCategory(newCategory);
        existingProduct.setNeedChoices(newNeedChoices);
        existingProduct.setStatus(newStatus);

        return existingProduct;
    }
}
