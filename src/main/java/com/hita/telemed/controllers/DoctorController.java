package com.hita.telemed.controllers;
import com.hita.telemed.model.AppUser;
import com.hita.telemed.model.BloodPressureRecord;
import com.hita.telemed.repository.AppUserRepository;
import com.hita.telemed.repository.BloodPressureRecordRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorController {

    private BloodPressureRecordRepository bloodPressureRecordRepository;
    private AppUserRepository appUserRepository;

    public DoctorController(BloodPressureRecordRepository bloodPressureRecordRepository, AppUserRepository appUserRepository) {
        this.bloodPressureRecordRepository = bloodPressureRecordRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/doctor/dashboard")
    public String getDoctorDashboard(Model model, HttpSession session) {
        AppUser doctor = (AppUser) session.getAttribute("appUser");

        List<BloodPressureRecord> records = bloodPressureRecordRepository.findPatientsWithLastRecordByDoctorId(doctor.getAppUserId());

        model.addAttribute("appUser", doctor);
        model.addAttribute("records", records);
        return "/doctor/doctor-dashboard";
    }

    @GetMapping("/doctor/patient/new")
    public String createPatient(@RequestParam(value = "msg", required = false) String msg, Model model, HttpSession session) {
        AppUser doctor = (AppUser) session.getAttribute("appUser");
        model.addAttribute("msg", msg);
        model.addAttribute("appUser", doctor);
        return "/doctor/doctor-patient-form";
    }

    @GetMapping("/doctor/patient/save")
    public String processPatientForm(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("userEmail") String userEmail,
                              @RequestParam("userPassword") String userPassword, HttpSession session) {

        AppUser doctor = (AppUser) session.getAttribute("appUser");

        AppUser user = appUserRepository.findAppUserByAppUserEmail(userEmail);
        if(user != null) {
            return "redirect:/doctor/patient/new?msg=" + "Email vec postoji";
        }
        //novi pacijent
        AppUser patient = new AppUser();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAppUserEmail(userEmail);
        patient.setAppUserPassword(userPassword);
        patient.setRole("PATIENT");
        patient.setDoctor(doctor);

        // if email exists redirect to form

        appUserRepository.save(patient);

        return "redirect:/doctor/dashboard";
    }

    @GetMapping("/doctor/patient/list")
    public String getPatientsByDoctorId(Model model, HttpSession session) {
        AppUser doctor = (AppUser) session.getAttribute("appUser");

        List<AppUser> patients = appUserRepository.findAllPatientsByDoctorIdOrderByLastName(doctor.getAppUserId());

        model.addAttribute("appUser", doctor);
        model.addAttribute("patients", patients);
        return "/doctor/doctor-patient-list";
    }

    @GetMapping("/doctor/patient/records")
    public String getPatientRecords(@RequestParam("patientId") Long patientId, Model model, HttpSession session) {

        AppUser doctor = (AppUser) session.getAttribute("appUser");
        AppUser patient = appUserRepository.findById(patientId).get();
        List<BloodPressureRecord> records = bloodPressureRecordRepository.findBloodPressureRecordsByPatient_AppUserId(patientId);

        model.addAttribute("appUser", doctor);
        model.addAttribute("patient", patient);
        model.addAttribute("records", records);
        return "/doctor/doctor-patient-records";
    }

}
