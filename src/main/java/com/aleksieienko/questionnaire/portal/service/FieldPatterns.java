package com.aleksieienko.questionnaire.portal.service;

public class FieldPatterns {
    public static final String USER_EMAIL = "^[a-zA-Z0-9_!#$%&’*+\\/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+\\/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static final String USER_PASSWORD = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    public static final String USER_NAME = "[A-Z][a-z]{1,29}";
    public static final String USER_PHONE = "^\\+?3?8?(0[5-9][0-9]\\d{7})$";

    private FieldPatterns() {}
}
