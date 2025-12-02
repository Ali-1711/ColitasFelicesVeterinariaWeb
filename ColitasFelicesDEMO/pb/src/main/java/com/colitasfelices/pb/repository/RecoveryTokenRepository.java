package com.colitasfelices.pb.repository;

import com.colitasfelices.pb.entity.RecoveryToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecoveryTokenRepository extends JpaRepository<RecoveryToken, Long> {
    Optional<RecoveryToken> findByCorreoAndCodigoAndUsadoFalse(String correo, String codigo);
}
