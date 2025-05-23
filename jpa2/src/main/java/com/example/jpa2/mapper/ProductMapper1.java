package com.example.jpa2.mapper;

import com.example.jpa2.dto.ProductRequestDto;
import com.example.jpa2.dto.ProductResponseDto;
import com.example.jpa2.entity.ProductEntity;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface ProductMapper1 {
    ProductEntity toEntity(ProductRequestDto productRequestDto);
    ProductResponseDto toDto(ProductEntity productEntity);
}
