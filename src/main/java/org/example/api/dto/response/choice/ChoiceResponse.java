package org.example.api.dto.response.choice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.api.dto.response.additionalItems.AdditionalItemResponse;
import org.example.persistence.entity.AdditionalItems;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceResponse {
    private UUID choiceId;
    private String name;
    private int min;
    private int max;
    private List<AdditionalItemResponse> additionalItems;
}
