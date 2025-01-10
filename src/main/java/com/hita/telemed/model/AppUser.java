package com.hita.telemed.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id")
    private Long appUserId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String appUserEmail;

    @Column(name = "password")
    private String appUserPassword;

    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "app_user_id")
    private AppUser doctor;

    public AppUser() {
    }

    // getter and setter


    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAppUserEmail() {
        return appUserEmail;
    }

    public void setAppUserEmail(String appUserEmail) {
        this.appUserEmail = appUserEmail;
    }

    public String getAppUserPassword() {
        return appUserPassword;
    }

    public void setAppUserPassword(String appUserPassword) {
        this.appUserPassword = appUserPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AppUser getDoctor() {
        return doctor;
    }

    public void setDoctor(AppUser doctor) {
        this.doctor = doctor;
    }

    //
//    public List<BloodPressureRecord> getBloodPressureRecords() {
//        return bloodPressureRecords;
//    }
//
//    public void setBloodPressureRecords(List<BloodPressureRecord> bloodPressureRecords) {
//        this.bloodPressureRecords = bloodPressureRecords;
//    }


    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", appUserEmail='" + appUserEmail + '\'' +
                ", appUserPassword='" + appUserPassword + '\'' +
                ", role='" + role + '\'' +
                ", doctorId=" + doctor.getAppUserId() +
                '}';
    }
}
