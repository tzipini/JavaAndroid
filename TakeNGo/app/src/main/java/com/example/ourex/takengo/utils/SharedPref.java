package com.example.ourex.takengo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by OurEx on 11/27/2017.
 */

public class SharedPref {
    protected  Context context;
    protected SharedPreferences preferences;

    public SharedPref(Context context){
        this.context = context;
        this.preferences = context.getSharedPreferences(Constants.SharedPreferences.File, Context.MODE_PRIVATE);
    }

    public String getString(String key){
        String value = preferences.getString(key, "");
        return value;
    }

    public void putString(String key,String value){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString(key,value);
        editor.commit();
    }
}
