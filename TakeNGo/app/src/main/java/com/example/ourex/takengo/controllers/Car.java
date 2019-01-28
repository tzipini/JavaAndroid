package com.example.ourex.takengo.controllers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.adapters.array.CarAdapter;
import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.utils.CarNoWatcher;
import com.example.ourex.takengo.utils.Constants;
import com.example.ourex.takengo.utils.Dialogs;
import com.example.ourex.takengo.utils.Globals;

import java.util.ArrayList;

public class Car extends AppCompatActivity {

    protected IDBManager idbManager =  Globals.getInstance().idbManager;

    protected EditText carNo;
    protected Spinner branchNo;
    protected Spinner modelId;
    protected EditText kilometers;
    ArrayAdapter<String> adapterBranch, adapterModels;

    ArrayList<com.example.ourex.takengo.models.entities.Car> cars = null;
    protected ListView listCars;
    CarAdapter carAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.car);
            carNo = (EditText) findViewById(R.id.carNo);
            branchNo = (Spinner) findViewById(R.id.branchNo);
            modelId = (Spinner) findViewById(R.id.modelId);
            kilometers = (EditText) findViewById(R.id.kilometers);

            carNo.addTextChangedListener(new CarNoWatcher());

            _fillModelsSpinner();
            _fillBranchesSpinner();

            loadCars();


        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    private void _fillModelsSpinner() {
        try {
            final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                    (
                            this,
                            android.R.layout.simple_spinner_item,
                            null,
                            new String[]{
                                    ContentProviderContracts.Models.MODEL_NAME,
                                    ContentProviderContracts.Models.ID,
                            },
                            new int[]{android.R.id.text1}
                    );

            new AsyncTask<Void, Void, Cursor>() {
                @Override
                protected Cursor doInBackground(Void... params) {
                    Cursor cursor = getContentResolver().query(ContentProviderContracts.Models.URI, null, null, null, null, null);
                    return cursor;
                }

                @Override
                protected void onPostExecute(Cursor cursor) {
                    super.onPostExecute(cursor);
                    if (cursor!= null)
                        adapter.changeCursor(cursor);
                }
            }.execute();
            this.modelId.setAdapter(adapter);
        } catch (Exception e) {
            Dialogs.openAlertDialog(this, "Error", e.getMessage(), "OK");
        }
    }

    private void _fillBranchesSpinner() {
        try {
            final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                    (
                            this,
                            android.R.layout.simple_spinner_item,
                            null,
                            new String[]{
                                    ContentProviderContracts.Branch.NAME,
                                    ContentProviderContracts.Branch.ID,
                            },
                            new int[]{android.R.id.text1}
                    );

            new AsyncTask<Void, Void, Cursor>() {
                @Override
                protected Cursor doInBackground(Void... params) {
                    Cursor cursor = getContentResolver().query(ContentProviderContracts.Branch.URI, null, null, null, null, null);
                    return cursor;
                }

                @Override
                protected void onPostExecute(Cursor cursor) {
                    super.onPostExecute(cursor);
                    if (cursor!= null)
                        adapter.changeCursor(cursor);
                }
            }.execute();
            this.branchNo.setAdapter(adapter);
        } catch (Exception e) {
            Dialogs.openAlertDialog(this, "Error", e.getMessage(), "OK");
        }
    }


    public void clearCar(View view) {
        kilometers.setText("");
        carNo.setText("");
    }

    @SuppressLint("StaticFieldLeak")
    private void loadCars() {
        try {
            final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                    (
                            this,
                            R.layout.row_car,
                            null,
                            new String[]{
                                    ContentProviderContracts.Car.CAR_ID,
                                    ContentProviderContracts.Car.CAR_NO,

                            },
                            new int[]{R.id._id,R.id.carNo}
                    );

            new AsyncTask<Void, Void, Cursor>() {
                @Override
                protected Cursor doInBackground(Void... params) {
                    Cursor cursor = getContentResolver().query(ContentProviderContracts.Car.URI, null, null, null, null, null);
                    return cursor;
                }

                @Override
                protected void onPostExecute(Cursor cursor) {
                    super.onPostExecute(cursor);
                    if (cursor!= null)
                        adapter.changeCursor(cursor);
                }
            }.execute();

            this.listCars = (ListView) findViewById(R.id.listCars);
            this.listCars.setAdapter(adapter);
        } catch (Exception e) {
            Dialogs.openAlertDialog(this, "Error", e.getMessage(), "OK");
        }
    }


    public void addNewCar(View view) {
        try {
            if (carNo.getText().toString().equals("")){
                Dialogs.openAlertDialog(this,getString(R.string.error_input),getString(R.string.error_input_carNo),getString(R.string.button_ok));
                return;
            }

            if (modelId.getSelectedItem().toString().equals("")){
                Dialogs.openAlertDialog(this,getString(R.string.error_input),getString(R.string.error_input_modelId),getString(R.string.button_ok));
                return;
            }

            if (kilometers.getText().toString().equals("")){
                Dialogs.openAlertDialog(this,getString(R.string.error_input),getString(R.string.error_input_km),getString(R.string.button_ok));
                return;
            }

            final ContentValues contentValues = new ContentValues();
            contentValues.put(ContentProviderContracts.Car.CAR_NO, this.carNo.getText().toString());
            contentValues.put(ContentProviderContracts.Car.MODEL_ID, this.modelId.getSelectedItemPosition());
            contentValues.put(ContentProviderContracts.Car.BRANCH_NUMBER, this.branchNo.getSelectedItemPosition());
            contentValues.put(ContentProviderContracts.Car.TOTAL_KILOMETERS, this.kilometers.getText().toString());

            new AsyncTask<Void, Void, Uri>() {

                @Override
                protected void onPostExecute(Uri uriResult) {
                    super.onPostExecute(uriResult);
                    loadCars();
                }

                @Override
                protected Uri doInBackground(Void... params) {
                    return getContentResolver().insert(ContentProviderContracts.Car.URI, contentValues);
                }
            }.execute();

        } catch (Exception e) {
            Dialogs.openAlertDialog(this,getString(R.string.error_execption),e.getMessage(),getString(R.string.button_ok));
        }
    }
}
