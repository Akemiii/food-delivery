package org.example.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.persistence.entity.Restaurant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ReviewResponse {

    private UUID reviewId;
    private Restaurant restaurant;
    private int value;
    private String description;
    private LocalDateTime createdAt;
}
