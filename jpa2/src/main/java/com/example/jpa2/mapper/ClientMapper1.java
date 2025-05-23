package com.example.jpa2.mapper;

import com.example.jpa2.dto.ClientRequestDto;
import com.example.jpa2.dto.ClientResponseDto;
import com.example.jpa2.entity.ClientEntity;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring", uses = OrderMapper1.class)
public interface ClientMapper1 {
    ClientEntity toEntity(ClientRequestDto clientRequestDto);
    ClientResponseDto toDto(ClientEntity clientEntity);

}
