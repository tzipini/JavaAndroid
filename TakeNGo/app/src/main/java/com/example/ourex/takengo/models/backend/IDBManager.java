package com.example.ourex.takengo.models.backend;


import android.content.Context;

import com.example.ourex.takengo.models.entities.Branch;
import com.example.ourex.takengo.models.entities.Car;
import com.example.ourex.takengo.models.entities.CarModel;
import com.example.ourex.takengo.models.entities.Customer;
import com.example.ourex.takengo.models.entities.User;

import java.util.ArrayList;

/**
 * •	בדוק האם משתמש כבר קיים במקור הנתונים.
 •	הוסף משתמש חדש.
 •	הוסף דגם
 •	הוסף מכונית.
 •	החזר רשימת כל הדגמים הקיימים.
 •	החזר את כל רשימת המשתמשים.
 •	החזר את רשימת כל הסניפים.
 •	החזר את רשימת כל המכוניות.
 */

public interface IDBManager {
    public boolean init(Context context);
    public boolean isUserExists(User user) throws Exception;
    public boolean addNewUser(User user);
    public boolean addNewCustomer(Customer customer);
    public boolean addNewModel(CarModel carModel);
    public boolean addNewBranch(Branch branch);
    public boolean addNewCar(Car car);

    public ArrayList<CarModel> getCarModels();
    public ArrayList<Car> getCarList();
    public ArrayList<Customer> getCustomersList();
    public ArrayList<Branch> getBranchList();
    public ArrayList<User> getUsersList();

    public User getUser(String userName);

}
