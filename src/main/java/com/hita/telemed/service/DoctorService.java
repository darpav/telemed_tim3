//package com.hita.telemed.service;
//
//import com.hita.telemed.model.Doctor;
//import com.hita.telemed.model.Patient;
//import com.hita.telemed.repository.AppUserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DoctorService {
//
//    private AppUserRepository appUserRepository;
//
//    public DoctorService(AppUserRepository appUserRepository) {
//        this.appUserRepository = appUserRepository;
//    }
//
//    public Doctor getDoctorById(int id) {
//        return appUserRepository.getDoctorById(id);
//    }
//
//    public List<Patient> getPatientsByDoctorId(int doctorId) {
//        return appUserRepository.getPatientsByDoctorId(doctorId);
//    }
//
//    public void savePatient(Patient patient) {
//        appUserRepository.savePatient(patient);
//        // update doctor list
//        Doctor doctor = getDoctorById(patient.getDoctor().getAppUserId());
//        List<Patient> patients = appUserRepository.getPatientsByDoctorId(doctor.getAppUserId());
//        doctor.setPatients(patients);
//        appUserRepository.updateDoctorPatientsList(doctor.getAppUserId(), patients);
//        // app user repository update doctor
//    }
//}
