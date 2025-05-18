package com.example.jpa2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;//phone_number
    @OneToMany(mappedBy = "clientEntity")
    private List<OrderEntity> orders = new ArrayList<>();

}
