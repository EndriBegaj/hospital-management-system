package dev.endribegaj.hospitalmanagementsystem.services;

import dev.endribegaj.hospitalmanagementsystem.models.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();

    Doctor findById(long id);
    Doctor add(Doctor doctor);
    Doctor modify(Doctor doctor);
    void deleteById(long id);

    List<Doctor> findBySpecialization(String specialization);

}