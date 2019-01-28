package com.example.ourex.takengo.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.utils.Globals;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.main_title + "," + Globals.getInstance().getCurrentUser().getFullName());
    }

    public void openActivity(View view) {
        switch(view.getId()){
            case R.id.btnBranches:
                startActivity(new Intent(this,Branch.class));
                break;
            case R.id.btnCars:
                startActivity(new Intent(this,Car.class));
                break;
            case R.id.btnOrders:
                startActivity(new Intent(this,Order.class));
                break;
            case R.id.btnUsers:
                startActivity(new Intent(this,User.class));
                break;
            case R.id.btnCarModels:
                startActivity(new Intent(this,CarModel.class));
                break;
        }
    }

}
