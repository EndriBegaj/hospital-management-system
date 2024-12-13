package dev.endribegaj.hospitalmanagementsystem.controllers;



import dev.endribegaj.hospitalmanagementsystem.models.Doctor;
import dev.endribegaj.hospitalmanagementsystem.models.Patient;
import dev.endribegaj.hospitalmanagementsystem.services.DoctorService;
import dev.endribegaj.hospitalmanagementsystem.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    public PatientController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Patient> patients = patientService.findAll();
        model.addAttribute("patients", patients);
        System.out.println("Patients list size: " + patientService.findAll().size());

        return "patients/list";
    }

    @GetMapping("/create")
    public String createPatient(Model model) {
        model.addAttribute("patient", new Patient());
        List<Doctor> doctors = doctorService.findAll();
        model.addAttribute("doctors", doctors);
        return "patients/create";
    }

    @GetMapping("/{id}/edit")
    public String showEditPatientForm(@PathVariable long id, Model model) {
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "patients/edit";
    }
    @GetMapping("/{id}/details")
    public String showPatientDetails(@PathVariable long id, Model model) {
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "patients/details";
    }

    @PostMapping("/create")
    public String addPatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "patients/create";
        }
        var newPatient = patientService.add(patient);
        if (newPatient == null) {
            bindingResult.rejectValue("personalNo", "error.driver", "Patient already exists with that personal number");
            bindingResult.rejectValue("email", "error.driver", "Patient already exists with that email");
            return "patients/create";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Patient added successfully");
        return "redirect:/patients";
    }







}