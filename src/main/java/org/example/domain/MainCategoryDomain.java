package org.example.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainCategoryDomain {
    private UUID id;
    private String title;
}
