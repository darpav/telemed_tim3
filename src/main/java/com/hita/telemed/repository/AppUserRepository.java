package com.hita.telemed.repository;

import com.hita.telemed.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppUserRepository {

    private List<AppUser> appUsers;

    public AppUserRepository() {
        appUsers = new ArrayList<>();
    }

    public List<AppUser> getAllAppUsers() {
        return appUsers;
    }

    public void saveAppUser(AppUser appUser) {
        appUsers.add(appUser);
    }

    // get doctor
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            if (appUser.getRole() == Role.DOCTOR) {
                doctors.add((Doctor) appUser);
            }
        }
        return doctors;
    }

    public Doctor getDoctorById(int id) {
        for (AppUser appUser : appUsers) {
            if (appUser.getRole() == Role.DOCTOR && ((Doctor) appUser).getAppUserId() == id) {
                return (Doctor) appUser;
            }
        }
        return null;
    }

    // get patient
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            if (appUser.getRole() == Role.PATIENT) {
                patients.add((Patient) appUser);
            }
        }
        return patients;
    }

    // get patients by doctor id
    public List<Patient> getPatientsByDoctorId(int doctorId) {
        List<Patient> patients = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            if (appUser.getRole() == Role.PATIENT && ((Patient) appUser).getDoctor().getAppUserId() == doctorId) {
                patients.add((Patient) appUser);
            }
        }
        return patients;
    }

    // save patient
    public void savePatient(Patient patient) {
        appUsers.add(patient);
    }

    public Patient getPatientById(int id) {
        for (AppUser appUser : appUsers) {
            if (appUser.getRole() == Role.PATIENT && ((Patient) appUser).getAppUserId() == id) {
                return (Patient) appUser;
            }
        }
        return null;
    }

    public void updatePatientBloodPressureRecords(int appUserId, List<BloodPressureRecord> records) {
        for(AppUser appUser : appUsers) {
            if (appUser.getRole() == Role.PATIENT && ((Patient) appUser).getAppUserId() == appUserId) {
                ((Patient) appUser).setBloodPressureRecords(records);
            }
        }
    }

    public void updateDoctorPatientsList(int appUserId, List<Patient> patients) {
        for(AppUser appUser : appUsers) {
            if (appUser.getRole() == Role.DOCTOR && ((Doctor) appUser).getAppUserId() == appUserId) {
                ((Doctor) appUser).setPatients(patients);
            }
        }
    }
}
