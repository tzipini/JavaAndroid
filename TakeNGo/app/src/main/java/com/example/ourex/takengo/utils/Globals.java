package com.example.ourex.takengo.utils;

import android.app.Application;

import com.example.ourex.takengo.models.backend.DBManagerFactory;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.models.entities.User;

public class Globals extends Application {
    private Object _Globals;
    private static User currentUser = new User();
    private static Globals singleInstance = null;
    public static IDBManager idbManager = DBManagerFactory.getIdbManager(DBManagerFactory.DBType.MySQL);

    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static Globals getInstance()
    {
        return singleInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        singleInstance = this;
    }
}
