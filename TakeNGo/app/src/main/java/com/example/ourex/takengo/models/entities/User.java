package com.example.ourex.takengo.models.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * •	שם משפחה
 •	שם פרטי
 •	מספר ת.ז.
 •	מספר טלפון
 •	כתובת דוא"ל
 •	מספר כרטיס אשראי לחיוב.
 */
public class User implements IArray {

    protected Integer _id;
    protected String identityNo;
    protected String firstName;
    protected String lastName;
    protected String tel;
    protected String email;
    protected String password;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public User() {
        try{

        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    public User( String email, String password) {
        this._id = 0;
        this.email = email;
        this.password = password;
    }

    public User(Integer _id,String firstName, String lastName, String identityNo, String tel, String email, String password) {
        this._id = _id;
        this.identityNo = identityNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }

    public User(String[] properties) {
        try{
            this._id = Integer.parseInt(properties[0]);
            this.identityNo = properties[1];
            this.firstName = properties[2];
            this.lastName = properties[3];
            this.tel = properties[4];
            this.email = properties[5];
            this.password = properties[6];
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    public User(Cursor cursor) {
        try{
            this._id = cursor.getInt(cursor.getColumnIndex(ContentProviderContracts.User.USER_ID));
            this.identityNo = cursor.getString(cursor.getColumnIndex(ContentProviderContracts.User.USER_ID));
            this.firstName = cursor.getString(cursor.getColumnIndex(ContentProviderContracts.User.FIRST_NAME));
            this.lastName = cursor.getString(cursor.getColumnIndex(ContentProviderContracts.User.LAST_NAME));
            this.email = cursor.getString(cursor.getColumnIndex(ContentProviderContracts.User.EMAIL));
            this.tel = cursor.getString(cursor.getColumnIndex(ContentProviderContracts.User.TEL));
            this.password = cursor.getString(cursor.getColumnIndex(ContentProviderContracts.User.PASSWORD));
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    public User(ContentValues properties) {
        try{
            this._id = properties.getAsInteger(ContentProviderContracts.User.USER_ID);
            this.identityNo = properties.getAsString(ContentProviderContracts.User.USER_ID);
            this.firstName = properties.getAsString(ContentProviderContracts.User.FIRST_NAME);
            this.lastName = properties.getAsString(ContentProviderContracts.User.LAST_NAME);
            this.tel =properties.getAsString(ContentProviderContracts.User.TEL);
            this.email = properties.getAsString(ContentProviderContracts.User.EMAIL);
            this.password = properties.getAsString(ContentProviderContracts.User.PASSWORD);
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    @Override
    public ArrayList<String> getArrayList() {
        return new ArrayList<>(Arrays.asList(
                this._id.toString(),
                this.identityNo.toString(),
                this.firstName,
                this.lastName,
                this.tel,
                this.email,
                this.password));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identityNo='" + identityNo + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getFullName() {
        return "";
    }

    public boolean isValid(){
        if (this.getPassword().equals(""))
            return false;
        if (this.getEmail().equals(""))
            return false;

        return true;
    }

}
