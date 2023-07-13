package com.levdoc.m20service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.stream.Stream;

public class MailUtils {

    private MailUtils() {
    }

    public static SimpleMailMessage creteMailMessage(final String email,
                                                     final String subject,
                                                     final String text) {
        return createMailMessage(
                Stream.of(email).toArray(String[]::new),
                subject,
                text
        );
    }

    public static SimpleMailMessage createMailMessage(final List<String> emails,
                                                      final String subject,
                                                      final String text) {
        return createMailMessage(
                emails.toArray(String[]::new),
                subject,
                text
        );
    }

    private static SimpleMailMessage createMailMessage(final String[] emails,
                                                       final String subject,
                                                       final String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("levdoc@mail.ua"); //TODO: Rewrite to config
        mailMessage.setTo(emails);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        return mailMessage;
    }

}
