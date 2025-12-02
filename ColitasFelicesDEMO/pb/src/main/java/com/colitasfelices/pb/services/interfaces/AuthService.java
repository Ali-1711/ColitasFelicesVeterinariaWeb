package com.colitasfelices.pb.services.interfaces;
import com.colitasfelices.pb.dto.request.LoginRequestDto;
import com.colitasfelices.pb.dto.request.RegisterInternDto;
import com.colitasfelices.pb.dto.request.RegisterUserDto;
import com.colitasfelices.pb.dto.request.RegisterVeterinarianDto;
import com.colitasfelices.pb.dto.response.LoginResponseDto;

public interface AuthService {
    void registerClient(RegisterUserDto dto);
    LoginResponseDto login(LoginRequestDto dto);
    void registerVeterinarian(RegisterVeterinarianDto dto);
    void registerIntern(RegisterInternDto dto);
}