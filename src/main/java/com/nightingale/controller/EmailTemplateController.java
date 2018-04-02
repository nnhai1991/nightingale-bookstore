package com.nightingale.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nhannv
 */
@Controller
@RequestMapping("/emailTemplate")
public class EmailTemplateController {

    private static final String FOLDER = "/emailTemplateDesign";

    @RequestMapping(value = "order", method = {RequestMethod.GET})
    public String orderNotify(Model model, HttpServletRequest request) {
        return FOLDER + "/customerOrderNotification";
    }

    @RequestMapping(value = "reminder", method = {RequestMethod.GET})
    public String reminderNotify(Model model, HttpServletRequest request) {
        return FOLDER + "/collectionReminderNotification";
    }

    @RequestMapping(value = "missed", method = {RequestMethod.GET})
    public String missedNotify(Model model, HttpServletRequest request) {
        return FOLDER + "/collectionExpireNotification";
    }
    
    @RequestMapping(value = "confirm", method = {RequestMethod.GET})
    public String preorderConfirm(Model model, HttpServletRequest request) {
        return FOLDER + "/preorderConfirm";
    }

    @RequestMapping(value = "collectionReminder", method = {RequestMethod.GET})
    public String collectionReminder(Model model, HttpServletRequest request) {
        return FOLDER + "/collectionReminder";
    }
}
