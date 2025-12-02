package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.VeterinarianResponseDto;

import java.util.List;

public interface VeterinarianClientService {
    List<VeterinarianResponseDto> getAllVeterinarians();
}
