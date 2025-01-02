package com.hita.telemed.controllers;

import com.hita.telemed.model.BloodPressureRecord;
import com.hita.telemed.model.Patient;
import com.hita.telemed.service.PatientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
        System.out.println("Patient constructor called");
    }

    // patient dashboard
    @GetMapping("/patient/dashboard")
    public String getPatientDashboard(Model model, HttpSession session) {
        Patient patient = (Patient) session.getAttribute("patient");
        List<BloodPressureRecord> records = patientService.getPatientRecords(patient.getAppUserId());
        String patientName = patient.getFirstName() + " " + patient.getLastName();
        model.addAttribute("patientName", patientName);
        model.addAttribute("records", records);
        return "/patient/dashboard_patient";
    }

    // add new record
    @GetMapping("/patient/createRecord")
    public String getCreateRecord() {
        return "/record/record_form";
    }

    // save record
    @GetMapping("/patient/saveRecord")
    public String saveRecord(@RequestParam("systolicBloodPressure") int systolicBloodPressure,
                             @RequestParam("diastolicBloodPressure") int diastolicBloodPressure,
                             @RequestParam("heartRate") int heartRate,
                             @RequestParam("shortDescription") String shortDescription, HttpSession session) {

        Patient patient = (Patient) session.getAttribute("patient");
        BloodPressureRecord record = new BloodPressureRecord();
        record.setDate(LocalDateTime.now());
        record.setSystolicBloodPressure(systolicBloodPressure);
        record.setDiastolicBloodPressure(diastolicBloodPressure);
        record.setHeartRate(heartRate);
        record.setShortDescription(shortDescription);
        record.setPatient(patient);
        patientService.saveRecord(record);
        return "redirect:/patient/dashboard";
    }

    // edit record
    @GetMapping("/patient/editRecord")
    public String editRecord() {
        // get logged in patient
        // get record by id
        // set new data to record
        // save record
//        Patient patient = patientRepository.getPatientById(0);
//        BloodPressureRecord record = bloodPressureRecordRepository.getBloodPressureRecordById(0);
//        record.setSystolicBloodPressure(-2);
//        record.setDiastolicBloodPressure(-2);
//        record.setHeartRate(-2);
//        record.setShortDescription("Updated record");
//        record.setPatient(patient);
//        bloodPressureRecordRepository.updateRecord(record);
        return "redirect:/patient/dashboard";
    }

    @GetMapping("/patient/deleteRecord")
    public String deleteRecord(@RequestParam("recordId") int recordId, HttpSession session) {

        Patient patient = (Patient) session.getAttribute("patient");
        patientService.deleteRecord(patient.getAppUserId(), recordId);
        return "redirect:/patient/dashboard";
    }

    @GetMapping("/patient/viewRecordDetail")
    public String viewRecordDetail(@RequestParam("recordId") int recordId, Model model) {

        BloodPressureRecord record = patientService.getPatientRecordById(recordId);
        model.addAttribute("record", record);
        return "/record/record_detail";
    }
}
