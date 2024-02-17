package org.example.validator;

import lombok.AllArgsConstructor;
import org.example.api.dto.request.CreateAdditionalItemRequest;
import org.example.api.dto.request.CreateChoiceRequest;
import org.example.api.dto.request.product.UpdateProductRequest;
import org.example.domain.ProductDomain;
import org.example.persistence.entity.AdditionalItems;
import org.example.persistence.entity.Category;
import org.example.persistence.entity.Choice;
import org.example.persistence.entity.Product;
import org.example.util.ObjectMapperUtil;
import org.example.util.ProductStatus;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class ProductValidator {

    private final ObjectMapperUtil objectMapperUtil;

    public void CheckNegativePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("O valor do produto não pode ser negativo");
    }

    public void CheckValidBody(ProductDomain domain) {
        if (!nonNull(domain.getName()) && !nonNull(domain.getDescription()) && !nonNull(domain.getImage()) && !nonNull(domain.getChoices()))
            throw new IllegalArgumentException("O body não pode ser vazio");
    }

    public ProductDomain updateProductDomain(ProductDomain domain, ProductDomain updateDomain) {
        if (nonNull(updateDomain.getName()) && !updateDomain.getName().isEmpty())
            domain.setName(updateDomain.getName());

        if (nonNull(updateDomain.getDescription()) && !updateDomain.getDescription().isEmpty())
            domain.setDescription(updateDomain.getDescription());

        if (nonNull(updateDomain.getPrice())) {
            CheckNegativePrice(updateDomain.getPrice());
            domain.setPrice(updateDomain.getPrice());
        }

        if (nonNull(updateDomain.getCategory())) domain.setCategory(updateDomain.getCategory());

        if (nonNull(updateDomain.getStatus())) domain.setStatus(updateDomain.getStatus());

        if (nonNull(updateDomain.getChoices())) domain.setChoices(updateDomain.getChoices());


        domain.setNeedChoices(updateDomain.isNeedChoices());

        return domain;
    }

    public Product updateProduct(Product existingProduct, UpdateProductRequest updateRequest) {

        String newName = Optional.ofNullable(updateRequest.getName()).orElse(existingProduct.getName());
        String newDescription = Optional.ofNullable(updateRequest.getDescription())
                .orElse(existingProduct.getDescription());
        BigDecimal newPrice = Optional.ofNullable(updateRequest.getPrice()).orElse(existingProduct.getPrice());

        Category newCategory = Optional.ofNullable(objectMapperUtil.map(updateRequest.getCategory(), Category.class))
                .orElse(existingProduct.getCategory());

        boolean newNeedChoices = Optional.of(updateRequest.isNeedChoices()).orElse(existingProduct.isNeedChoices());
        ProductStatus newStatus = Optional.ofNullable(updateRequest.getStatus()).orElse(existingProduct.getStatus());

        List<Choice> existingChoices = getChoices(existingProduct, updateRequest);

        existingProduct.setChoices(existingChoices);
        existingProduct.setName(newName);
        existingProduct.setDescription(newDescription);
        existingProduct.setPrice(newPrice);
        existingProduct.setCategory(newCategory);
        existingProduct.setNeedChoices(newNeedChoices);
        existingProduct.setStatus(newStatus);

        return existingProduct;
    }

    //TODO:: separar para classe especifica
    private List<Choice> getChoices(Product existingProduct, UpdateProductRequest updateRequest) {
        List<CreateChoiceRequest> choiceRequests = updateRequest.getChoices();
        List<Choice> existingChoices = existingProduct.getChoices();

        if (choiceRequests != null) {
            choiceRequests.forEach(choiceRequest -> {
                Optional<Choice> existingChoiceOptional = choiceRequest.getChoiceId() != null ?
                        existingChoices.stream().filter(choice ->
                                choice.getChoiceId().equals(choiceRequest.getChoiceId())).findFirst() :
                        Optional.empty();

                existingChoiceOptional.ifPresentOrElse(
                        existingChoice -> existingChoices.set(existingChoices.indexOf(existingChoice),
                                updateOrCreateChoiceModel(existingChoice, choiceRequest, existingProduct)),
                        () -> existingChoices.add(updateOrCreateChoiceModel(new Choice(), choiceRequest,
                                existingProduct))
                );
            });
        }

        return existingChoices;
    }

    //TODO:: separar para classe especifica
    public Choice updateOrCreateChoiceModel(final Choice choice, CreateChoiceRequest request,
                                            Product existingProduct) {
        if (request.getChoiceId() == null) choice.setChoiceId(UUID.randomUUID());
        if (request.getName() != null && !request.getName().isEmpty()) choice.setName(request.getName());
        if (request.getMin() != 0) choice.setMin(request.getMin());
        if (request.getMax() != 0) choice.setMax(request.getMax());
        choice.setProduct(existingProduct);

        //TODO:: revisar
        List<CreateAdditionalItemRequest> additionalItemRequests = request.getAdditionalItems();
        List<AdditionalItems> existingAdditionalItems = Optional.ofNullable(choice.getAdditionalItems())
                .orElseGet(ArrayList::new);

        if (additionalItemRequests != null) {
            additionalItemRequests.forEach(additionalItemRequest -> {
                Optional<AdditionalItems> existingAdditionalItemOptional = additionalItemRequest.getId() != null ?
                        existingAdditionalItems.stream().filter(item ->
                                item.getId().equals(additionalItemRequest.getId())).findFirst() :
                        Optional.empty();

                existingAdditionalItemOptional.ifPresentOrElse(
                        existingAdditionalItem ->
                                existingAdditionalItems.set(existingAdditionalItems.indexOf(existingAdditionalItem),
                                        createOrUpdateAdditionalItemModel(existingAdditionalItem,
                                                additionalItemRequest, choice)),
                        () -> existingAdditionalItems.add(createOrUpdateAdditionalItemModel(new AdditionalItems(),
                                additionalItemRequest, choice))
                );
            });
        }

        choice.setAdditionalItems(existingAdditionalItems);

        return choice;
    }

    //TODO:: separar para classe especifica
    public AdditionalItems createOrUpdateAdditionalItemModel(final AdditionalItems additionalItems,
                                                             final CreateAdditionalItemRequest request,
                                                             Choice choice) {
        if (request.getId() == null) additionalItems.setId(UUID.randomUUID());
        if (request.getName() != null && !request.getName().isEmpty()) additionalItems.setName(request.getName());
        if (request.getDescription() != null && !request.getDescription().isEmpty())
            additionalItems.setDescription(request.getDescription());
        if (request.getImage() != null && !request.getImage().isEmpty()) additionalItems.setImage(request.getImage());
        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) > 0)
            additionalItems.setPrice(request.getPrice());
        additionalItems.setChoice(choice);

        return additionalItems;
    }


}
