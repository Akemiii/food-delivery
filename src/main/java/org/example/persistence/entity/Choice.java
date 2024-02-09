package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Table(name = "choice")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "choice_id")
    private UUID choiceId;
    private String name;
    private int min;
    private int max;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "choice", cascade = CascadeType.ALL)
    private List<Additionalitems> additionalitems;



}
