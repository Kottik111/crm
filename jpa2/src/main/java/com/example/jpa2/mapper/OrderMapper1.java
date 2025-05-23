package com.example.jpa2.mapper;

import com.example.jpa2.dto.OrderRequestDto;
import com.example.jpa2.dto.OrderResponseDto;
import com.example.jpa2.dto.ProductWithQuantity;
import com.example.jpa2.entity.OrderEntity;
import com.example.jpa2.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Mapper(componentModel = "spring")
public interface OrderMapper1 {
    OrderEntity toEntity(OrderRequestDto orderRequestDto);
    @Mapping(source = "products", target = "products", qualifiedByName = "mapToProductWithQuantity")
    OrderResponseDto toDto(OrderEntity orderEntity);


    @Named("mapToProductWithQuantity")
    default List<ProductWithQuantity> mapToProductWithQuantity(Map<ProductEntity, Integer> products) {
        if (products == null) return new ArrayList<>();

        List<ProductWithQuantity> list = new ArrayList<>();
        for (Map.Entry<ProductEntity, Integer> product : products.entrySet()) {
            ProductWithQuantity pwq = new ProductWithQuantity();
            pwq.setProductId(product.getKey().getId());
            pwq.setProductName(product.getKey().getName());
            pwq.setQuantity(product.getValue());
            list.add(pwq);
        }
        return list;
    }
}
