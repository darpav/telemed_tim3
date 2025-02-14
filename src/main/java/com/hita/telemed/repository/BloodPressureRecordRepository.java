package com.hita.telemed.repository;

import com.hita.telemed.model.AppUser;
import com.hita.telemed.model.BloodPressureRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodPressureRecordRepository extends JpaRepository<BloodPressureRecord, Long> {

    public List<BloodPressureRecord> findBloodPressureRecordsByPatient_AppUserId(Long patientId);

    @Query("SELECT b FROM BloodPressureRecord b WHERE b.patient.appUserId = :patientId ORDER BY b.dateOfMeasurement DESC, b.recordId DESC")
    public List<BloodPressureRecord> findBloodPressureRecordsByPatient_AppUserIdOrderByDateOfMeasurementDescRecordIdDesc(Long patientId);

    @Query("SELECT b FROM BloodPressureRecord b JOIN b.patient p WHERE p.role = 'PATIENT' AND p.doctor.appUserId = :doctorId ORDER BY b.dateOfMeasurement DESC")
    public List<BloodPressureRecord> findAllBloodPressureRecordsWithPatientNameByDoctorIdOrderedByDateOfMeasurementDesc(Long doctorId);


    @Query("SELECT b FROM BloodPressureRecord b JOIN b.patient p WHERE p.role = 'PATIENT' AND p.doctor.appUserId = :doctorId"
            + " AND b.recordId = (SELECT MAX(bpr2.recordId) FROM BloodPressureRecord bpr2 WHERE bpr2.patient.appUserId = p.appUserId)"
            + " ORDER BY b.dateOfMeasurement DESC")
    public List<BloodPressureRecord> findPatientsWithLastRecordByDoctorId(Long doctorId);

}
