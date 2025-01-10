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
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class PatientController {

    private AppUserRepository appUserRepository;
    private BloodPressureRecordRepository bloodPressureRecordRepository;

    public PatientController(AppUserRepository appUserRepository, BloodPressureRecordRepository bloodPressureRecordRepository) {
        this.appUserRepository = appUserRepository;
        this.bloodPressureRecordRepository = bloodPressureRecordRepository;
    }

    // patient dashboard
    @GetMapping("/patient/dashboard")
    public String getPatientDashboard(Model model, HttpSession session) {
        AppUser patient = (AppUser) session.getAttribute("appUser");
        List<BloodPressureRecord> records = bloodPressureRecordRepository.findBloodPressureRecordsByPatient_AppUserIdOrderByDateOfMeasurementDesc(patient.getAppUserId());
        model.addAttribute("records", records);
        model.addAttribute("appUser", patient);
        return "/patient/patient-dashboard";
    }

    @GetMapping("/patient/record/new")
    public String getRecordForm(Model model, HttpSession session) {
        AppUser patient = (AppUser) session.getAttribute("appUser");
        model.addAttribute("appUser", patient);
        return "/record/record-form";
    }

    @GetMapping("/patient/record/save")
    public String processRecordForm(@RequestParam("systolicBloodPressure") int systolicBloodPressure,
                                    @RequestParam("diastolicBloodPressure") int diastolicBloodPressure,
                                    @RequestParam("heartRate") int heartRate,
                                    @RequestParam("shortDescription") String shortDescription,
                                    @RequestParam("dateOfMeasurement")LocalDate dateOfMeasurement, HttpSession session) {

        BloodPressureRecord bloodPressureRecord = new BloodPressureRecord();
        bloodPressureRecord.setSystolicBloodPressure(systolicBloodPressure);
        bloodPressureRecord.setDiastolicBloodPressure(diastolicBloodPressure);
        bloodPressureRecord.setHeartRate(heartRate);
        bloodPressureRecord.setShortDescription(shortDescription);
        AppUser patient = (AppUser) session.getAttribute("appUser");
        bloodPressureRecord.setPatient(patient);
        LocalDateTime dt = dateOfMeasurement.atStartOfDay();
        bloodPressureRecord.setDateOfMeasurement(dt);

        // save record
        bloodPressureRecordRepository.save(bloodPressureRecord);


        return "redirect:/patient/dashboard";
    }

    // edit record
    @GetMapping("/patient/record/edit")
    public String getEditRecord(@RequestParam("recordId") Long recordId, Model model, HttpSession session) {
        AppUser patient = (AppUser) session.getAttribute("patient");
        model.addAttribute("appUser", patient);
        BloodPressureRecord record = bloodPressureRecordRepository.findById(recordId).get();
        model.addAttribute("record", record);
        return "/record/record-form-edit";
    }

    @GetMapping("/patient/record/update")
    public String processEditRecord(@RequestParam("recordId") Long recordId,
                                    @RequestParam("systolicBloodPressure") int systolicBloodPressure,
                                    @RequestParam("diastolicBloodPressure") int diastolicBloodPressure,
                                    @RequestParam("heartRate") int heartRate,
                                    @RequestParam("shortDescription") String shortDescription) {

        // find record by id
        BloodPressureRecord recordToUpdate = bloodPressureRecordRepository.findById(recordId).get();
        recordToUpdate.setSystolicBloodPressure(systolicBloodPressure);
        recordToUpdate.setDiastolicBloodPressure(diastolicBloodPressure);
        recordToUpdate.setHeartRate(heartRate);
        recordToUpdate.setShortDescription(shortDescription);

        bloodPressureRecordRepository.save(recordToUpdate);

        return "redirect:/patient/dashboard";
    }

    @GetMapping("/patient/record/delete")
    public String deleteRecord(@RequestParam("recordId") Long recordId, HttpSession session) {
        bloodPressureRecordRepository.deleteById(recordId);
        return "redirect:/patient/dashboard";
    }

    @GetMapping("/patient/record/detail")
    public String viewRecordDetails(@RequestParam("recordId") Long recordId, Model model, HttpSession session) {

        // implement view detail
        BloodPressureRecord record = bloodPressureRecordRepository.findById(recordId).get();
        AppUser patient = (AppUser) session.getAttribute("appUser");
        model.addAttribute("record", record);
        model.addAttribute("appUser", patient);

        return "/record/record-detail";
    }
}
