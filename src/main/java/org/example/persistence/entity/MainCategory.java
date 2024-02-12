package org.example.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Table(name="main_category")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainCategory {

    @Id
    private UUID id;
    private String title;

    @OneToMany(mappedBy = "mainCategory")
    private List<Restaurant> restaurant;


}
