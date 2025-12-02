package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.ServiceResponseDto;

import java.util.List;

public interface ServiceClientService {
    List<ServiceResponseDto> getAllActiveServices();
}
