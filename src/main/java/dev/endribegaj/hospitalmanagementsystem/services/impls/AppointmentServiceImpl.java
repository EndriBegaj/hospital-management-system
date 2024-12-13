/*
package dev.endribegaj.hospitalmanagementsystem.services.impls;

import dev.endribegaj.hospitalmanagementsystem.models.Appointment;
import dev.endribegaj.hospitalmanagementsystem.models.Doctor;
import dev.endribegaj.hospitalmanagementsystem.models.Patient;
import dev.endribegaj.hospitalmanagementsystem.repsitories.AppointmentRepository;
import dev.endribegaj.hospitalmanagementsystem.services.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment createAppointment(Patient patient, Doctor doctor, LocalDateTime appointmentDate) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED);
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAllAppointments(Long patientId, Long doctorId) {
        return appointmentRepository.findByPatientIdAndDoctorId(patientId, doctorId);  // Filters by patient and doctor
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, startDate, endDate);
    }

    @Override
    public Appointment updateAppointmentStatus(Long appointmentId, Appointment.AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(Appointment.AppointmentStatus.CANCELED);
        appointmentRepository.save(appointment);
    }
}
*/
