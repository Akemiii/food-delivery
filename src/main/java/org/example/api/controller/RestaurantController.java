package org.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.RestaurantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;



}
