package com.example.jpa2.controller;


import com.example.jpa2.dto.OrderProductResponseDto;
import com.example.jpa2.dto.OrderRequestDto;
import com.example.jpa2.dto.OrderResponseDto;
import com.example.jpa2.dto.ProductRequestDto;
import com.example.jpa2.service.OrderService;
import com.example.jpa2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @PostMapping("/orders")
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.addOrder(orderRequestDto);
    }

    @PutMapping("/orders/{id}")
    public OrderResponseDto updateOrderStatusById(@PathVariable UUID id, @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.updateOrderStatusById(id, orderRequestDto);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrderById(@PathVariable UUID id) {
        orderService.deleteOrderById(id);
    }

    @GetMapping("/orders")
    public Page<OrderResponseDto> getAllOrders( @RequestParam(required = false) String status,
                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                @RequestParam(required = false) UUID productId,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "date") String sortField,
                                                @RequestParam(defaultValue = "desc") String sortDirection) {

        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        return orderService.getAllOrders(status, date, productId, pageable);
    }

    @PostMapping("/orders/{orderId}/products/{productId}")
    public OrderResponseDto addProductToOrder(@PathVariable UUID orderId, @PathVariable UUID productId, @RequestParam(defaultValue = "1") int quantity) {
         return orderService.addProductInOrder(orderId, productId, quantity);
    }

    @PutMapping("/orders/{orderId}/products/{productId}")
    public OrderResponseDto updateProductQuantityInOrder(
            @PathVariable UUID orderId,
            @PathVariable UUID productId,
            @RequestParam int quantity) {
        return orderService.updateProductQuantityInOrder(orderId, productId, quantity);
    }

    @GetMapping("/orders/{orderId}/products")
    public List<OrderProductResponseDto> getOrderProducts(@PathVariable UUID orderId) {
        return orderService.getOrderProducts(orderId);
    }
}
