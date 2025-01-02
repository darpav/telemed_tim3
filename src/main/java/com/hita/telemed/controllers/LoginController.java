package com.hita.telemed.controllers;

import com.hita.telemed.model.AppUser;
import com.hita.telemed.model.Role;
import com.hita.telemed.service.AppUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private AppUserService appUserService;

    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String getLogin() {
        // username, password
        return "/login";
    }

    @GetMapping("/loginUser")
    public String loginUser(@RequestParam("useremail") String useremail, @RequestParam("userpassword") String userpassword, HttpSession session) {
        AppUser appUser = appUserService.getAppUserByEmailAndPassword(useremail, userpassword);

        if(appUser == null) {
            return "redirect:/login?msg=Invalid username or password";
        } else {
            if (appUser.getRole() == Role.DOCTOR) {
                session.setAttribute("doctor", appUser);
                return "redirect:/doctor/dashboard";
            } else if (appUser.getRole() == Role.PATIENT) {
                session.setAttribute("patient", appUser);
                return "redirect:/patient/dashboard";
            }
        }

        return "redirect:/";
    }

    @GetMapping("/")
    public String getLoginRoot() {
        return "redirect:/login";
    }

    @GetMapping("/*")
    public String getLoginRootAny(){
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        if(session.getAttribute("doctor") != null) {
            session.removeAttribute("doctor");
        } else if (session.getAttribute("patient") != null) {
            session.removeAttribute("patient");
        }
        session.invalidate();
        return "redirect:/login";
    }
}
