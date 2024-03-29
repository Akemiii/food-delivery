package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.util.ProductStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "product")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @Column(name = "product_id")
    private UUID productId;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private boolean needChoices;//Todo:: Atualizar o cadastro e o update do produto
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private CatalogMenu catalogMenu;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Choice> choices;
}
