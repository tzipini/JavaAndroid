package com.example.ourex.takengo.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ourex.takengo.R;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        setTitle(R.string.order_title);
    }
}
