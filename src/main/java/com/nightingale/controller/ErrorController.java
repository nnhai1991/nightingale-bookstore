package com.nightingale.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nightingale.exception.CustomException;
import com.nightingale.exception.NightingaleException;

@ControllerAdvice
public class ErrorController {

    private final String FOLDER = "/error";
    //private final Logger logger = Logger.getLogger(this.getClass().getName());


    @ExceptionHandler(CustomException.class)
    public String customExceptionHandler(HttpServletRequest req, CustomException e) throws Exception {
        StringBuffer sb = req.getRequestURL();
        String query = req.getQueryString();
        if (query != null) {
            sb.append(query);
        }
        // log something
        return FOLDER + "/error";
    }

    @ExceptionHandler(NightingaleException.class)
    public String ObjectCreationExceptionHandler(HttpServletRequest req, NightingaleException e) throws Exception {
        StringBuffer sb = req.getRequestURL();
        String query = req.getQueryString();
        if (query != null) {
            sb.append(query);
        }

        // log something
        return FOLDER + "/error";
    }


    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        StringBuffer sb = req.getRequestURL();
        String query = req.getQueryString();
        if (query != null) {
            sb.append(query);
        }
        // log something
        return FOLDER + "/error";
    }
}
