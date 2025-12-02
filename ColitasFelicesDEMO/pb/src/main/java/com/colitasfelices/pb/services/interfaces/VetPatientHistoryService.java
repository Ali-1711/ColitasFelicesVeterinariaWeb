package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.VetClientWithPetResponseDto;
import com.colitasfelices.pb.dto.response.VetClinicalRecordResponseDto;
import com.colitasfelices.pb.dto.response.VetPatientListResponseDto;

import java.util.List;

public interface VetPatientHistoryService {
    List<VetPatientListResponseDto> getAllPatientsWithHistory();
    List<VetClinicalRecordResponseDto> getClinicalHistoryByPet(Long petId);
    List<VetClientWithPetResponseDto> obtenerClientesAtendidos(String correoVeterinario);
    
}
