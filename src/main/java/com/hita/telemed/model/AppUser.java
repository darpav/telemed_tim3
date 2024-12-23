package com.hita.telemed.model;

public class AppUser {

    private Long apUserId;

    private String userEmail;
    private String userPassword;


    public AppUser() {
    }

    public AppUser(Long apUserId, String userEmail, String userPassword) {
        this.apUserId = apUserId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public Long getApUserId() {
        return apUserId;
    }

    public void setApUserId(Long apUserId) {
        this.apUserId = apUserId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "apUserId=" + apUserId +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword +
                '}';
    }
}
