package org.example.domain;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceDomain {

    private UUID choiceId;
    private String name;
    private int min;
    private int max;
    private List<AdditionalItensDomain> additionalItens;

}
