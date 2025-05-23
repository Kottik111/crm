package com.example.jpa2.mapper;

import com.example.jpa2.dto.OrderRequestDto;
import com.example.jpa2.dto.OrderResponseDto;
import com.example.jpa2.dto.ProductWithQuantity;
import com.example.jpa2.entity.ClientEntity;
import com.example.jpa2.entity.OrderEntity;
import com.example.jpa2.entity.ProductEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderMapper {
    public  OrderEntity toEntity(OrderRequestDto orderRequestDto, ClientEntity clientEntity) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus(orderRequestDto.getStatus());
        orderEntity.setDate(LocalDate.now());
        orderEntity.setClientEntity(clientEntity);
        return orderEntity;
    }

    public  OrderResponseDto toDto(OrderEntity orderEntity) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(orderEntity.getId());
        orderResponseDto.setStatus(orderEntity.getStatus());
        orderResponseDto.setDate(orderEntity.getDate());
        orderResponseDto.setClientInfo(orderEntity.getClientEntity().getName() + " " + orderEntity.getClientEntity().getSurname());

        List<ProductWithQuantity> newProduct = new ArrayList<>();

        for(Map.Entry<ProductEntity, Integer> product : orderEntity.getProducts().entrySet()) {
            ProductEntity productEntity = product.getKey();
            Integer quantity = product.getValue();
            ProductWithQuantity productWithQuantity = new ProductWithQuantity();
            productWithQuantity.setProductId(productEntity.getId());
            productWithQuantity.setProductName(productEntity.getName());
            productWithQuantity.setQuantity(quantity);
            newProduct.add(productWithQuantity);
        }
        orderResponseDto.setProduct(newProduct);
        return orderResponseDto;
    }
}
