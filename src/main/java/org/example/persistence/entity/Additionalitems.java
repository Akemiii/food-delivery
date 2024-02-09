package org.example.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.SimpleTimeZone;
import java.util.UUID;

@Table(name = "additional_items")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Additionalitems {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "choice_id")
    private Choice choice;

}
