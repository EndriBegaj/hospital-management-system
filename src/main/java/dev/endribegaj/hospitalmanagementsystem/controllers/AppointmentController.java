/*
package dev.endribegaj.hospitalmanagementsystem.controllers;

import dev.endribegaj.hospitalmanagementsystem.models.Appointment;
import dev.endribegaj.hospitalmanagementsystem.models.Doctor;
import dev.endribegaj.hospitalmanagementsystem.models.Patient;
import dev.endribegaj.hospitalmanagementsystem.services.AppointmentService;
import dev.endribegaj.hospitalmanagementsystem.services.DoctorService;
import dev.endribegaj.hospitalmanagementsystem.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    // Show the create appointment and patient form (GET request)
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Patient> patients = patientService.getAllPatients(); // Get all patients
        List<Doctor> doctors = doctorService.getAllDoctors(); // Get all doctors
        model.addAttribute("patients", patients); // Pass patients to the view
        model.addAttribute("doctors", doctors); // Pass doctors to the view
        model.addAttribute("appointment", new Appointment()); // Empty appointment object for form
        return "appointments/create"; // Return the Thymeleaf template for the form
    }

    // Handle the creation of a new appointment (POST request)
    @PostMapping
    public String createAppointment(@RequestParam Long patientId, @RequestParam Long doctorId, @RequestParam String appointmentDate) {
        Patient patient = patientService.findById(patientId); // Get the patient by ID
        Doctor doctor = doctorService.findById(doctorId); // Get the doctor by ID

        // Define the date format and parse the appointment date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime date = LocalDateTime.parse(appointmentDate, formatter); // Convert the appointment date string to LocalDateTime

        // Create a new Appointment object and set its properties
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(date);
        appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED); // Set default status

        // Save the appointment
        appointmentService.saveAppointment(appointment);

        // Redirect to the list of appointments after saving
        return "redirect:/appointments";
    }

    // Show a list of all appointments (GET request)
    @GetMapping
    public String listAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments(); // Get all appointments
        model.addAttribute("appointments", appointments); // Pass the list of appointments to the view
        return "appointments/list"; // Return the Thymeleaf template that lists appointments
    }

    // Handle the cancellation of an appointment (POST request)
    @PostMapping("/{id}/cancel")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id); // Cancel the appointment by its ID
        return "redirect:/appointments"; // Redirect to the list of appointments after cancellation
    }

    // Handle patient creation (POST request)
    @PostMapping("/patients")
    public String createPatient(@ModelAttribute Patient patient) {
        patientService.add(patient); // Save the new patient
        return "redirect:/appointments/create"; // Redirect back to the create appointment page
    }
}
*/
