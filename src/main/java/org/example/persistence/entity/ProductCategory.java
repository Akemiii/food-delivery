package org.example.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name="product_category")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_category_id")
    private UUID productCategoryId;
    private String title;
    @JoinColumn(name="restaurant_id")
    private UUID restaurantId;

}
