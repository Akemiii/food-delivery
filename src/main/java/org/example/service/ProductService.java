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

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ObjectMapperUtil objectMapperUtil;
    private final ProductValidator productValidator;

    public List<ProductDomain> getAllProducts() {
        return objectMapperUtil.mapAll(repository.findAll(), ProductDomain.class);
    }

    @SneakyThrows
    public ProductDomain findById(UUID productId) {
        return repository.findById(productId).map(objectMapperUtil.mapFn(ProductDomain.class)).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<ProductDomain> getAllProductsByCategoryId(UUID categoryId) {
        return objectMapperUtil.mapAll(repository.findByCategory_CategoryId(categoryId), ProductDomain.class);
    }

    public List<ProductDomain> getAllByProductName(String name){
        return objectMapperUtil.mapAll(repository.findByName(name), ProductDomain.class);
    }

    public List<ProductDomain> getAllProductsByRestaurantId(UUID restaurantId) {
        return objectMapperUtil.mapAll(repository.findByRestaurant_RestaurantId(restaurantId), ProductDomain.class);
    }

    public ProductDomain create(final ProductDomain productDomain) {
        final var product = objectMapperUtil.map(productDomain, Product.class);

        productValidator.CheckNegativePrice(productDomain.getPrice());

        return objectMapperUtil.map(repository.save(product), ProductDomain.class);
    }

    public void delete(final UUID productId) {
        repository.deleteById(productId);
    }

    public ProductDomain update(final ProductDomain updateProductDomain) {
        productValidator.CheckValidBody(updateProductDomain);
        var productDomain = findById(updateProductDomain.getProductId());

        productDomain = productValidator.updateProductDomain(productDomain, updateProductDomain);

        final var product = repository.save(objectMapperUtil.map(productDomain, Product.class));

        return objectMapperUtil.map(product, ProductDomain.class);
    }

    public ProductDomain updateImage(final ProductDomain updateProductDomain) {
        var productDomain = findById(updateProductDomain.getProductId());

        productDomain.setImage(updateProductDomain.getImage());

        final var product = repository.save(objectMapperUtil.map(productDomain, Product.class));

        return objectMapperUtil.map(product, ProductDomain.class);
    }

}
