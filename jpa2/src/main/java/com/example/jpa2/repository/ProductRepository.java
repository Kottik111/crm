package com.example.jpa2.repository;

import com.example.jpa2.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> , JpaSpecificationExecutor<ProductEntity> {
}
