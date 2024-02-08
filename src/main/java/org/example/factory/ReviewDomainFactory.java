package org.example.factory;

import org.example.api.dto.request.CreateReviewRequest;
import org.example.domain.ReviewDomain;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReviewDomainFactory {

    public ReviewDomain toCreate(final CreateReviewRequest request){
        return ReviewDomain.builder()
                .value(request.getValue())
                .description(request.getDescription())
                .build();
    }

}
