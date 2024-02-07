package org.example.factory;

import org.example.api.dto.request.CreateUserRequest;
import org.example.domain.ProductDomain;
import org.example.domain.UserDomain;
import org.springframework.stereotype.Component;

@Component
public class UserDomainFactory {

    public UserDomain toCreate(final CreateUserRequest request) {
        return UserDomain.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }
}
