package dev.endribegaj.hospitalmanagementsystem.services;

import dev.endribegaj.hospitalmanagementsystem.models.Appointment;
import dev.endribegaj.hospitalmanagementsystem.models.Doctor;
import dev.endribegaj.hospitalmanagementsystem.models.Patient;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Patient patient, Doctor doctor, LocalDateTime appointmentDate);

    List<Appointment> getAppointmentsByDoctor(Long doctorId);

    List<Appointment> getAppointmentsByPatient(Long patientId);

    List<Appointment> getAllAppointments(Long patientId, Long doctorId);  // Keep the parameters

    List<Appointment> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    Appointment updateAppointmentStatus(Long appointmentId, Appointment.AppointmentStatus status);

    void cancelAppointment(Long appointmentId);
}
