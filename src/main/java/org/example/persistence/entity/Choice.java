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
@NoArgsConstructor
public class Choice {
    @Id
    @Column(name = "choice_id")
    private UUID choiceId;
    private String name;
    private int min;
    private int max;
    private UUID productId;
    @OneToMany(mappedBy = "choiceId", fetch = FetchType.EAGER)
    private List<AdditionalItems> additionalItems;

}
