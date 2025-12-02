package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.dto.request.AdminCreateUserRequestDto;
import com.colitasfelices.pb.dto.request.AdminUpdateUserRequestDto;
import com.colitasfelices.pb.dto.response.AdminUserResponseDto;
import com.colitasfelices.pb.entity.Role;
import com.colitasfelices.pb.entity.TipoDocumento;
import com.colitasfelices.pb.entity.User;
import com.colitasfelices.pb.exception.ResourceNotFoundException;
import com.colitasfelices.pb.repository.UserRepository;
import com.colitasfelices.pb.repository.RoleRepository;
import com.colitasfelices.pb.repository.TipoDocumentoRepository;
import com.colitasfelices.pb.services.interfaces.AdminUserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final PasswordEncoder passwordEncoder;

   @Override
public List<AdminUserResponseDto> listarUsuariosPorRol(String rolNombre) {
    Long roleId = null;
    if (rolNombre != null && !rolNombre.equalsIgnoreCase("ALL")) {
        Optional<Role> role = roleRepository.findByNombre(rolNombre.toUpperCase());
        if (role.isPresent()) {
            roleId = role.get().getId();
        } else {
            return List.of(); // Si no se encuentra el rol, retornamos lista vacía
        }
    }

    return userRepository.findAllByOptionalRoleId(roleId).stream()
            .map(user -> new AdminUserResponseDto(
                user.getId(),
                user.getNombres() + " " + user.getApellidoPaterno() + " " + user.getApellidoMaterno(),
                user.getCorreo(),
                traducirRol(user.getRole().getNombre()), // 👈 Rol traducido al español
                user.getRole().getId().intValue(),
                user.getEstado(),
                user.getMotivoDesactivacion()
            ))
            .collect(Collectors.toList());
}

    @Override
    @Transactional
    public void toggleEstadoUsuario(Long id, String motivo) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        boolean nuevoEstado = !user.getEstado();
        user.setEstado(nuevoEstado);

        if (!nuevoEstado && motivo != null) {
            user.setMotivoDesactivacion(motivo);
        } else {
            user.setMotivoDesactivacion(null);
        }

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void crearUsuario(AdminCreateUserRequestDto dto) {
        if (userRepository.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        Role rol = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no válido"));

        TipoDocumento tipoDoc = tipoDocumentoRepository.findById(dto.getTipoDocumentoId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de documento inválido"));

        User user = new User();
        user.setNombres(dto.getNombres());
        user.setApellidoPaterno(dto.getApellidoPaterno());
        user.setApellidoMaterno(dto.getApellidoMaterno());
        user.setNumeroIdentidad(dto.getNumeroIdentidad());
        user.setTipoDocumento(tipoDoc);
        user.setTelefono(dto.getTelefono());
        user.setFechaNacimiento(dto.getFechaNacimiento());
        user.setSexo(dto.getSexo());
        user.setDireccion(dto.getDireccion());
        user.setCorreo(dto.getCorreo());
        user.setRole(rol);
        user.setEstado(true);
        user.setContrasena(passwordEncoder.encode(dto.getContrasena())); // Contraseña por defecto

        userRepository.save(user);
    }

    @Override
    public void actualizarUsuario(Long id, AdminUpdateUserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        user.setNombres(dto.getNombre());

        if (dto.getRoleId() != null) {
            Role nuevoRol = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
            user.setRole(nuevoRol);
        }

        userRepository.save(user);
    }

    @Override
    public void desactivarUsuarioConMotivo(Long id, String motivo) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setEstado(false);
        user.setMotivoDesactivacion(motivo); // asegúrate que este campo exista en la entidad
        userRepository.save(user);
    }


    private String traducirRol(String rolIngles) {
    return switch (rolIngles.toUpperCase()) {
        case "ADMIN" -> "Administrador";
        case "CLIENT" -> "Cliente";
        case "VETERINARIAN" -> "Veterinario";
        case "INTERN" -> "Interno";
        default -> rolIngles;
    };
}

}
