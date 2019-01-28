package com.example.ourex.takengo.models.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Branch implements IArray {

    protected Integer _id;
    protected String name;
    protected Integer streetNo;
    protected String street;
    protected String city;

    public Branch(Integer number, String name, Address address) {
        this._id = number;
        this.name = name;

    }

    @Override
    public ArrayList<String> getArrayList() {
        return new ArrayList<>(Arrays.asList(
                this._id.toString(),
                this.name));
    }

    public Branch(String[] properties) {
        try {
            this._id = Integer.parseInt(properties[0]);
            this.name = properties[1];
            this.streetNo = Integer.parseInt(properties[2]);
            this.street = properties[3];
            this.city = properties[4];

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG, e);
        }
    }

    public Branch(ContentValues properties) {
        try {
            this._id = properties.getAsInteger(ContentProviderContracts.Branch.ID);
            this.name = properties.getAsString(ContentProviderContracts.Branch.NAME);
            this.streetNo = 0;
            this.street = "";
            this.city = "";

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG, e);
        }
    }

    public Branch(Cursor cursor) {
        try{
            this._id =  Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContentProviderContracts.Branch.ID)));
            this.name = cursor.getString(cursor.getColumnIndex(ContentProviderContracts.Branch.NAME));
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
    }


    public Integer getId() {
        return _id;
    }

    public void setId(Integer number) {
        this._id = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Branch{" +
                ", number=" + _id +
                ", name='" + name + '\'' +
                '}';
    }
}
