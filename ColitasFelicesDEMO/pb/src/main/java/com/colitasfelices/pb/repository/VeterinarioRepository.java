package com.colitasfelices.pb.repository;

import com.colitasfelices.pb.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    // Devuelve veterinarios con estado activo (true)
    List<Veterinario> findByEstadoTrue();
    Optional<Veterinario> findByUsuarioCorreo(String correo);
    Optional<Veterinario> findByUsuarioId(Long usuarioId);

}
