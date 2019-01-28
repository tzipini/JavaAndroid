package com.example.ourex.takengo.models.datasources;

import android.content.Context;
import android.util.Log;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.models.entities.Branch;
import com.example.ourex.takengo.models.entities.Car;
import com.example.ourex.takengo.models.entities.CarModel;
import com.example.ourex.takengo.models.entities.Customer;
import com.example.ourex.takengo.models.entities.Order;
import com.example.ourex.takengo.models.entities.User;
import com.example.ourex.takengo.utils.Constants;

import java.util.ArrayList;

public class Lists implements IDBManager {

    protected Context context;
    protected ArrayList<User> users = new ArrayList<User>();
    protected ArrayList<Customer> customers = new ArrayList<Customer>();
    protected ArrayList<Car> cars = new ArrayList<Car>();
    protected ArrayList<CarModel> carModels = new ArrayList<CarModel>();
    protected ArrayList<Branch> branches = new ArrayList<Branch>();
    protected ArrayList<Order> orders = new ArrayList<Order>();

    private boolean _loadUsers(){
        try {
            String[] items = context.getResources().getStringArray(R.array.users);
            for (String item : items) {
                this.users.add(new User(item.split(",")));
            }
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
        return  true;
    }

    private boolean _loadCars(){
        String[] items = context.getResources().getStringArray(R.array.cars);
        for (String item : items) {
            this.cars.add(new Car(item.split(",")));
        }
        return  true;
    }

    private boolean _loadCustomers(){
        return  true;
    }

    private boolean _loadBranches(){
        String[] items = context.getResources().getStringArray(R.array.branches);
        for (String item : items) {
            this.branches.add(new Branch(item.split(",")));
        }
        return  true;
    }

    private boolean _loadCarModels(){
        try {
            String[] items = context.getResources().getStringArray(R.array.cars_models);
            for (String item : items) {
                this.carModels.add(new CarModel(item.split(",")));
            }
        }
        catch(Exception e){
            Log.w(Constants.Log.APP_LOG,e);
        }
        return  true;
    }

    @Override
    public boolean init(Context context) {
        this.context = context;

        _loadUsers();
        _loadCars();
        _loadBranches();
        _loadCustomers();
        _loadCarModels();

        return true;
    }

    @Override
    public boolean isUserExists(User user) {
        for (User u : users) {
            if (u.getEmail().equals((user.getEmail())) && u.getPassword().equals((user.getPassword())))
                return true;
        }
        return false;
    }

    @Override
    public boolean addNewUser(User user) {
        this.users.add(user);
        return true;
    }

    @Override
    public boolean addNewCustomer(Customer customer) {
        this.customers.add(customer);
        return true;
    }

    @Override
    public boolean addNewModel(CarModel carModel) {
        carModel.set_id(this.cars.size() + 1);
        this.carModels.add(carModel);
        return true;
    }

    @Override
    public boolean addNewBranch(Branch branch) {
        this.branches.add(branch);
        return true;
    }

    @Override
    public boolean addNewCar(Car car) {
        car.set_id(this.cars.size() + 1);
        this.cars.add(car);
        return true;
    }

    @Override
    public ArrayList<CarModel> getCarModels() {
        return this.carModels;
    }

    @Override
    public ArrayList<Car> getCarList() {
        return this.cars;
    }

    @Override
    public ArrayList<Customer> getCustomersList() {
        return this.customers;
    }

    @Override
    public ArrayList<Branch> getBranchList() {
        return this.branches;
    }

    @Override
    public ArrayList<User> getUsersList() {
        return this.users;
    }

    @Override
    public User getUser(String userName) {
        for (User u : users) {
            if (u.getEmail().equals((userName)))
                return u;
        }
        return null;
    }
}
