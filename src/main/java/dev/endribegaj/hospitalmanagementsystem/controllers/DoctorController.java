package dev.endribegaj.hospitalmanagementsystem.controllers;


import dev.endribegaj.hospitalmanagementsystem.helpers.FileHelper;
import dev.endribegaj.hospitalmanagementsystem.models.Doctor;
import dev.endribegaj.hospitalmanagementsystem.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;



@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;
    private final FileHelper fileHelper;


    public DoctorController(DoctorService service, FileHelper fileHelper) {
        this.service = service;
        this.fileHelper = fileHelper;
    }

    @GetMapping("")
    public String doctors(Model model) {
        model.addAttribute("doctors", service.findAll());
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
    public String addDoctor(@Valid @ModelAttribute Doctor doctor, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam("photoFile") MultipartFile photoFile) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "doctors/create";
        }
        var newDoctor = service.add(doctor);
        if (newDoctor == null) {
            bindingResult.rejectValue("personalNo", "error.Doctor", "Doctor already exists with that personal number");
            bindingResult.rejectValue("email", "error.Doctor", "Doctor already exists with that email");
            return "doctors/create";

        }
        System.out.println("Photo file: " + photoFile.getOriginalFilename());


        if (!photoFile.isEmpty()) {
            try {
                var fileName = fileHelper.uploadFile("target/classes/static/assets/img/doctors"
                        , photoFile.getOriginalFilename()
                        , photoFile.getBytes());
                doctor.setPhoto("/assets/img/doctors/" + fileName);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        redirectAttributes.addFlashAttribute("successMessage", "Doctor added successfully");
        return "redirect:/doctors";


    }

    @PostMapping("/{id}/edit")
    public String modifyDoctor(@PathVariable long id, @Valid @ModelAttribute Doctor doctor, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam("photoFile") MultipartFile photoFile) {
        if (doctor.getId() != id) {
            redirectAttributes.addAttribute("errorId","DR404");
            redirectAttributes.addFlashAttribute("errorMessage", "Doctor ID does not match");
            return "redirect:/doctors";
        }

        if (!photoFile.isEmpty()) {
            try {
                var fileName = fileHelper.uploadFile("target/classes/static/assets/img/doctors"
                        , photoFile.getOriginalFilename()
                        , photoFile.getBytes());
                doctor.setPhoto("/assets/img/doctors/" + fileName);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
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