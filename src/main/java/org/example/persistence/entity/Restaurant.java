package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.util.RestaurantStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name="restaurant")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "restaurant_id")
    private UUID restaurantId;

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'CLOSED'")
    private RestaurantStatus status;
    private BigDecimal delivery_tax;
    private String city;
    private String state;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    private String reference;
    @CreationTimestamp
    private LocalDateTime createdAt;

//    @OneToMany(mappedBy = "restaurant")
//    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name= "category_id")
    private Category category;
}
