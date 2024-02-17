package org.example.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChoiceRequest {

    private UUID choiceId;
    private String name;
    private int min;
    private int max;
    private List<CreateAdditionalItemRequest> additionalItems;
}
