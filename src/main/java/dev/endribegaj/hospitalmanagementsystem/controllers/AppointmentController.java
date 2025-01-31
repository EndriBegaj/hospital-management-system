package dev.endribegaj.hospitalmanagementsystem.controllers;

import dev.endribegaj.hospitalmanagementsystem.services.AppointmentService;

import dev.endribegaj.hospitalmanagementsystem.services.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {



    private final AppointmentService service;
    private final DoctorService doctorService;

    public AppointmentController(AppointmentService service, DoctorService doctorService) {
        this.service = service;
        this.doctorService = doctorService;
    }

    @GetMapping("")
    public String doctors(Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("appointments", service.findAllAppointments());
        return "appoiments/list";
    }


}