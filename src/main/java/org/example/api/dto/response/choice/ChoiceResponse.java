package org.example.api.dto.response.choice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
}
