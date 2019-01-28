package com.example.ourex.takengo.models.datasources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.models.entities.Branch;
import com.example.ourex.takengo.models.entities.Car;
import com.example.ourex.takengo.models.entities.CarModel;
import com.example.ourex.takengo.models.entities.Customer;
import com.example.ourex.takengo.models.entities.User;
import com.example.ourex.takengo.utils.Constants;
import com.example.ourex.takengo.utils.SQLLiteHelper;

import java.util.ArrayList;

public class SQLLite implements IDBManager  {

    SQLLiteHelper sqlLiteHelper;
    @Override
    public boolean init(Context context) {
        sqlLiteHelper = new SQLLiteHelper(context);

        return false;
    }

    @Override
    public boolean isUserExists(User user) {
        try{
            ArrayList<User> items = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = sqlLiteHelper.getReadableDatabase();
            String sql =  "select * from users where email = '" + user.getEmail() + "' and password = '" + user.getPassword() + "'";
            Cursor cursor =  sqLiteDatabase.rawQuery(sql, null );
            if (cursor.getCount() > 0 )
                return  true;

            return  false;
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
        return false;
    }


    @Override
    public boolean addNewUser(User user) {
        SQLiteDatabase sqLiteDatabase = sqlLiteHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentProviderContracts.User.USER_ID, user.getIdentityNo());
        contentValues.put(ContentProviderContracts.User.FIRST_NAME, user.getFirstName());
        contentValues.put(ContentProviderContracts.User.LAST_NAME, user.getLastName());
        contentValues.put(ContentProviderContracts.User.TEL, user.getTel());
        contentValues.put(ContentProviderContracts.User.EMAIL, user.getEmail());
        contentValues.put(ContentProviderContracts.User.PASSWORD, user.getPassword());
        sqLiteDatabase.insert("users", null, contentValues);
        return false;
    }

    @Override
    public boolean addNewCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean addNewModel(CarModel carModel) {
        return false;
    }

    @Override
    public boolean addNewBranch(Branch branch) {

        SQLiteDatabase sqLiteDatabase = sqlLiteHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentProviderContracts.Branch.ID, branch.getId());
        contentValues.put(ContentProviderContracts.Branch.NAME, branch.getName());
        sqLiteDatabase.insert("branches", null, contentValues);
        return false;
    }

    @Override
    public boolean addNewCar(Car car) { return false; }

    @Override
    public ArrayList<CarModel> getCarModels() {
        return null;
    }

    @Override
    public ArrayList<Car> getCarList() {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomersList() {
        return null;
    }

    @Override
    public ArrayList<Branch> getBranchList() {

        ArrayList<Branch> items = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = sqlLiteHelper.getReadableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery( "select * from branches", null );
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            items.add(new Branch(cursor));
            cursor.moveToNext();
        }
        return items;
    }

    @Override
    public ArrayList<User> getUsersList() {  ArrayList<String> array_list = new ArrayList<String>();
        ArrayList<User> items = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = sqlLiteHelper.getReadableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery( "select * from users", null );
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            items.add(new User(cursor));
            cursor.moveToNext();
        }
        return items;
    }

    @Override
    public User getUser(String userName) {
        try {
            ArrayList<User> items = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = sqlLiteHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from users where email = '" + userName + "'", null);
            if (cursor.getCount() > 0)
                return null;

            cursor.moveToFirst();

            User user = new User(cursor);
            return user;
        }
        catch (Exception e){
            Log.w(Constants.Log.APP_LOG,e);
            return null;
        }
    }


}
