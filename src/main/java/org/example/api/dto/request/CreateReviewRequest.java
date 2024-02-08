package org.example.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.persistence.entity.Restaurant;

@Getter
@Setter
public class CreateReviewRequest {
    
    @NotNull
    private int value;
    private String description;
}
