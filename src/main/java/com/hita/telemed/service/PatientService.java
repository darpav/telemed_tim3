package com.hita.telemed.service;

import com.hita.telemed.model.BloodPressureRecord;
import com.hita.telemed.model.Patient;
import com.hita.telemed.repository.AppUserRepository;
import com.hita.telemed.repository.BloodPressureRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private AppUserRepository patientRepository;
    private BloodPressureRecordRepository bloodPressureRecordRepository;

    public PatientService(AppUserRepository patientRepository, BloodPressureRecordRepository bloodPressureRecordRepository) {
        this.patientRepository = patientRepository;
        this.bloodPressureRecordRepository = bloodPressureRecordRepository;
    }

    // get patient by id
    public Patient getPatientById(int id) {
        return patientRepository.getPatientById(id);
    }

    public List<BloodPressureRecord> getPatientRecords(int id) {
        Patient patient = patientRepository.getPatientById(id);
        List<BloodPressureRecord> records = bloodPressureRecordRepository.getBloodPressureRecordsByPatientId(patient.getAppUserId());
        return records;
    }

    public void saveRecord(BloodPressureRecord record) {
        bloodPressureRecordRepository.saveBloodPressureRecord(record);
        // patient repository set list
        List<BloodPressureRecord> records = patientRepository.getPatientById(record.getPatient().getAppUserId()).getBloodPressureRecords();
        patientRepository.updatePatientBloodPressureRecords(record.getPatient().getAppUserId(), records);
    }

    public void deleteRecord(int appUserId, int recordId) {
        BloodPressureRecord record = bloodPressureRecordRepository.getBloodPressureRecordById(recordId);
        bloodPressureRecordRepository.deleteRecord(record);
        // patient repository set list
        List<BloodPressureRecord> records = patientRepository.getPatientById(appUserId).getBloodPressureRecords();
        patientRepository.updatePatientBloodPressureRecords(appUserId, records);
    }

    public BloodPressureRecord getPatientRecordById(int recordId) {
        return bloodPressureRecordRepository.getBloodPressureRecordById(recordId);
    }
}
