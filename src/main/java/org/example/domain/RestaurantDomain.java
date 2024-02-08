package org.example.domain;

import lombok.*;
import org.example.persistence.entity.Category;
import org.example.persistence.entity.Review;
import org.example.util.RestaurantStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDomain {
    private UUID restaurantId;

    private String name;
    private String description;
    private RestaurantStatus status;
    private BigDecimal delivery_tax;
    private String city;
    private String state;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    private String reference;
    private LocalDateTime createdAt;
//    private List<Review> reviews;
//    private Category category;


}
