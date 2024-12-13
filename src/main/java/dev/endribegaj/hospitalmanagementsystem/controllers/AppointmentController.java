/*
package dev.endribegaj.hospitalmanagementsystem.controllers;

import dev.endribegaj.hospitalmanagementsystem.models.Appointment;
import dev.endribegaj.hospitalmanagementsystem.services.AppointmentService;
import dev.endribegaj.hospitalmanagementsystem.services.DoctorService;
import dev.endribegaj.hospitalmanagementsystem.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    // Show the form to create an appointment
    @GetMapping("/create")
    public String showCreateAppointmentForm(Model model) {
        model.addAttribute("doctors", doctorService.findAll()); // Fetch doctors
        model.addAttribute("patients", patientService.findAll()); // Fetch patients
        model.addAttribute("appointment", new Appointment()); // Empty appointment object
        return "appointments/create"; // Thymeleaf template
    }

    // Handle form submission
    @PostMapping("/create")
    public String createAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.createAppointment(appointment);
        return "redirect:/appointments/list"; // Redirect to list view
    }
}
*/
