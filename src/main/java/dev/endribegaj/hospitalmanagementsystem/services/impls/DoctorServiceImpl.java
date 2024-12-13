package dev.endribegaj.hospitalmanagementsystem.services.impls;

import dev.endribegaj.hospitalmanagementsystem.models.Doctor;
import dev.endribegaj.hospitalmanagementsystem.repsitories.DoctorRepository;
import dev.endribegaj.hospitalmanagementsystem.services.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Doctor> findAll() {
        return repository.findAll();
    }

    @Override
    public Doctor findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Doctor add(Doctor doctor) {
        // Check if doctor has an id. If it does, ensure it's not already in use.
        if (doctor.getId() != null) {
            var existingDoctor = findById(doctor.getId());
            if (existingDoctor != null) {
                System.out.println("Doctor with this id exists: " + doctor.getId());
                return null;
            }
        }

        // Check for duplicate personal number or email
        long totalDoctors = repository.countAllByPersonalNoOrEmail(doctor.getPersonalNo(), doctor.getEmail());
        if (totalDoctors > 0) {
            System.out.println("Doctor with this personal number or email exists: " + doctor.getPersonalNo() + " or " + doctor.getEmail());
            return null;
        }

        // Save new doctor to the database
        return repository.save(doctor);
    }

    @Override
    public Doctor modify(Doctor doctor) {
        // Check if doctor ID is null or doctor doesn't exist
        if (doctor.getId() == null || findById(doctor.getId()) == null) {
            System.out.println("Doctor with this id does not exist: " + doctor.getId());
            return null;
        }

        // Save modified doctor details
        return repository.save(doctor);
    }

    @Override
    public void deleteById(long id) {
        var existingDoctor = findById(id);

        if (existingDoctor == null) {
            System.out.println("Doctor with this id does not exist: " + id);
            return;
        }
        repository.deleteById(id);
    }

    @Override
    public List<Doctor> findBySpecialization(String specialization) {
        return List.of();
    }
}