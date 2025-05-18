package com.example.jpa2;

import com.example.jpa2.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<ProductEntity> hasName(String name) {
        return (root, query, cb) -> name == null
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<ProductEntity> hasMinPrice(Double minPrice) {
        return (root, query, cb) -> minPrice == null
                ? cb.conjunction()
                : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<ProductEntity> hasMaxPrice(Double maxPrice) {
        return (root, query, cb) -> maxPrice == null
                ? cb.conjunction()
                : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}

