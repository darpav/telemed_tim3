package com.hita.telemed.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BloodPressureRecord {

    private static int counterId = 0;

    private int recordId;

    private LocalDateTime date;

    private int systolicBloodPressure;      // mmHg
    private int diastolicBloodPressure;     // mmHg
    private int heartRate;                  // bpm

    private String shortDescription;

    private AppUser patient;

    public BloodPressureRecord() {
        this.recordId = counterId++;
    }

    public int getRecordId() {
        return recordId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(int systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public int getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(int diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public AppUser getPatient() {
        return patient;
    }

    public void setPatient(AppUser patient) {
        this.patient = patient;
    }

    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
    }

    @Override
    public String toString() {
        return "BloodPressureRecord{" +
                "recordId=" + recordId +
                ", date=" + date +
                ", systolicBloodPressure=" + systolicBloodPressure +
                ", diastolicBloodPressure=" + diastolicBloodPressure +
                ", heartRate=" + heartRate +
                ", shortDescription='" + shortDescription + '\'' +
                ", patient=" + patient +
                '}';
    }
}
