package com.example.jpa2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "client_entity_id")
    private ClientEntity clientEntity;

    @ElementCollection
    @CollectionTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<ProductEntity, Integer> products = new HashMap<>();

}
