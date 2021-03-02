package ru.itis.rasimusv.util;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String code);
}
