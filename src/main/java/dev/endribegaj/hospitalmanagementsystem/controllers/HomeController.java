package dev.endribegaj.hospitalmanagementsystem.controllers;


import dev.endribegaj.hospitalmanagementsystem.models.Appointment;
import dev.endribegaj.hospitalmanagementsystem.services.AppointmentService;
import dev.endribegaj.hospitalmanagementsystem.services.DoctorService;

import dev.endribegaj.hospitalmanagementsystem.services.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

public class HomeController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService reservationService;



    public HomeController(DoctorService doctorService, PatientService patientService, AppointmentService reservationService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.reservationService = reservationService;
    }



    @GetMapping("")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("appointments", reservationService.findAllAppointments()); // Add this line
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        return "page/index";
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "index";
    }

    @PostMapping("/page/index")
    public String handleSubmit(@ModelAttribute Appointment reservation) {

        reservationService.createAppointment(reservation);
        return "redirect:/page/index";
    }


}
