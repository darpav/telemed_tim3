package com.hita.telemed.repository;

import com.hita.telemed.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public AppUser findAppUserByAppUserEmailAndAppUserPassword(String appUserEmail, String appUserPassword);

    @Query("SELECT p FROM AppUser p WHERE p.doctor.appUserId = :doctorId")
    List<AppUser> findAllPatientsByDoctorId(Long doctorId);

}
