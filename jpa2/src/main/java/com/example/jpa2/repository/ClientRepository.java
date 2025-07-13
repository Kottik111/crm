package com.example.jpa2.repository;

import ch.qos.logback.core.net.server.Client;
import com.example.jpa2.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> , JpaSpecificationExecutor<ClientEntity> {

    @EntityGraph(attributePaths = {"orders"})
    Page<ClientEntity> findAll(Specification<ClientEntity> spec, Pageable pageable);

    @EntityGraph(attributePaths = {"orders"})
    Optional<ClientEntity> findById(UUID id);

    boolean existsByEmail(String email);
}

