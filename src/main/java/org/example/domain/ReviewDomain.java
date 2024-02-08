package org.example.domain;

import lombok.*;
import org.example.persistence.entity.Restaurant;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDomain {

    private UUID reviewId;
    private int value;
    private String description;
    private LocalDateTime createdAt;
}
