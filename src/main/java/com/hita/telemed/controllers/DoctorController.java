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

        // find all patients by doctor id
        //List<AppUser> patients = appUserRepository.findAllPatientsByDoctorId(doctor.getAppUserId());
        //List<BloodPressureRecord> records = bloodPressureRecordRepository.findBloodPressureRecordsByPatient_AppUserIdOrderByDateOfMeasurementDesc(doctor.getAppUserId());
        //List<BloodPressureRecord> records =

        //model.addAttribute("records", records);

        model.addAttribute("appUser", doctor);

        return "/doctor/doctor-dashboard";
    }

    @GetMapping("/doctor/patient/new")
    public String createPatient(Model model, HttpSession session) {
        AppUser doctor = (AppUser) session.getAttribute("appUser");
        model.addAttribute("appUser", doctor);
        return "/patient/patient-form";
    }

    @GetMapping("/doctor/patient/save")
    public String processPatientForm(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("useremail") String useremail,
                              @RequestParam("userpassword") String userpassword, HttpSession session) {

        AppUser doctor = (AppUser) session.getAttribute("appUser");
        //novi pacijent
        AppUser patient = new AppUser();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAppUserEmail(useremail);
        patient.setAppUserPassword(userpassword);
        patient.setRole("PATIENT");
        patient.setDoctor(doctor);
        appUserRepository.save(patient);

        return "redirect:/doctor/dashboard";
    }

    @GetMapping("/doctor/patient/list")
    public String getPatientsByDoctorId(Model model, HttpSession session) {
        AppUser doctor = (AppUser) session.getAttribute("appUser");
        model.addAttribute("appUser", doctor);
        return "/patient/patient-list";
    }

    @GetMapping("/doctor/patient/records")
    public String getPatientRecords(@RequestParam("patientId") Long patientId, Model model, HttpSession session) {

        AppUser doctor = (AppUser) session.getAttribute("appUser");
        AppUser patient = appUserRepository.findById(patientId).get();
        List<BloodPressureRecord> records = bloodPressureRecordRepository.findBloodPressureRecordsByPatient_AppUserId(patientId);

        model.addAttribute("appUser", doctor);
        model.addAttribute("patient", patient);
        model.addAttribute("records", records);
        return "/patient/patient-records";
    }

    @GetMapping("/doctor/patient/list/all")
    public String getAllPatients(){
        // popis svih pacijenata
        return "/patient/patient-list";
    }

    public String editPatient() {
        return "/patient_form";
    }

    public String deletePatient() {
        return "redirect:/doctor/dashboard";
    }
}
