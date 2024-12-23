package com.hita.telemed.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {

    @GetMapping("/doctor/dashboard")
    public String getDoctorDashboard() {
        return "dashboard_doctor";
    }

    @GetMapping("/doctor/patientNew")
    public String getAddNewPatient() {
        return "add_new_patient";
    }

    public String postAddNewPatient() {
        return "redirect: /doctor_dashboard";
    }
}
