package com.hita.telemed.repository;

import com.hita.telemed.model.BloodPressureRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BloodPressureRecordRepository {

    private List<BloodPressureRecord> bloodPressureRecords;

    public BloodPressureRecordRepository() {
        this.bloodPressureRecords = new ArrayList<>();
    }

    public List<BloodPressureRecord> getAllBloodPressureRecords() {
        return bloodPressureRecords;
    }

    public BloodPressureRecord getBloodPressureRecordById(int recordId) {
        for (BloodPressureRecord bloodPressureRecord : bloodPressureRecords) {
            if (bloodPressureRecord.getRecordId() == recordId) {
                return bloodPressureRecord;
            }
        }
        return null;
    }

    public List<BloodPressureRecord> getBloodPressureRecordsByPatientId(int appUserId) {
        List<BloodPressureRecord> patientBloodPressureRecords = new ArrayList<>();
        for (BloodPressureRecord bloodPressureRecord : bloodPressureRecords) {
            if (bloodPressureRecord.getPatient().getAppUserId() == appUserId) {
                patientBloodPressureRecords.add(bloodPressureRecord);
            }
        }
        return patientBloodPressureRecords;
    }

    public void saveBloodPressureRecord(BloodPressureRecord bloodPressureRecord) {
        bloodPressureRecords.add(bloodPressureRecord);
    }

    public void updateRecord(BloodPressureRecord record) {
        BloodPressureRecord recordToUpdate = getBloodPressureRecordById(record.getRecordId());
        recordToUpdate.setDate(record.getDate());
        recordToUpdate.setSystolicBloodPressure(record.getSystolicBloodPressure());
        recordToUpdate.setDiastolicBloodPressure(record.getDiastolicBloodPressure());
        recordToUpdate.setHeartRate(record.getHeartRate());
        recordToUpdate.setShortDescription(record.getShortDescription());
        recordToUpdate.setPatient(record.getPatient());
    }

    public void deleteRecord(BloodPressureRecord record) {
        bloodPressureRecords.remove(record);
    }
}
