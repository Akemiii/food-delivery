package org.example.factory;

import org.example.api.dto.request.product.CreateProductRequest;
import org.example.api.dto.request.product.UpdateProductImageRequest;
import org.example.api.dto.request.product.UpdateProductRequest;
import org.example.api.dto.request.product.UpdateProductStatusRequest;
import org.example.domain.AdditionalItemsDomain;
import org.example.domain.ChoiceDomain;
import org.example.domain.ProductDomain;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductDomainFactory {

    public ProductDomain toCreate(final CreateProductRequest request) {
        return ProductDomain.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .category(request.getCategory())
                .build();
    }

    public ProductDomain toUpdateDetails(final UUID productId, final UpdateProductRequest request) {
        final var builder = ProductDomain.builder()
                .productId(productId)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .status(request.getStatus())
                .needChoices(request.isNeedChoices())
                .build();

        builder.setChoices((buildChoiceDomain(builder, request)));


        return builder;
    }

    public List<ChoiceDomain> buildChoiceDomain(final ProductDomain productDomain, final UpdateProductRequest request) {
        final var choices = request.getChoices();

        return choices
                .stream()
                .map(choice -> ChoiceDomain.builder()
                        .choiceId(Optional.ofNullable(choice.getChoiceId()).orElse(UUID.randomUUID()))
                        .name(choice.getName())
                        .min(choice.getMin())
                        .max(choice.getMax())
                        .product(productDomain)
                        .additionalItems(buildAdditionalItemsDomain(choice))
                        .build())
                .collect(Collectors.toList());
    }

    public List<AdditionalItemsDomain> buildAdditionalItemsDomain(final ChoiceDomain choice){
        final var additionalItems = choice.getAdditionalItems();

        return additionalItems.
                stream()
                .map(additionalItem -> AdditionalItemsDomain.builder()
                        .id(Optional.ofNullable(additionalItem.getId()).orElse(UUID.randomUUID()))
                        .name(additionalItem.getName())
                        .description(additionalItem.getDescription())
                        .image(additionalItem.getImage())
                        .price(additionalItem.getPrice())
                        .choice(choice)
                        .build()
                ).collect(Collectors.toList());
    }


    public ProductDomain toUpdateImage(final UUID productId, final UpdateProductImageRequest request) {
        return ProductDomain.builder().productId(productId).image(request.getImage()).build();
    }

    public ProductDomain toUpdateStatus(final UUID productId, final UpdateProductStatusRequest request) {
        return ProductDomain.builder().productId(productId).status(request.getStatus()).build();
    }

}
