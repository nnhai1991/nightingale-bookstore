package com.nightingale.web.controller;

import com.nightingale.app.exception.CustomException;
import com.nightingale.app.exception.ObjectCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorController {

    private final String FOLDER = "/error";
    private final Logger logger = Logger.getLogger(this.getClass().getName());


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

    @ExceptionHandler(ObjectCreationException.class)
    public String ObjectCreationExceptionHandler(HttpServletRequest req, ObjectCreationException e) throws Exception {
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
