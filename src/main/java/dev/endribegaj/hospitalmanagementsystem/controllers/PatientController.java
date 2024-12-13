package dev.endribegaj.hospitalmanagementsystem.controllers;


import dev.endribegaj.hospitalmanagementsystem.models.Patient;
import dev.endribegaj.hospitalmanagementsystem.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("")
    public String patients(Model model) {
        List<Patient> patients =  service.findAll();
        model.addAttribute("patients", patients);
        return "patients/list";
    }




}