package com.example.jpa2.service;

import com.example.jpa2.specification.ClientSpecification;
import com.example.jpa2.dto.ClientRequestDto;
import com.example.jpa2.dto.ClientResponseDto;
import com.example.jpa2.entity.ClientEntity;
import com.example.jpa2.exception.ClientAlreadyExistsException;
import com.example.jpa2.exception.ClientNotFoundException;
import com.example.jpa2.mapperInterf.ClientMapper1;
import com.example.jpa2.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper1 clientMapper;

    public ClientResponseDto addClient(ClientRequestDto dto){
        ClientEntity client= clientMapper.toEntity(dto);
        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new ClientAlreadyExistsException("Клиент с таким email уже существует");
        }
        clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    public ClientResponseDto updateClient( UUID id, ClientRequestDto dto) {
        Optional<ClientEntity> client = clientRepository.findById(id);
        if (client.isEmpty()){
            throw new ClientNotFoundException("Клиент с таким id не найден");
        }
        client.get().setName(dto.getName());
        client.get().setSurname(dto.getSurname());
        client.get().setEmail(dto.getEmail());
        client.get().setPhoneNumber(dto.getPhoneNumber());
        return clientMapper.toDto(clientRepository.save(client.get()));
    }

    public void deleteClient(UUID id){
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Клиент с таким id не найден");
        }
        clientRepository.deleteById(id);
    }

    public Page<ClientResponseDto> getAllClients(String name, String surname, String email, Pageable pageable) {
        Specification<ClientEntity> spec = Specification.where(
                ClientSpecification.hasName(name)
        ).and(
                ClientSpecification.hasSurname(surname)
        ).and(
                ClientSpecification.containsEmail(email)
        );
        return clientRepository.findAll(spec, pageable)
                .map(clientMapper::toDto);
    }

    public ClientResponseDto getClientById(UUID id){
        Optional<ClientEntity> client = clientRepository.findById(id);
        if (client.isEmpty()){
            throw new ClientNotFoundException("Клиент с таким id не найден");
        }
        return clientMapper.toDto(client.get());
    }
}
