package com.hita.telemed.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "blood_pressure_record")
public class BloodPressureRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "systolic_blood_pressure")
    private int systolicBloodPressure;      // mmHg

    @Column(name = "diastolic_blood_pressure")
    private int diastolicBloodPressure;     // mmHg

    @Column(name = "heart_rate")
    private int heartRate;                  // bpm

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "date_of_measurement")
    private LocalDateTime dateOfMeasurement;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "app_user_id")
    private AppUser patient;


    public BloodPressureRecord() {
    }

    // getters and setters


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

    public AppUser getPatient() {
        return patient;
    }

    public void setPatient(AppUser patient) {
        this.patient = patient;
    }

    public LocalDateTime getDateOfMeasurement() {
        return dateOfMeasurement;
    }

    public void setDateOfMeasurement(LocalDateTime dateOfMeasurement) {
        this.dateOfMeasurement = dateOfMeasurement;
    }


    public String getFormattedDateOfMeasurement() {
        return dateOfMeasurement.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
    }

    @Override
    public String toString() {
        return "BloodPressureRecord{" +
                "recordId=" + recordId +
                ", systolicBloodPressure=" + systolicBloodPressure +
                ", diastolicBloodPressure=" + diastolicBloodPressure +
                ", heartRate=" + heartRate +
                ", shortDescription='" + shortDescription + '\'' +
                ", dateOfMeasurement=" + dateOfMeasurement +
                ", patient=" + patient +
                '}';
    }
}
