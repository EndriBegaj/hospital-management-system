package dev.endribegaj.hospitalmanagementsystem.models;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    // Getters and setters

    public enum AppointmentStatus {
        SCHEDULED, CANCELED, COMPLETED
    }
}
