package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.dto.response.VetInternSimpleResponseDto;
import com.colitasfelices.pb.repository.UserRepository;
import com.colitasfelices.pb.services.interfaces.VetInternService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetInternServiceImpl implements VetInternService {

    private final UserRepository userRepository;

    public VetInternServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<VetInternSimpleResponseDto> findAllInterns() {
        return userRepository.findAllInterns();
    }
}
