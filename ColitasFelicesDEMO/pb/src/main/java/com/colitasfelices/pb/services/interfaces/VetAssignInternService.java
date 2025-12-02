package com.colitasfelices.pb.services.interfaces;

public interface VetAssignInternService {
    void assignInternToAppointment(Long citaId, Long internId, String correoVeterinario);
}