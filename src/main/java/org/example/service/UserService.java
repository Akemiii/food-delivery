package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.domain.ProductDomain;
import org.example.domain.UserDomain;
import org.example.persistence.entity.Product;
import org.example.persistence.entity.User;
import org.example.persistence.repository.UserRepository;
import org.example.util.ObjectMapperUtil;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ObjectMapperUtil objectMapperUtil;

    @SneakyThrows
    public UserDomain findById(UUID userId) {
        return repository.findById(userId)
                .map(objectMapperUtil.mapFn(UserDomain.class))
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public UserDomain create(final UserDomain userDomain) {
        final var user = objectMapperUtil.map(userDomain, User.class);

        return objectMapperUtil.map(
                repository.save(user), UserDomain.class
        );
    }
}
