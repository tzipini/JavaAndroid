package com.example.ourex.takengo.contentProviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.models.entities.Branch;
import com.example.ourex.takengo.models.entities.CarModel;
import com.example.ourex.takengo.models.entities.IArray;
import com.example.ourex.takengo.utils.Constants;
import com.example.ourex.takengo.utils.Globals;
import com.example.ourex.takengo.models.entities.User;
import com.example.ourex.takengo.models.entities.Car;
import java.util.ArrayList;

public class MainContentProvider extends ContentProvider {
    protected IDBManager idbManager = Globals.getInstance().idbManager;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        switch (uri.getLastPathSegment()) {
            case "users":
                ArrayList<User> users = idbManager.getUsersList();
                return getCursor( new String[] {
                        ContentProviderContracts.User.USER_ID,
                        ContentProviderContracts.User.IDENTITY_NO,
                        ContentProviderContracts.User.FIRST_NAME,
                        ContentProviderContracts.User.LAST_NAME,
                        ContentProviderContracts.User.TEL,
                        ContentProviderContracts.User.EMAIL,
                        ContentProviderContracts.User.PASSWORD},users);

            case "branches":
                ArrayList<Branch> branches = idbManager.getBranchList();
                return getCursor( new String[] {
                        ContentProviderContracts.Branch.ID,
                        ContentProviderContracts.Branch.NAME
                },branches);
            case "cars":
                ArrayList<Car> cars = idbManager.getCarList();
                return getCursor( new String[] {
                        ContentProviderContracts.Car.CAR_ID,
                        ContentProviderContracts.Car.CAR_NO},cars);
            case "models":
                ArrayList<CarModel> carModels = idbManager.getCarModels();
                return getCursor( new String[] {
                        ContentProviderContracts.Models.ID,
                        ContentProviderContracts.Models.COMPANY_NAME,
                        ContentProviderContracts.Models.MODEL_NAME,
                        ContentProviderContracts.Models.ENGINE_SIZE,
                        ContentProviderContracts.Models.GEAR_TYPE,
                        ContentProviderContracts.Models.SEATS
                },carModels);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (uri.getLastPathSegment()) {
            case "users":
                User user = new User(contentValues);
                idbManager.addNewUser(user);
                break;
            case "branches":
                Branch branch = new Branch(contentValues);
                idbManager.addNewBranch(branch);
                break;
            case "models":
                CarModel carModel = new CarModel(contentValues);
                idbManager.addNewModel(carModel);
                break;
            case "cars":
                Car car = new Car(contentValues);
                idbManager.addNewCar(car);
                break;
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    public  <T> MatrixCursor getCursor (String[]columns, ArrayList<T> items){
        MatrixCursor matrixCursor = new MatrixCursor(columns);
        try{
            for (T item :items)
                matrixCursor.addRow(((IArray) item).getArrayList());
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG, e.getMessage());

        }

        return matrixCursor;
    }

}
