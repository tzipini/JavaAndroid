package com.example.ourex.takengo.controllers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.models.entities.User;
import com.example.ourex.takengo.services.TimerService;
import com.example.ourex.takengo.utils.Constants;
import com.example.ourex.takengo.utils.Dialogs;
import com.example.ourex.takengo.utils.Globals;
import com.example.ourex.takengo.utils.SharedPref;

public class Login extends AppCompatActivity {

    protected IDBManager idbManager =  Globals.getInstance().idbManager;
    protected EditText userName,password;
    protected SharedPref sharedPref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        try {
            sharedPref = new SharedPref(this);
            startService(new Intent(this, TimerService.class));
            setTitle(R.string.login_title);
            userName = (EditText) findViewById(R.id.userName);
            password = (EditText) findViewById(R.id.password);

            userName.setText(sharedPref.getString("userName"));

            Globals.getInstance().idbManager.init(this);
        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void login(View view) {
        final String name = userName.getText().toString();
        final String pass =password.getText().toString();
        sharedPref.putString("userName",name);

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                Boolean result = false;
                try{
                    result =  idbManager.isUserExists(new User(name,pass));
                    if (result == true)

                        startActivity(new Intent(Login.this,Main.class));
                }
                catch(Exception e){
                    Log.w(Constants.Log.APP_LOG,e);
                }

                return result;
            }

        }.execute();
    }

    public void clearLogin(View view) {
        userName.setText("");
        password.setText("");
    }
}
