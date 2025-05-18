package com.example.jpa2;

import com.example.jpa2.entity.OrderEntity;
import com.example.jpa2.entity.ProductEntity;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class OrderSpecification {
    public static Specification<OrderEntity> hasStatus(String status) {
        return (root, query, cb) -> status == null ? null :
                cb.equal(root.get("status"), status);
    }

    public static Specification<OrderEntity> hasDate(LocalDate date) {
        return (root, query, cb) -> date == null ? null :
                cb.equal(root.get("date"), date);
    }

    public static Specification<OrderEntity> containsProduct(UUID productId) {
        return (root, query, cb) -> {
            if (productId == null) return null;

            Path<Object> products = root.joinMap("products").key();
            return cb.equal(products.get("id"), productId);
        };
    }
}
