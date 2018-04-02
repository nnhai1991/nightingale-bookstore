package com.nightingale.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nightingale.Constants;
import com.nightingale.entity.User;

import com.nightingale.model.dto.UserForUpdatePassword;
import com.nightingale.service.UserService;

@Controller
@RequestMapping("")
public class LoginController {

    private final static String FOLDER = "/login";
    @Autowired
    private UserService userService;
    
    @GetMapping("/admin")
    public String home() {
        return FOLDER + "/home";
    }
    
    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false, defaultValue = "") String error) {

        if (error != null)
            model.addAttribute("error", error);
        if (userService.getListAll().size()==0){
        	userService.createAdmin();        	
        }

        return FOLDER + "/login";
    }

    @GetMapping("/reset-password")
    public String resetPassword(Model model,
                                @RequestParam(name = "token", required = false, defaultValue = "") String token)
             {

        model.addAttribute("token", token);
        if (token != null) {
            User user = userService.readFromToken(token);
            UserForUpdatePassword userForUpdatePassword = new UserForUpdatePassword();
            userForUpdatePassword.setId(user.getId());
            if (user != null) {
                model.addAttribute("userForUpdatePassword", userForUpdatePassword);
                return FOLDER + "/reset-password";
            }
        }
        model.addAttribute("msg", "invalid_token");
        return FOLDER + "/reset-password";

    }

    @PostMapping("/reset-password")
    public String resetPasswordPost(Model model,
                                    @RequestParam(name = "token", required = false, defaultValue = "") String token,
                                    @Valid UserForUpdatePassword userForUpdatePassword, BindingResult bindingResult)
             {

        if (!bindingResult.hasErrors()) {
            if (userService.updatePassword(userForUpdatePassword)) {
                return "redirect:/login";
            } else {
                bindingResult.reject("UpdateFailed");
                return FOLDER + "/reset-password";
            }
        }

        model.addAttribute("userForUpdatePassword", userForUpdatePassword);
        return FOLDER + "/reset-password";
    }

}
