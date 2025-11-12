package com.puenteblanco.pb;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    @Test
    void generarPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "Alisa12345!";
        String hashed = encoder.encode(password);
        System.out.println("Contraseña encriptada: " + hashed);
    }
}
