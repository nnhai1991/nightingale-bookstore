package com.nightingale.app.service;

public interface MailGunEmailService {

    Boolean sendEmail(String recipient, String subject, String content);
}
