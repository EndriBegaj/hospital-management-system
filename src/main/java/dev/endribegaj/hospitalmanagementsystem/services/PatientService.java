package dev.endribegaj.hospitalmanagementsystem.services;



import dev.endribegaj.hospitalmanagementsystem.models.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> findAll();

    Patient findById(long id);

    Patient add(Patient patient);

    Patient modify(Patient patient);

    void deleteById(long id);

}