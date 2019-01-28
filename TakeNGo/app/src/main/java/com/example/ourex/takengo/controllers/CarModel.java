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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.adapters.array.CarModelAdapter;
import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.utils.Constants;
import com.example.ourex.takengo.utils.Dialogs;
import com.example.ourex.takengo.utils.Globals;

import java.util.ArrayList;

public class CarModel extends AppCompatActivity {

    protected IDBManager idbManager =  Globals.getInstance().idbManager;

    protected EditText modelId;
    protected EditText companyName;
    protected EditText modelName;


    ArrayList<com.example.ourex.takengo.models.entities.CarModel> carModels = null;
    protected ListView listCarModels;
    CarModelAdapter carModelAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_model);

        try {
            setContentView(R.layout.car_model);
            modelId = (EditText) findViewById(R.id.carModelId);
            companyName = (EditText) findViewById(R.id.carModelCompanyName);
            modelName = (EditText) findViewById(R.id.carModelModelName);
            loadCarModels();

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }

    }

    public void addCarModel(View view) {
        try {
            if (modelId.getText().toString().equals("")){
                Dialogs.openAlertDialog(this,getString(R.string.error_input),getString(R.string.error_input_modelId),getString(R.string.button_ok));
                return;
            }

            if (companyName.getText().toString().equals("")){
                Dialogs.openAlertDialog(this,getString(R.string.error_input),getString(R.string.error_input_companyName),getString(R.string.button_ok));
                return;
            }

            if (modelName.getText().toString().equals("")){
                Dialogs.openAlertDialog(this,getString(R.string.error_input),getString(R.string.error_input_modelName),getString(R.string.button_ok));
                return;
            }

            final ContentValues contentValues = new ContentValues();
            contentValues.put(ContentProviderContracts.Models.ID, 0);
            contentValues.put(ContentProviderContracts.Models.MODEL_ID, this.modelId.getText().toString());
            contentValues.put(ContentProviderContracts.Models.COMPANY_NAME, this.companyName.getText().toString());
            contentValues.put(ContentProviderContracts.Models.MODEL_NAME, this.modelName.getText().toString());

            new AsyncTask<Void, Void, Uri>() {

                @Override
                protected void onPostExecute(Uri uriResult) {
                    super.onPostExecute(uriResult);
                    loadCarModels();
                }

                @Override
                protected Uri doInBackground(Void... params) {
                    return getContentResolver().insert(ContentProviderContracts.Models.URI, contentValues);
                }
            }.execute();

        } catch (Exception e) {
            Dialogs.openAlertDialog(this,getString(R.string.error_execption),e.getMessage(),getString(R.string.button_ok));
        }
    }

    public void clearCarModel(View view) {
        modelId.setText("");
        companyName.setText("");
        modelName.setText("");
    }


    @SuppressLint("StaticFieldLeak")
    private void loadCarModels() {
        try {
            final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                    (
                            this,
                            R.layout.row_car_model,
                            null,
                            new String[]{
                                    ContentProviderContracts.Models.ID,
                                    ContentProviderContracts.Models.COMPANY_NAME,
                                    ContentProviderContracts.Models.MODEL_NAME,
                                    ContentProviderContracts.Models.ENGINE_SIZE,
                                    ContentProviderContracts.Models.GEAR_TYPE,
                                    ContentProviderContracts.Models.SEATS
                            },
                            new int[]{R.id._id,R.id.companyName,R.id.modelName,R.id.engineSize,R.id.gearType,R.id.seats}
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

            this.listCarModels = (ListView) findViewById(R.id.listCarModels);
            this.listCarModels.setAdapter(adapter);
        } catch (Exception e) {
            Dialogs.openAlertDialog(this, "Error", e.getMessage(), "OK");
        }
    }
}
