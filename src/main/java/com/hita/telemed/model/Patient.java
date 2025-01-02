package com.hita.telemed.model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends AppUser{

    private Doctor doctor;

    //List os blood pressure records
    private List<BloodPressureRecord> bloodPressureRecords = new ArrayList<>();

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<BloodPressureRecord> getBloodPressureRecords() {
        return bloodPressureRecords;
    }

    public void setBloodPressureRecords(List<BloodPressureRecord> bloodPressureRecords) {
        this.bloodPressureRecords = bloodPressureRecords;
    }

    // get last blood pressure record
    public BloodPressureRecord getLastBloodPressureRecord() {
        if(bloodPressureRecords.size() - 1 < 0) {
            return null;
        }
        return bloodPressureRecords.get(bloodPressureRecords.size() - 1);
    }
}
