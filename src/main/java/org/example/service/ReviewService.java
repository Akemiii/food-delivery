package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.response.ReviewResponse;
import org.example.persistence.repository.ReviewRepository;
import org.example.util.ObjectMapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final ObjectMapperUtil objectMapperUtil;

    public List<ReviewResponse> getAllReviews(final UUID restaurantId){
        final var allReviews =  repository.findByRestaurant_RestaurantId(restaurantId);

        return objectMapperUtil.mapAll(allReviews, ReviewResponse.class);
    }

}
