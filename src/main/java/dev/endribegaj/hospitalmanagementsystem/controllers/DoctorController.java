package dev.endribegaj.hospitalmanagementsystem.controllers;


import dev.endribegaj.hospitalmanagementsystem.models.Doctor;
import dev.endribegaj.hospitalmanagementsystem.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;


    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Doctor> doctors = service.findAll();
        System.out.println("Doctors list size: " + doctors.size());  // Check if there are doctors
        model.addAttribute("doctors", doctors);
        return "doctors/list";
    }



    @GetMapping("/{id}/details")
    public String doctorDetails(@PathVariable long id, Model model) {
        var doctor = service.findById(id);
        model.addAttribute("doctor", doctor);
        return "doctors/details";
    }

    @GetMapping("/create")
    public String createDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("from", LocalDate.now().minusYears(65));
        model.addAttribute("today", LocalDate.now());
        return "doctors/create";
    }

    @GetMapping("/{id}/edit")
    public String editDoctor(@PathVariable long id, Model model) {
        var doctor = service.findById(id);
        model.addAttribute("doctor", doctor);
        return "doctors/edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable long id, Model model) {
        var doctor = service.findById(id);
        model.addAttribute("doctor", doctor);
        return "doctors/delete";
    }






    @PostMapping("/create")
    public String addDoctor(@Valid @ModelAttribute Doctor doctor, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "doctors/create";
        }
        var newDoctor = service.add(doctor);
        if (newDoctor == null) {
            bindingResult.rejectValue("personalNo", "error.driver", "Driver already exists with that personal number");
            bindingResult.rejectValue("email", "error.driver", "Driver already exists with that email");
            return "doctors/create";

        }
        redirectAttributes.addFlashAttribute("successMessage", "Driver added successfully");
        return "redirect:/doctors";


    }

    @PostMapping("/{id}/edit")
    public String modifyDoctor(@PathVariable long id, @Valid @ModelAttribute Doctor doctor, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (doctor.getId() != id) {
            redirectAttributes.addAttribute("errorId","DR404");
            redirectAttributes.addFlashAttribute("errorMessage", "Doctor ID does not match");
            return "redirect:/doctors";
        }
        service.modify(doctor);
        return "redirect:/doctors";
    }


    @PostMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable long id) {
        service.deleteById(id);
        return "redirect:/doctors";
    }






}