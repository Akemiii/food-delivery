package org.example.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.AdditionalItemsDomain;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateChoiceRequest {

    private UUID choiceId;
    private String name;
    private int min;
    private int max;
    private List<CreateAdditionalItemRequest> additionalItems;
}
