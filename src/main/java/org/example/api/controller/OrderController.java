package org.example.api.controller;


import lombok.RequiredArgsConstructor;
import org.example.api.dto.response.order.OrderResponse;
import org.example.service.OrderService;
import org.example.util.ObjectMapperUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final ObjectMapperUtil objectMapperUtil;
    private final OrderService service;

    @GetMapping("{orderId}")
    public OrderResponse get(@PathVariable UUID orderId) {
        final var order = service.findById(orderId);

        return objectMapperUtil.map(order, OrderResponse.class);
    }

}
