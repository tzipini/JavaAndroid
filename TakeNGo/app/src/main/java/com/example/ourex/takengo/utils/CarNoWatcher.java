package com.example.ourex.takengo.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by OurEx on 11/11/2017.
 */

public class CarNoWatcher implements TextWatcher {
    private boolean lock;


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        try {
            if (lock || editable.length() > 11) {
                editable.delete(11,1);
                return;
            }
            lock = true;
            for (int i = 3; i < editable.length(); i += 4) {
                if (editable.toString().charAt(i) != '-') {
                    editable.insert(i, "-");
                }
            }
            lock = false;

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }

    }
}
