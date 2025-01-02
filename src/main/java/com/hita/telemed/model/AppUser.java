package com.hita.telemed.model;

public class AppUser {

    private static int counterId = 0;

    private int appUserId;

    private String firstName;
    private String lastName;

    private String appUserEmail;
    private String appUserPassword;

    private Role role;

    public AppUser() {
        this.appUserId = counterId++;
    }

    public AppUser(String firstName, String lastName, String appUserEmail, String appUserPassword, Role role) {
        this.appUserId = counterId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.appUserEmail = appUserEmail;
        this.appUserPassword = appUserPassword;
        this.role = role;
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

    public int getAppUserId() {
        return appUserId;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", appUserEmail='" + appUserEmail + '\'' +
                ", appUserPassword='" + appUserPassword + '\'' +
                ", role=" + role +
                '}';
    }
}
