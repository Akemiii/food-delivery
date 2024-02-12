package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.domain.ProductDomain;
import org.example.persistence.entity.Category;
import org.example.persistence.entity.MainCategory;
import org.example.persistence.entity.Product;
import org.example.persistence.repository.MainCategoryRepository;
import org.example.persistence.repository.RestaurantRepository;
import org.example.util.ObjectMapperUtil;
import org.example.validator.ProductValidator;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.example.persistence.repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ObjectMapperUtil objectMapperUtil;
    private final ProductValidator productValidator;
    private final RestaurantRepository restaurantRepository;
    private final MainCategoryRepository mainCategoryRepository;

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

    public List<ProductDomain> getAllByProductName(String name) {
        return objectMapperUtil.mapAll(repository.findByName(name), ProductDomain.class);
    }

    public List<ProductDomain> getAllByRestaurantName(String name) {
        return objectMapperUtil.mapAll(repository.findByRestaurant_name(name), ProductDomain.class);
    }

    public List<ProductDomain> getAllByCategoryTitle(String categoryTitle) {
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

    private void updateRestaurantMainCategoryWithMajorityCategory(UUID restaurantId) {
        //Calcular a categoria com a maioria dos produtos
        Optional<Category> majorityCategory = repository
                .findByRestaurant_RestaurantId(restaurantId)
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        //Se não houver uma caegoria majoritária, retornar
        if (majorityCategory.isEmpty()) return;

        //Verificar se a categoria majoritária já existe como MainCategory
        MainCategory mainCategory = mainCategoryRepository.findById(majorityCategory.get().getCategoryId()).orElseGet(() -> {
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
