package com.example.jpa2.mapperInterf;

import com.example.jpa2.dto.ClientRequestDto;
import com.example.jpa2.dto.ClientResponseDto;
import com.example.jpa2.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderMapper1.class)
public interface ClientMapper1 {
    ClientEntity toEntity(ClientRequestDto clientRequestDto);
    @Mapping(source = "orders", target = "order")
    ClientResponseDto toDto(ClientEntity clientEntity);

}
