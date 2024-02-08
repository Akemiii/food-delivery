package org.example.factory;

import org.example.api.dto.request.review.CreateReviewRequest;
import org.example.domain.ReviewDomain;
import org.springframework.stereotype.Component;

@Component
public class ReviewDomainFactory {

    public ReviewDomain toCreate(final CreateReviewRequest request){
        return ReviewDomain.builder()
                .value(request.getValue())
                .description(request.getDescription())
                .build();
    }

}
