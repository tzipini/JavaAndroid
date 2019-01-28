package com.example.ourex.takengo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.models.backend.DBManagerFactory;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.models.entities.Branch;
import com.example.ourex.takengo.models.entities.User;


public class SQLLiteHelper extends SQLiteOpenHelper {

    protected Context context;
    protected IDBManager lists ;
    protected SQLiteDatabase sqLiteDatabase;

    public SQLLiteHelper(Context context) {
        super(context, "db", null, 2);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        lists = DBManagerFactory.getIdbManager(DBManagerFactory.DBType.Lists);
        lists.init(context);
        initBranches(sqLiteDatabase);
        initUsers(sqLiteDatabase);
    }

    public void initUsers(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(" +
                    ContentProviderContracts.User.USER_ID + " text," +
                    ContentProviderContracts.User.FIRST_NAME + " text," +
                    ContentProviderContracts.User.LAST_NAME + " text," +
                    ContentProviderContracts.User.TEL + " text," +
                    ContentProviderContracts.User.EMAIL + " text," +
                    ContentProviderContracts.User.PASSWORD + " text" +
                    ")");
            for (User user : lists.getUsersList()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ContentProviderContracts.User.USER_ID, user.getIdentityNo());
                contentValues.put(ContentProviderContracts.User.FIRST_NAME, user.getFirstName());
                contentValues.put(ContentProviderContracts.User.LAST_NAME, user.getLastName());
                contentValues.put(ContentProviderContracts.User.TEL, user.getTel());
                contentValues.put(ContentProviderContracts.User.EMAIL, user.getEmail());
                contentValues.put(ContentProviderContracts.User.PASSWORD, user.getPassword());
                sqLiteDatabase.insert("users", null, contentValues);
            }
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    public void initBranches(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS branches(" +
                    ContentProviderContracts.Branch.ID + " text," +
                    ContentProviderContracts.Branch.NAME + " text" +
                    ")");
            for (Branch branch : lists.getBranchList()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ContentProviderContracts.Branch.ID, branch.getId());
                contentValues.put(ContentProviderContracts.Branch.NAME, branch.getName());
                sqLiteDatabase.insert("branches", null, contentValues);
            }
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE users");
        sqLiteDatabase.execSQL("DROP TABLE branches");
    }
}
