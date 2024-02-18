package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.api.dto.request.product.UpdateProductRequest;
import org.example.api.dto.response.choice.ChoiceResponse;
import org.example.api.dto.response.product.ProductResponse;
import org.example.domain.CategoryDomain;
import org.example.domain.ProductDomain;
import org.example.factory.ProductDomainFactory;
import org.example.persistence.entity.*;
import org.example.persistence.repository.*;
import org.example.util.ObjectMapperUtil;
import org.example.validator.ProductValidator;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ObjectMapperUtil objectMapperUtil;
    private final ProductValidator productValidator;
    private final RestaurantRepository restaurantRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final ProductDomainFactory factory;

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

    public List<ProductDomain> getAllByProductByName(String name) {
        return objectMapperUtil.mapAll(repository.findByName(name), ProductDomain.class);
    }

    public List<ProductDomain> getAllByRestaurantByName(String name) {
        return objectMapperUtil.mapAll(repository.findByRestaurant_name(name), ProductDomain.class);
    }

    public List<ProductDomain> getAllByCategoryByTitle(String categoryTitle) {
        return objectMapperUtil.mapAll(repository.findByCategory_title(categoryTitle), ProductDomain.class);
    }

    public List<ProductDomain> getAllProductsByRestaurantId(UUID restaurantId) {
        return objectMapperUtil.mapAll(repository.findByRestaurant_RestaurantId(restaurantId), ProductDomain.class);
    }

    public ProductDomain create(UUID restaurantId, final ProductDomain productDomain) {
        final var product = objectMapperUtil.map(productDomain, Product.class);

        restaurantRepository.findById(restaurantId).ifPresent(product::setRestaurant);

        productValidator.CheckNegativePrice(productDomain.getPrice());

        final var productSaved = repository.save(product);

        updateRestaurantMainCategoryWithMajorityCategory(restaurantId);

        return objectMapperUtil.map(productSaved, ProductDomain.class);
    }

    public void delete(final UUID productId) {
        repository.deleteById(productId);
    }

    @SneakyThrows
    @Transactional
    public ProductResponse updateDetails(final UUID productId, final UpdateProductRequest request) {
        final var product = repository.findById(productId).orElseThrow(ChangeSetPersister.NotFoundException::new);


        return updateProductModel()
                .andThen(persistProduct())
                .andThen(buildProductResponse())
                .apply(product, request);
    }

    private BiFunction<UUID, UpdateProductRequest, ProductDomain> updateProductDomain() {
        return factory::toUpdateDetails;
    }

    private BiFunction<Product, UpdateProductRequest, Product> updateProductModel(){
        return factory::updateProduct;
    }

    private UnaryOperator<Product> persistProduct() {
        return repository::save;
    }

    private Function<Product, ProductResponse> buildProductResponse() {
        return domain -> ProductResponse.builder()
                .productId(domain.getProductId())
                .name(domain.getName())
                .description(domain.getDescription())
                .price(domain.getPrice())
                .image(domain.getImage())
                .needChoices(domain.isNeedChoices())
                .category(objectMapperUtil.map(domain.getCategory(), CategoryDomain.class))
                .status(domain.getStatus())
                .choices(objectMapperUtil.mapAll(domain.getChoices(), ChoiceResponse.class))
                .build();
    }

    public ProductDomain updateImage(final ProductDomain updateProductDomain) {
        var productDomain = findById(updateProductDomain.getProductId());

        productDomain.setImage(updateProductDomain.getImage());

        final var product = repository.save(objectMapperUtil.map(productDomain, Product.class));

        return objectMapperUtil.map(product, ProductDomain.class);
    }

    public ProductDomain updateStatus(final ProductDomain updateProductDomain) {
        var productDomain = findById(updateProductDomain.getProductId());

        productDomain.setStatus(updateProductDomain.getStatus());

        final var product = repository.save(objectMapperUtil.map(productDomain, Product.class));

        return objectMapperUtil.map(product, ProductDomain.class);
    }

    private void updateRestaurantMainCategoryWithMajorityCategory(UUID restaurantId) {
        //Calcular a categoria com a maioria dos produtos
        Optional<Category> majorityCategory = repository.
                findByRestaurant_RestaurantId(restaurantId)
                .stream()
                .collect(Collectors
                        .groupingBy(Product::getCategory, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        //Se não houver uma caegoria majoritária, retornar
        if (majorityCategory.isEmpty()) return;

        //Verificar se a categoria majoritária já existe como MainCategory
        MainCategory mainCategory = mainCategoryRepository
                .findById(majorityCategory.get().getCategoryId())
                .orElseGet(() -> {
                    MainCategory newMainCategory = objectMapperUtil.map(majorityCategory.get(), MainCategory.class);
                    return mainCategoryRepository.save(newMainCategory);
                });


        //Atualizar o restaurante com a categoria majoritária
        restaurantRepository.findById(restaurantId).ifPresent(restaurant -> {
            restaurant.setMainCategory(mainCategory);
            restaurantRepository.save(restaurant);
        });
    }

}
