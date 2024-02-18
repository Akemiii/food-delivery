package org.example.factory;

import lombok.AllArgsConstructor;
import org.example.api.dto.request.CreateAdditionalItemRequest;
import org.example.persistence.entity.AdditionalItems;
import org.example.persistence.entity.Choice;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AdditionalItemModelFactory {

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
