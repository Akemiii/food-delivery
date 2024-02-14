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
    private RestaurantStatus status = RestaurantStatus.CLOSED;
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

    @ManyToOne
    @JoinColumn(name= "category_id")
    private MainCategory mainCategory;

    @OneToMany(mappedBy = "restaurant")
    private List<CatalogMenu> catalogMenus;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews;

}
