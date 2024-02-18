package org.example.factory;

import lombok.AllArgsConstructor;
import org.example.api.dto.request.CreateAdditionalItemRequest;
import org.example.api.dto.request.CreateChoiceRequest;
import org.example.api.dto.request.product.UpdateProductRequest;
import org.example.persistence.entity.AdditionalItems;
import org.example.persistence.entity.Choice;
import org.example.persistence.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ChoiceModelFactory {

    private final AdditionalItemModelFactory additionalItemModelFactory;

    public List<Choice> getChoices(Product existingProduct, UpdateProductRequest updateRequest) {
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
                                        additionalItemModelFactory.createOrUpdateAdditionalItemModel(
                                                existingAdditionalItem,
                                                additionalItemRequest, choice)),
                        () -> existingAdditionalItems.add(
                                additionalItemModelFactory.createOrUpdateAdditionalItemModel(new AdditionalItems(),
                                additionalItemRequest, choice))
                );
            });
        }

        choice.setAdditionalItems(existingAdditionalItems);

        return choice;
    }
}
