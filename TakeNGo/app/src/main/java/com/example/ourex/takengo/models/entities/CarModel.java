package com.example.ourex.takengo.models.entities;

import android.content.ContentValues;
import android.util.Log;

import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * •	קוד דגם
 •	שם חברה
 •	שם דגם
 •	נפח מנוע
 •	תיבת הילוכים {אוטומטי, ידני}
 •	מקומות ישיבה
 •	וכו...

 */
public class CarModel implements  IArray{

    public enum GearType {
        Manual,
        Automatic
    }

    protected Integer _id;
    protected String companyName;
    protected String modelName;
    protected Integer engineSize;
    protected GearType gearType;
    protected Integer seats;

    public CarModel(Integer _id, String companyName, String modelName, Integer engineSize, GearType gearType, Integer seats) {
        this._id = _id;
        this.companyName = companyName;
        this.modelName = modelName;
        this.engineSize = engineSize;
        this.gearType = gearType;
        this.seats = seats;
    }

    public CarModel(String[] properties) {
        try{
            this._id = Integer.parseInt(properties[0]);
            this.companyName = properties[1];
            this.modelName = properties[2];
            this.engineSize = Integer.parseInt(properties[3]);
            this.gearType = (GearType.valueOf(properties[4]));
            this.seats =  Integer.parseInt(properties[5]);
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    @Override
    public ArrayList<String> getArrayList() {
        return new ArrayList<>(Arrays.asList(this._id.toString(),
                this.companyName,
                this.modelName,
                this.engineSize.toString(),
                this.gearType.toString(),
                this.seats.toString()));
    }

    public CarModel(ContentValues properties) {
        try {
            this._id = properties.getAsInteger(ContentProviderContracts.Models.ID);
            this.companyName = properties.getAsString(ContentProviderContracts.Models.COMPANY_NAME);
            this.modelName = properties.getAsString(ContentProviderContracts.Models.MODEL_NAME);
            this.engineSize = 2000;
            this.gearType =  GearType.Manual;
            this.seats = 4;

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG, e);
        }
    }
    public String[] getArray() {
        return new String[]{
                this._id.toString(),
                this.companyName,
                this.modelName,
                this.engineSize.toString(),
                this.gearType.toString(),
                this.seats.toString()
        };
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Integer engineSize) {
        this.engineSize = engineSize;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "_id='" + _id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", engineSize=" + engineSize +
                ", gearType=" + gearType +
                ", seats=" + seats +
                '}';
    }
}
