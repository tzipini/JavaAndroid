package com.example.ourex.takengo.models.entities;

import android.content.ContentValues;
import android.util.Log;

import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * •	מספר הסניף בו המכונית חונה בדרך קבע.
 •	סוג דגם
 •	קילומטרז'
 •	מספר רכב
 */
public class Car implements IArray {

    protected Integer _id;
    protected String carNo;
    protected String branchNo;
    protected String modelId;
    protected Integer kilometers;

    public Car(int _id,String carNo, String branchNo, String modelId, Integer kilometers) {
        this._id = _id;
        this.carNo = carNo;
        this.branchNo = branchNo;
        this.modelId = modelId;
        this.kilometers = kilometers;
    }

    @Override
    public ArrayList<String> getArrayList() {
        return new ArrayList<>(Arrays.asList(this._id.toString(), this.carNo.toString()));
    }

    public Car(String[] properties) {
        try{
            this._id = Integer.parseInt(properties[0]);
            this.carNo = properties[1];
            this.branchNo = properties[2];
            this.modelId = properties[3];
            this.kilometers = Integer.parseInt(properties[4]);
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    public Car(ContentValues properties) {
        try {
            this._id = 0;
            this.carNo = properties.getAsString(ContentProviderContracts.Car.CAR_NO);
            this.modelId = properties.getAsString(ContentProviderContracts.Car.MODEL_ID);
            this.branchNo = properties.getAsString(ContentProviderContracts.Car.BRANCH_NUMBER);
            this.kilometers = properties.getAsInteger(ContentProviderContracts.Car.TOTAL_KILOMETERS);

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG, e);
        }
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    @Override
    public String toString() {
        return "Car{" +
                "_id=" + _id +
                "carNo=" + carNo +
                ", branchNo=" + branchNo +
                ", _id='" + modelId + '\'' +
                ", kilometers=" + kilometers +
                '}';
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
