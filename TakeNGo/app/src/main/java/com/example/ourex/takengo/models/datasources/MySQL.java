package com.example.ourex.takengo.models.datasources;


import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.models.entities.Branch;
import com.example.ourex.takengo.models.entities.Car;
import com.example.ourex.takengo.models.entities.CarModel;
import com.example.ourex.takengo.models.entities.Customer;
import com.example.ourex.takengo.models.entities.User;
import com.example.ourex.takengo.utils.Constants;
import com.example.ourex.takengo.utils.Php;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MySQL implements IDBManager {
    private final String API = "http://lachman.vlab.jct.ac.il/TakeNGo";


    @Override
    public boolean init(Context context) {
        return false;
    }

    @Override
    public boolean isUserExists(User user) {
        boolean result = false;
        try{
            String url = String.format("%s/users/is_user_exists.php?email=%s&password=%s", API,user.getEmail(),user.getPassword()); //-- http://lachman.vlab.jct.ac.il/TakeNGo/is_user_exists.php?email=a&password=1234
            String php =  Php.GET(url);

            JSONArray users = new JSONObject(php).getJSONArray("users");
            JSONObject u = users.getJSONObject(0);
            Gson gson = new Gson();
            User u1 = gson.fromJson(u.toString(),User.class);
            result =  (u1.isValid());

        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }

        return  result;
    }

    @Override
    public boolean addNewUser(User user) {
        return false;
    }

    @Override
    public boolean addNewCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean addNewModel(CarModel carModel) {

        ContentValues values = new ContentValues();
        values.put(ContentProviderContracts.Models.MODEL_ID, carModel.getCompanyName());
        values.put(ContentProviderContracts.Models.MODEL_NAME, carModel.getCompanyName());
        values.put(ContentProviderContracts.Models.COMPANY_NAME, carModel.getCompanyName());
        try {
            String url = String.format("%s/models/add_model.php", API);
            String php =  Php.POST(url,values);

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }
        return true;
    }

    @Override
    public boolean addNewBranch(Branch branch) {
        ContentValues values = new ContentValues();
        values.put(ContentProviderContracts.Branch.NAME, branch.getName());
        try {
            String url = String.format("%s/branches/add_branch.php", API);
            String php =  Php.POST(url,values);

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }
        return true;
    }

    @Override
    public boolean addNewCar(Car car) {

        ContentValues values = new ContentValues();
        values.put(ContentProviderContracts.Car.CAR_NO, car.getCarNo());
        values.put(ContentProviderContracts.Car.MODEL_ID, car.getModelId());
        values.put(ContentProviderContracts.Car.BRANCH_NUMBER, car.getBranchNo());
        values.put(ContentProviderContracts.Car.TOTAL_KILOMETERS, car.getKilometers());
        try {
            String url = String.format("%s/cars/add_car.php", API);
            String php =  Php.POST(url,values);

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }
        return true;
    }

    @Override
    public ArrayList<CarModel> getCarModels() {
        ArrayList<CarModel> carModels = new ArrayList<>();
        try{
            String url = String.format("%s/models/get_models.php", API);
            String php =  Php.GET(url);

            JSONArray items = new JSONObject(php).getJSONArray("models");
            Gson gson = new Gson();
            for (int i=0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                CarModel carModel = gson.fromJson(item.toString(),CarModel.class);
                carModels.add(carModel);
            }
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }

        return carModels;
    }

    @Override
    public ArrayList<Car> getCarList() {
        ArrayList<Car> cars = new ArrayList<>();
        try{
            String url = String.format("%s/cars/get_cars.php", API);
            String php =  Php.GET(url);

            JSONArray items = new JSONObject(php).getJSONArray("cars");
            Gson gson = new Gson();
            for (int i=0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                Car car = gson.fromJson(item.toString(),Car.class);
                cars.add(car);
            }
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }

        return cars;

    }

    @Override
    public ArrayList<Customer> getCustomersList() {
        return null;
    }

    @Override
    public ArrayList<Branch> getBranchList() {

        ArrayList<Branch> branches = new ArrayList<>();
        try{
            String url = String.format("%s/branches/get_branches.php", API);
            String php =  Php.GET(url);

            JSONArray items = new JSONObject(php).getJSONArray("branches");
            Gson gson = new Gson();
            for (int i=0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                Branch branch = gson.fromJson(item.toString(),Branch.class);
                branches.add(branch);
            }
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }

        return branches;
    }

    @Override
    public ArrayList<User> getUsersList() {
        ArrayList<User> users = new ArrayList<>();
        try{
            String url = String.format("%s/users/get_users.php", API);
            String php =  Php.GET(url);

            JSONArray items = new JSONObject(php).getJSONArray("users");
            Gson gson = new Gson();
            for (int i=0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                User user = gson.fromJson(item.toString(),User.class);
                users.add(user);
            }
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }

        return users;
    }

    @Override
    public User getUser(String userName) { return null;}
}
