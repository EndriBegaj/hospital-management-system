package dev.endribegaj.hospitalmanagementsystem.services.impls;


import dev.endribegaj.hospitalmanagementsystem.models.Patient;
import dev.endribegaj.hospitalmanagementsystem.repsitories.PatientRepository;
import dev.endribegaj.hospitalmanagementsystem.services.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll();
    }

    @Override
    public Patient findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Patient add(Patient patient) {
        if (patient.getId() != null) {
            var existingPatient = findById(patient.getId());
            if (existingPatient != null) {
                System.out.println("Patient with this id exists: " + patient.getId());
                return null;
            }
        }
        long totalPatients = repository.countAllByPersonalNoOrEmail(patient.getPersonalNo(), patient.getEmail());
        if (totalPatients > 0) {
            System.out.println("Patient with this personal number or email exists: " + patient.getPersonalNo() + " or " + patient.getEmail());
            return null;

        }
        return repository.save(patient);
    }


    @Override
    public Patient modify(Patient patient) {
        if(patient.getId() == null || findById(patient.getId()) == null) {
            System.out.println("Patient with this id does not exist: " + patient.getId());
            return null;
        }
        return repository.save(patient);
    }

    @Override
    public void deleteById(long id) {
        var existingPatient = findById(id);

        if (existingPatient == null) {

            System.out.println("Patient with this id does not exist: " + id);
            return;
        }

        repository.deleteById(id);

    }


}