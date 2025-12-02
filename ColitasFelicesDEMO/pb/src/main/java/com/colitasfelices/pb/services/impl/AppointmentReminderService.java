package com.colitasfelices.pb.services.impl;
import com.colitasfelices.pb.entity.Cita;
import com.colitasfelices.pb.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service

public class AppointmentReminderService {
   
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CitaRepository citaRepository;

 
    // Enviar correo de recordatorio
    private void sendAppointmentReminderEmail(Cita cita) {
        String userEmail = cita.getUsuario().getCorreo();
        String veterinarioName = cita.getVeterinario().getNombreCompleto();
        String date = cita.getFecha().toString();
        String time = cita.getHora().toString();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Recordatorio de Cita - Puente Blanco");
        message.setText("Estimado cliente,\n\n" +
                        "Le recordamos que su cita está agendada para el " + date +
                        " a las " + time + " con el veterinario " + veterinarioName +
                        ".\n\nGracias por elegirnos.\n\nAtentamente,\nClínica y Veterinaria Puente Blanco");

        mailSender.send(message);
    }
}