package org.example.api.controller;
import lombok.RequiredArgsConstructor;
import org.example.api.dto.request.CreateReviewRequest;
import org.example.api.dto.response.ReviewResponse;
import org.example.factory.ReviewDomainFactory;
import org.example.service.ReviewService;
import org.example.util.ObjectMapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("restaurant/{restaurantId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;
    private final ObjectMapperUtil objectMapperUtil;
    private final ReviewDomainFactory domainFactory;

    @GetMapping
    public List<ReviewResponse> getAllReviews(@PathVariable final UUID restaurantId){

        return  objectMapperUtil.mapAll(service.getAllReviews(restaurantId), ReviewResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse create(@PathVariable UUID restaurantId, @RequestBody @Validated CreateReviewRequest request){
        final var review = service.create(restaurantId,
                domainFactory.toCreate(request)
        );

        return objectMapperUtil.map(review, ReviewResponse.class);
    }


}
