package org.example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.persistence.entity.Order;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDomain {
    
    private UUID user_id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<Order> orders;
}
