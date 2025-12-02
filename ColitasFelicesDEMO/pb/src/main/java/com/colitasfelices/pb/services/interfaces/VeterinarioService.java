package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.VeterinarianListResponseDto;
import java.util.List;

public interface VeterinarioService {
    List<VeterinarianListResponseDto> getVeterinariosConDetalles();
}
