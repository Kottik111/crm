package com.example.jpa2.mapper;

import com.example.jpa2.dto.ProductRequestDto;
import com.example.jpa2.dto.ProductResponseDto;
import com.example.jpa2.entity.ProductEntity;

public class ProductMapper {

    public  ProductEntity toEntity(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productRequestDto.getName());
        productEntity.setDescription(productRequestDto.getDescription());
        productEntity.setPrice(productRequestDto.getPrice());
        return productEntity;
    }

    public  ProductResponseDto toDto(ProductEntity productEntity) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(productEntity.getId());
        productResponseDto.setName(productEntity.getName());
        productResponseDto.setDescription(productEntity.getDescription());
        productResponseDto.setPrice(productEntity.getPrice());
        return productResponseDto;
    }
}
