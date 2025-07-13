package com.example.jpa2.repository;

import com.example.jpa2.entity.OrderEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID>, JpaSpecificationExecutor<OrderEntity> {
    @EntityGraph(attributePaths = {"clientEntity", "products"})
    Page<OrderEntity> findAll(Specification<OrderEntity> spec, Pageable pageable);

    @EntityGraph(attributePaths = {"clientEntity", "products"})
    Optional<OrderEntity> findById(UUID id);
}
