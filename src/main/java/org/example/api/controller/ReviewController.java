package org.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.api.dto.response.ReviewResponse;
import org.example.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("restaurant/{restaurantId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;


    @GetMapping
    public List<ReviewResponse> getAllReviews(@PathVariable final UUID restaurantId){
        return service.getAllReviews(restaurantId);
    }


}
