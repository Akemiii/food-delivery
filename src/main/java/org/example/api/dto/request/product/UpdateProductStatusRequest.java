package org.example.api.dto.request.product;

import lombok.Getter;
import lombok.Setter;
import org.example.util.ProductStatus;

@Getter
@Setter
public class UpdateProductStatusRequest {
    private ProductStatus status;
}
