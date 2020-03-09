package com.aleksieienko.questionnaire.portal.db;

public class Fields {
    public static final String USER_ID = "id";
    public static final String USER_EMAIL = "email";
    public static final String USER_HASHED_PASSWORD = "hash";
    public static final String USER_SALT = "salt";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_PHONE = "phone";

    public static final String VERIFICATION_TOKEN_ID = "id";
    public static final String VERIFICATION_TOKEN_TOKEN = "token";

    public static final String TYPE_ID = "id";
    public static final String TYPE_NAME = "name";

    public static final String FIELD_ID = "id";
    public static final String FIELD_LABEL = "label";
    public static final String FIELD_TYPE_ID = "type_id";
    public static final String FIELD_OPTION = "option";
    public static final String FIELD_REQUIRED = "required";
    public static final String FIELD_ACTIVE = "active";

    public static final String FIELD_NAMES_NAME = "name";

    private Fields() {
    }
}
