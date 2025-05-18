package com.example.jpa2.repository;

import ch.qos.logback.core.net.server.Client;
import com.example.jpa2.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> , JpaSpecificationExecutor<ClientEntity> {
    boolean existsByEmail(String email);
}
