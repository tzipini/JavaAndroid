package com.example.ourex.takengo.models.backend;


import android.content.Context;

import com.example.ourex.takengo.models.datasources.Lists;
import com.example.ourex.takengo.models.datasources.MySQL;
import com.example.ourex.takengo.models.datasources.SQLLite;

public class DBManagerFactory {

    public enum DBType {
        Lists,
        MySQL,
        SQLLite
    }

    static IDBManager idbManager = null;

    public static IDBManager getIdbManager(DBType dbType) {
        switch (dbType){
            case Lists:
                idbManager = new Lists();
                break;
            case MySQL:
                idbManager = new MySQL();
                break;
            case SQLLite:
                idbManager = new SQLLite();
                break;
        }
        return  idbManager;
    }

}
