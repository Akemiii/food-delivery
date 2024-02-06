package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    private Boolean status;
    private BigDecimal delivery_tax;
    @Column(name= "category_id")
    private UUID categoryId;
    private String city;
    private String state;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    private String reference;

}
