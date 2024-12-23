package com.hita.telemed.model;

public class BloodPressureRecord {

    private Long recordId;

    private String date;

    private int systolicBloodPressure;      // mmHg
    private int diastolicBloodPressure;     // mmHg
    private int heartRate;                  // bpm

    private String shortDescription;

    // private AppUser appUser;

    public BloodPressureRecord () {
    }

    public BloodPressureRecord (Long recordId, String date, int systolicBloodPressure, int diastolicBloodPressure, int heartRate, String shortDescription) {
        this.recordId = recordId;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
        this.heartRate = heartRate;
        this.shortDescription = shortDescription;
        this.date = date;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BloodPressureRecord{" +
                "recordId=" + recordId +
                ", systolicBloodPressure=" + systolicBloodPressure +
                ", diastolicBloodPressure=" + diastolicBloodPressure +
                ", heartRate=" + heartRate +
                ", shortDescription='" + shortDescription + '\'' +
                '}';
    }
}
