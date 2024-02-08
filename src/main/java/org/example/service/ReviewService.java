package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.domain.ReviewDomain;
import org.example.persistence.entity.Review;
import org.example.persistence.repository.RestaurantRepository;
import org.example.persistence.repository.ReviewRepository;
import org.example.util.ObjectMapperUtil;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final ObjectMapperUtil objectMapperUtil;

    public List<ReviewDomain> getAllReviews(final UUID restaurantId){
        return objectMapperUtil.mapAll(
                repository.findByRestaurant_RestaurantId(restaurantId), ReviewDomain.class
        );
    }

    @SneakyThrows
    public ReviewDomain create(final UUID restaurantId , final ReviewDomain domain){
        final var review = objectMapperUtil.map(domain, Review.class);

        final var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        review.setRestaurant(restaurant);

        return objectMapperUtil.map(repository.save(review), ReviewDomain.class);
    }




}
