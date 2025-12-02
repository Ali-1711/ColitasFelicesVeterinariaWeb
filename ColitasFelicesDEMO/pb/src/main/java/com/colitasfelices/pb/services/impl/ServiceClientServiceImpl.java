package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.dto.response.ServiceResponseDto;
import com.colitasfelices.pb.entity.Servicio;
import com.colitasfelices.pb.repository.ServiceRepository;
import com.colitasfelices.pb.services.interfaces.ServiceClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceClientServiceImpl implements ServiceClientService {

    private final ServiceRepository serviceRepository;

    @Override
    public List<ServiceResponseDto> getAllActiveServices() {
        List<Servicio> servicios = serviceRepository.findByEstadoTrue();
        return servicios.stream()
                .map(servicio -> new ServiceResponseDto(servicio.getId(), servicio.getDescripcion()))
                .collect(Collectors.toList());
    }
}
