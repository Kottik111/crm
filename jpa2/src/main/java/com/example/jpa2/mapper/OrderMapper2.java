package com.example.jpa2.mapper;

/*
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
        orderResponseDto.setProducts(newProduct);
        return orderResponseDto;
    }
}

 */
