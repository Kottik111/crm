package com.example.jpa2.service;

import com.example.jpa2.OrderSpecification;
import com.example.jpa2.dto.OrderProductResponseDto;
import com.example.jpa2.dto.OrderRequestDto;
import com.example.jpa2.dto.OrderResponseDto;
import com.example.jpa2.dto.ProductRequestDto;
import com.example.jpa2.entity.ClientEntity;
import com.example.jpa2.entity.OrderEntity;
import com.example.jpa2.entity.ProductEntity;
import com.example.jpa2.exception.ClientNotFoundException;
import com.example.jpa2.exception.OrderNotFoundException;
import com.example.jpa2.exception.ProductNotFoundException;
import com.example.jpa2.mapper.OrderMapper;
import com.example.jpa2.repository.ClientRepository;
import com.example.jpa2.repository.OrderRepository;
import com.example.jpa2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.example.jpa2.mapper.OrderMapper.mapToDto;
import static com.example.jpa2.mapper.OrderMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        Optional<ClientEntity> clientOptional = clientRepository.findById(orderRequestDto.getClientId());
        if (clientOptional.isEmpty()){
            throw new ClientNotFoundException("Client not found");
        }

        OrderEntity orderEntity = mapToEntity(orderRequestDto, clientOptional.get());
        orderRepository.save(orderEntity);
        return mapToDto(orderEntity);
    }

    public OrderResponseDto updateOrderStatusById(UUID id, OrderRequestDto orderRequestDto) {
        Optional<ClientEntity> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()){
            throw new ClientNotFoundException("Client not found");
        }
        OrderEntity orderEntity = mapToEntity(orderRequestDto, clientOptional.get());
        orderEntity.setStatus(orderRequestDto.getStatus());
        return mapToDto(orderRepository.save(orderEntity));
    }

    public void deleteOrderById(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    public Page<OrderResponseDto> getAllOrders(String status, LocalDate date, UUID productId, Pageable pageable) {
        Specification<OrderEntity> spec = Specification.where(
                OrderSpecification.hasStatus(status)
        ).and(
                OrderSpecification.hasDate(date)
        ).and(
                OrderSpecification.containsProduct(productId)
        );

        return orderRepository.findAll(spec, pageable)
                .map(OrderMapper::mapToDto);
    }

    public OrderResponseDto addProductInOrder(UUID orderId, UUID productId,  int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if (orderEntity.isEmpty()){
            throw new OrderNotFoundException("Order not found");
        }
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if (productEntity.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        orderEntity.get().getProducts().merge(productEntity.get(), quantity, Integer::sum);

        orderRepository.save(orderEntity.get());
        return mapToDto(orderEntity.get());
    }

    public OrderResponseDto updateProductQuantityInOrder(UUID orderId, UUID productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }

        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (quantity == 0) {
            order.getProducts().remove(product);
        } else {
            order.getProducts().put(product, quantity);
        }

        orderRepository.save(order);
        return mapToDto(order);
    }

    public List<OrderProductResponseDto> getOrderProducts(UUID orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        List<OrderProductResponseDto> result = new ArrayList<>();

        for (Map.Entry<ProductEntity, Integer> entry : order.getProducts().entrySet()) {
            ProductEntity product = entry.getKey();
            int quantity = entry.getValue();

            OrderProductResponseDto dto = new OrderProductResponseDto();
            dto.setProductId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setPrice(product.getPrice());
            dto.setQuantity(quantity);

            result.add(dto);
        }

        return result;
    }
}
