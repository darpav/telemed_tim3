package com.hita.telemed.controllers;

import com.hita.telemed.model.AppUser;
import com.hita.telemed.repository.AppUserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/telemed")
public class LoginController {

    private AppUserRepository appUserRepository;

    public LoginController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "msg", required = false) String msg, Model model) {
        model.addAttribute("msg", msg);
        return "/login";
    }

    @GetMapping("/")
    public String getRootLogin(){
        return "/login";
    }

    @GetMapping("/login/user")
    public String processLoginUser(@RequestParam("userEmail") String userEmail, @RequestParam("userPassword") String userPassword, HttpSession session) {

        AppUser appUser = appUserRepository.findAppUserByAppUserEmailAndAppUserPassword(userEmail, userPassword);

        if(appUser != null) {
            session.setAttribute("appUser", appUser);
            if (appUser.getRole().equals("PATIENT")) {
                return "redirect:/patient/dashboard";
            } else if (appUser.getRole().equals("DOCTOR")) {
                return "redirect:/doctor/dashboard";
            }
        }
        return "redirect:/login?msg=" + "Email ili lozinka nisu ispravni";
    }


    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        if(session.getAttribute("appUser") != null) {
            session.removeAttribute("appUser");
        }
        session.invalidate();
        return "redirect:/login";
    }
}
