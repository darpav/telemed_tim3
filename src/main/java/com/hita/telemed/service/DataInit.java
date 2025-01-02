package com.hita.telemed.service;

import com.hita.telemed.model.BloodPressureRecord;
import com.hita.telemed.model.Doctor;
import com.hita.telemed.model.Patient;
import com.hita.telemed.model.Role;
import com.hita.telemed.repository.AppUserRepository;
import com.hita.telemed.repository.BloodPressureRecordRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataInit {

    private AppUserRepository appUserRepository;
    private BloodPressureRecordRepository bloodPressureRecordRepository;

    public DataInit(AppUserRepository appUserRepository, BloodPressureRecordRepository bloodPressureRecordRepository) {
        this.appUserRepository = appUserRepository;
        this.bloodPressureRecordRepository = bloodPressureRecordRepository;
    }

    @PostConstruct
    public void init() {
        //        // add blood pressure records
        BloodPressureRecord record1 = new BloodPressureRecord();
        record1.setDate(LocalDateTime.now());
        record1.setSystolicBloodPressure(120);
        record1.setDiastolicBloodPressure(80);
        record1.setHeartRate(75);
        record1.setShortDescription("Resting comfortably in a chair for several minutes before the measurement.");

        BloodPressureRecord record2 = new BloodPressureRecord();
        record2.setDate(LocalDateTime.now());
        record2.setSystolicBloodPressure(130);
        record2.setDiastolicBloodPressure(85);
        record2.setHeartRate(82);
        record2.setShortDescription("Just finished walking around the house and was sitting down to relax.");

        BloodPressureRecord record3 = new BloodPressureRecord();
        record3.setDate(LocalDateTime.now());
        record3.setSystolicBloodPressure(140);
        record3.setDiastolicBloodPressure(90);
        record3.setHeartRate(88);
        record3.setShortDescription("Just completed a workout session");

        BloodPressureRecord record4 = new BloodPressureRecord();
        record4.setDate(LocalDateTime.now());
        record4.setSystolicBloodPressure(110);
        record4.setDiastolicBloodPressure(70);
        record4.setHeartRate(70);
        record4.setShortDescription("Sitting quietly for an extended period of time, reading and watching TV.");

        BloodPressureRecord record5 = new BloodPressureRecord();
        record5.setDate(LocalDateTime.now());
        record5.setSystolicBloodPressure(160);
        record5.setDiastolicBloodPressure(100);
        record5.setHeartRate(95);
        record5.setShortDescription("Feeling stressed due to work-related issues right before the measurement.");

        BloodPressureRecord record6 = new BloodPressureRecord();
        record6.setDate(LocalDateTime.now());
        record6.setSystolicBloodPressure(125);
        record6.setDiastolicBloodPressure(85);
        record6.setHeartRate(78);
        record6.setShortDescription("I had just finished a light meal and was sitting down to rest.");

//        // add patients
        Patient patient1 = new Patient();
        patient1.setAppUserEmail("peroperic@gmail.com");
        patient1.setAppUserPassword("peroperic");
        patient1.setRole(Role.PATIENT);
        patient1.setFirstName("Pero");
        patient1.setLastName("Peric");

        Patient patient2 = new Patient();
        patient2.setAppUserEmail("gogoroschanu@gmail.com");
        patient2.setAppUserPassword("gogoroschanu");
        patient2.setRole(Role.PATIENT);
        patient2.setFirstName("Gogo");
        patient2.setLastName("Roschanu");

        Patient patient3 = new Patient();
        patient3.setAppUserEmail("anaanic@gmail.com");
        patient3.setAppUserPassword("anaanic");
        patient3.setRole(Role.PATIENT);
        patient3.setFirstName("Ana");
        patient3.setLastName("Anic");

        // add doctors
        Doctor doctor1 = new Doctor();
        doctor1.setAppUserEmail("docuno@telemed.com");
        doctor1.setAppUserPassword("docuno");
        doctor1.setRole(Role.DOCTOR);
        doctor1.setFirstName("Doc");
        doctor1.setLastName("Uno");

        Doctor doctor2 = new Doctor();
        doctor2.setAppUserEmail("docduje@telemed.com");
        doctor2.setAppUserPassword("docduje");
        doctor2.setRole(Role.DOCTOR);
        doctor2.setFirstName("Duje");
        doctor2.setLastName("Doc");

        record1.setPatient(patient1);
        record2.setPatient(patient1);
        record3.setPatient(patient1);
        record4.setPatient(patient2);
        record5.setPatient(patient2);
        record6.setPatient(patient3);

        // patient set list
        patient1.setBloodPressureRecords(List.of(record1, record2, record3));
        patient2.setBloodPressureRecords(List.of(record4, record5));
        patient3.setBloodPressureRecords(List.of(record6));

        patient1.setDoctor(doctor1);
        patient2.setDoctor(doctor1);
        patient3.setDoctor(doctor2);

        // doctor add list of patient
        doctor1.setPatients(List.of(patient1, patient2));
        doctor2.setPatients(List.of(patient3));

        bloodPressureRecordRepository.saveBloodPressureRecord(record1);
        bloodPressureRecordRepository.saveBloodPressureRecord(record2);
        bloodPressureRecordRepository.saveBloodPressureRecord(record3);
        bloodPressureRecordRepository.saveBloodPressureRecord(record4);
        bloodPressureRecordRepository.saveBloodPressureRecord(record5);
        bloodPressureRecordRepository.saveBloodPressureRecord(record6);

        appUserRepository.saveAppUser(patient1);
        appUserRepository.saveAppUser(patient2);
        appUserRepository.saveAppUser(patient3);

        appUserRepository.saveAppUser(doctor1);
        appUserRepository.saveAppUser(doctor2);

    }
}
