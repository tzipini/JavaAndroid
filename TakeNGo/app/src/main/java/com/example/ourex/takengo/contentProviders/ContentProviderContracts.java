package com.example.ourex.takengo.contentProviders;

import android.net.Uri;

public class ContentProviderContracts {
    public static final String AUTHORITY = "com.example.ourex.takengo";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static class User {
        public static final Uri URI = Uri.withAppendedPath(AUTHORITY_URI, "users");
        public static final String USER_ID = "_id";
        public static final String IDENTITY_NO = "identityNo";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String TEL = "tel";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
    }



    public static class Branch {
        public static final Uri URI = Uri.withAppendedPath(AUTHORITY_URI, "branches");
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String CITY = "city";
        public static final String STREET_NO = "street_no";
        public static final String STREET_NAME = "street_name";
    }

    public static class Car {
        public static final Uri URI = Uri.withAppendedPath(AUTHORITY_URI, "cars");
        public static final String CAR_ID = "_id";
        public static final String CAR_NO = "carNo";
        public static final String BRANCH_NUMBER = "branchNo";
        public static final String MODEL_ID = "_id";
        public static final String TOTAL_KILOMETERS = "kilometers";

    }

    public static class Models {
        public static final Uri URI = Uri.withAppendedPath(AUTHORITY_URI, "models");
        public static final String ID = "_id";
        public static final String MODEL_ID = "modelId";
        public static final String COMPANY_NAME = "companyName";
        public static final String MODEL_NAME = "modelName";
        public static final String ENGINE_SIZE = "engineSize";
        public static final String GEAR_TYPE = "gearType";
        public static final String SEATS = "seats";

    }
}