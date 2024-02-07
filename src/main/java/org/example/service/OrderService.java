package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.domain.OrderDomain;
import org.example.domain.ProductDomain;
import org.example.persistence.entity.Order;
import org.example.persistence.repository.ProductRepository;
import org.example.util.ObjectMapperUtil;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository repository;
    private final ObjectMapperUtil objectMapperUtil;

    @SneakyThrows
    public OrderDomain findById(UUID orderId) {
        return repository.findById(orderId)
                .map(objectMapperUtil.mapFn(OrderDomain.class))
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }



}
