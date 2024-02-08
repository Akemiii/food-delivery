package org.example.api.dto.response.review;

import lombok.*;
import org.example.persistence.entity.Restaurant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {

    private UUID reviewId;
    private int value;
    private String description;
    private LocalDateTime createdAt;
}
