package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.VetInternSimpleResponseDto;
import java.util.List;

public interface VetInternService {
    List<VetInternSimpleResponseDto> findAllInterns();
}