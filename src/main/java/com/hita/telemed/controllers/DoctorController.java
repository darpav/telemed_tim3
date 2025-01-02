package com.hita.telemed.controllers;

import com.hita.telemed.model.Doctor;
import com.hita.telemed.model.Patient;
import com.hita.telemed.model.Role;
import com.hita.telemed.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DoctorController {


    private DoctorService doctorService;

    DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor/dashboard")
    public String getDoctorDashboard(Model model, HttpSession session) {
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        // get all patients by doctor
        List<Patient> patients = doctorService.getPatientsByDoctorId(doctor.getAppUserId());
        String doctorName = doctor.getFirstName() + " " + doctor.getLastName();
        model.addAttribute("doctorName", doctorName);
        model.addAttribute("patients", patients);
        // get all patient records

        return "/doctor/dashboard_doctor";
    }

    @GetMapping("/doctor/createPatient")
    public String createPatient() {
        return "/doctor/patient_form";
    }

    @GetMapping("/doctor/savePatient")
    public String savePatient(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("useremail") String useremail,
                              @RequestParam("userpassword") String userpassword, HttpSession session) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAppUserEmail(useremail);
        patient.setAppUserPassword(userpassword);
        patient.setRole(Role.PATIENT);

        Doctor doctor = (Doctor) session.getAttribute("doctor");
        patient.setDoctor(doctor);

        doctorService.savePatient(patient);
        return "redirect:/doctor/dashboard";
    }

    public String editPatient() {
        return "/patient_form";
    }

    public String deletePatient() {
        return "redirect:/doctor/dashboard";
    }
}
