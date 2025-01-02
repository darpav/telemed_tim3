package com.hita.telemed.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends AppUser{

    private List<Patient> patients = new ArrayList<>();

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
