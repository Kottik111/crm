package com.example.jpa2.mapper;

import com.example.jpa2.dto.ClientRequestDto;
import com.example.jpa2.dto.ClientResponseDto;
import com.example.jpa2.entity.ClientEntity;

public class ClientMapper {
    public static ClientEntity mapToEntity(ClientRequestDto requestDto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(requestDto.getName());
        entity.setSurname(requestDto.getSurname());
        entity.setEmail(requestDto.getEmail());
        entity.setPhoneNumber(requestDto.getPhoneNumber());
        return entity;
    }

    public static ClientResponseDto mapToResponse(ClientEntity entity) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}
