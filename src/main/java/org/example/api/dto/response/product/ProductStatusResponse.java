package org.example.api.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.util.ProductStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatusResponse {

    private ProductStatus status;
}
