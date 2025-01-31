package dev.endribegaj.hospitalmanagementsystem.services;

import dev.endribegaj.hospitalmanagementsystem.models.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> findAllAppointments();
    Appointment findAppointmentById(Long id);
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
}
