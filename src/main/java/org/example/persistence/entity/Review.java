package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name="review")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private UUID reviewId;
}
