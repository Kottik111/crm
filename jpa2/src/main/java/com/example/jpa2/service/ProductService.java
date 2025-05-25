package com.example.jpa2.service;

import com.example.jpa2.ProductSpecification;
import com.example.jpa2.dto.ProductRequestDto;
import com.example.jpa2.dto.ProductResponseDto;
import com.example.jpa2.entity.ProductEntity;
import com.example.jpa2.exception.ProductNotFoundException;
import com.example.jpa2.mapper.ProductMapper;
import com.example.jpa2.mapper.ProductMapper1;
import com.example.jpa2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper1 productMapper1;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = productMapper1.toEntity(productRequestDto);
        productRepository.save(productEntity);
        return productMapper1.toDto(productEntity);
    }

    public ProductResponseDto updateProduct(UUID id, ProductRequestDto productRequestDto) {
        Optional<ProductEntity> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        ProductEntity productEntity = productOptional.get();
        productEntity.setName(productRequestDto.getName());
        productEntity.setDescription(productRequestDto.getDescription());
        productEntity.setPrice(productRequestDto.getPrice());
        return productMapper1.toDto(productRepository.save(productEntity));
    }

    public void deleteProduct(@PathVariable UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }


    public Page<ProductResponseDto> getAllProduct(String name, Double minPrice, Double maxPrice, Pageable pageable) {
        Specification<ProductEntity> spec = Specification.where(
                ProductSpecification.hasName(name)
        ).and(
                ProductSpecification.hasMinPrice(minPrice)
        ).and(
                ProductSpecification.hasMaxPrice(maxPrice)
        );

        return productRepository.findAll(spec, pageable)
                .map(productMapper1::toDto);
    }


}
