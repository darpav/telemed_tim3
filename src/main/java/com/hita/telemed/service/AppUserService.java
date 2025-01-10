//package com.hita.telemed.service;
//
//import com.hita.telemed.model.AppUser;
//import com.hita.telemed.repository.AppUserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AppUserService {
//
//    public AppUserRepository appUserRepository;
//
//    public AppUserService(AppUserRepository appUserRepository) {
//        this.appUserRepository = appUserRepository;
//    }
//
//    // get all app users
//    public List<AppUser> getAllAppUsers() {
//        return appUserRepository.getAllAppUsers();
//    }
//
//    public AppUser getAppUserByEmailAndPassword(String useremail, String userpassword) {
//        List<AppUser> appUsers = appUserRepository.getAllAppUsers();
//        for (AppUser appUser : appUsers) {
//            if (appUser.getAppUserEmail().equals(useremail) && appUser.getAppUserPassword().equals(userpassword)) {
//                return appUser;
//            }
//        }
//        return null;
//    }
//}
