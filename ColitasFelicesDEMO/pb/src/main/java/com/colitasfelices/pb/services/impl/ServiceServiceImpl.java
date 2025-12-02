package com.colitasfelices.pb.services.impl;
import com.colitasfelices.pb.entity.Servicio;
import com.colitasfelices.pb.repository.ServiceRepository;
import com.colitasfelices.pb.services.interfaces.ServiceService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Servicio> obtenerTodos() {
        return serviceRepository.findByEstadoTrue();
    }
}

