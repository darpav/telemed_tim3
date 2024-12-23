package com.hita.telemed.controllers;

import com.hita.telemed.model.BloodPressureRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {

    // list of records
    List<BloodPressureRecord> records;

    // constructor init
    public PatientController() {
        records = new ArrayList<>();
        // add few records
        records.add(new BloodPressureRecord(1L, "2024-12-15", 120, 18, 72, "measured after waking up in the morning, feeling well-rested and relaxed"));
        records.add(new BloodPressureRecord(2L, "2024-12-18", 138, 85, 80, "measured after moderate physical activity during a walk. "));
        records.add(new BloodPressureRecord(3L, "2024-12-20", 110,70,65,"recorded in a relaxed state at home in the evening after dinner"));
    }

    @GetMapping("/patient/dashboard")
    public String getPatientDashboard(Model model) {

        model.addAttribute("records", records);
        return "dashboard_patient";
    }

    @GetMapping("/patient/createRecord")
    public String addNewRecord() {
        return "record_new";
    }

    @GetMapping("/patient/saveRecord")
    public String addNewRecordForm(@RequestParam("systolicBloodPressure") int systolicBloodPressure,
                                   @RequestParam("diastolicBloodPressure") int diastolicBloodPressure,
                                   @RequestParam("heartRate") int heartRate,
                                   @RequestParam("shortDescription") String shortDescription) {
        BloodPressureRecord record = new BloodPressureRecord();
        record.setSystolicBloodPressure(systolicBloodPressure);
        record.setDiastolicBloodPressure(diastolicBloodPressure);
        record.setHeartRate(heartRate);
        record.setShortDescription(shortDescription);
        records.add(record);
        return "redirect:/patient/dashboard";
    }

    @GetMapping("/patient/recordDetail")
    public String getRecordDetail(@RequestParam("id") int id, Model model) {
        BloodPressureRecord record = new BloodPressureRecord();
        for(BloodPressureRecord r: records) {
            if(r.getRecordId() == id) {
                record = r;
            }
        }
        model.addAttribute("record", record);
        return "record_detail";
    }
}
