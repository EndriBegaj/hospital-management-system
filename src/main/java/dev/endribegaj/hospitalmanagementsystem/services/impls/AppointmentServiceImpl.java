package dev.endribegaj.hospitalmanagementsystem.services.impls;

import dev.endribegaj.hospitalmanagementsystem.models.Appointment;
import dev.endribegaj.hospitalmanagementsystem.repsitories.AppointmentRepository;
import dev.endribegaj.hospitalmanagementsystem.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.repository = appointmentRepository;
    }

    @Override
    public List<Appointment> findAllAppointments() {
        return repository.findAll();
    }

    @Override
    public Appointment findAppointmentById(Long id) {
        Optional<Appointment> appointment = repository.findById(id);
        if (appointment.isPresent()) {
            return appointment.get();
        }
        throw new RuntimeException("Appointment not found for id: " + id);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return repository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
        if (repository.existsById(id)) {
            appointment.setId(id);  // Ensure the correct ID is set
            return repository.save(appointment);
        }
        throw new RuntimeException("Appointment not found for id: " + id);
    }

    @Override
    @Transactional
    public void deleteAppointment(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Appointment not found for id: " + id);
        }
    }
}
