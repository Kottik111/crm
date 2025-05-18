package com.example.jpa2;

import com.example.jpa2.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {
    public static Specification<EmployeeEntity> hasName(String name) {
        return (root, query, cb) -> name == null
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
    public static Specification<EmployeeEntity> hasSurname(String surname) {
        return (root, query, cb) -> surname == null
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("surname")), "%" + surname.toLowerCase() + "%");
    }
    public static Specification<EmployeeEntity> containsEmail(String email) {
        return (root, query, cb) -> email == null
                ? cb.conjunction()
                : cb.equal(root.get("email"), email);
    }
}
