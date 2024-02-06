package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.domain.ProductDomain;
import org.example.persistence.entity.Product;
import org.example.util.ObjectMapperUtil;
import org.example.validator.ProductValidator;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.example.persistence.repository.ProductRepository;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ObjectMapperUtil objectMapperUtil;
    private final ProductValidator productValidator;

    public List<ProductDomain> getAllProducts() {
        return objectMapperUtil.mapAll(
                repository.findAll(), ProductDomain.class
        );
    }

    @SneakyThrows
    public ProductDomain findById(UUID productId) {
        return repository.findById(productId)
                .map(objectMapperUtil.mapFn(ProductDomain.class))
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public ProductDomain create(final ProductDomain productDomain) {
        final var product = objectMapperUtil.map(productDomain, Product.class);

        productValidator.CheckNegativePrice(productDomain.getPrice());

        return objectMapperUtil.map(
                repository.save(product), ProductDomain.class
        );
    }

    public void delete(final UUID productId) {
        repository.deleteById(productId);
    }

    public ProductDomain update(final ProductDomain updateProductDomain) {
        final var productDomain = findById(updateProductDomain.getProductId());

        productValidator.CheckNegativePrice(updateProductDomain.getPrice());

        if (nonNull(updateProductDomain.getName()) && !updateProductDomain.getName().isEmpty())
            productDomain.setName(updateProductDomain.getName());
        if (nonNull(updateProductDomain.getImage()) && !updateProductDomain.getImage().isEmpty())
            productDomain.setImage(updateProductDomain.getImage());
        if (nonNull(updateProductDomain.getDescription()) && !updateProductDomain.getDescription().isEmpty())
            productDomain.setDescription(updateProductDomain.getDescription());

        if (nonNull(updateProductDomain.getPrice()))
            productDomain.setPrice(updateProductDomain.getPrice());


        final var product = repository.save(objectMapperUtil.map(productDomain, Product.class));

        return objectMapperUtil.map(product, ProductDomain.class);
    }

}
