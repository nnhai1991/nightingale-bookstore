package com.nightingale.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nightingale.app.entity.User;
import com.nightingale.app.exception.ObjectNotFoundException;
import com.nightingale.app.model.dto.UserForUpdatePassword;
import com.nightingale.app.service.UserService;
import com.nightingale.app.util.UtilConstants;

@Controller
@RequestMapping("")
public class LoginController {

    private final static String FOLDER = "/account";
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false, defaultValue = "") String error) {

        if (error != null)
            model.addAttribute("error", error);

        return FOLDER + "/login";
    }

    @GetMapping("/reset-password")
    public String resetPassword(Model model,
                                @RequestParam(name = "token", required = false, defaultValue = "") String token)
            throws ObjectNotFoundException {

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
            throws ObjectNotFoundException {

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