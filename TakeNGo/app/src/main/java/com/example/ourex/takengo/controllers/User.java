package com.example.ourex.takengo.controllers;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.adapters.array.UsersAdapter;
import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.utils.Dialogs;
import com.example.ourex.takengo.utils.Globals;

import java.util.ArrayList;

public class User extends AppCompatActivity {

    protected ListView listUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        LoadUsers();
    }

    @SuppressLint("StaticFieldLeak")
    private void LoadUsers() {
        try {
            final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                    (
                            this,
                            R.layout.row_user,
                            null,
                            new String[]{
                                    ContentProviderContracts.User.USER_ID,
                                    ContentProviderContracts.User.IDENTITY_NO,
                                    ContentProviderContracts.User.FIRST_NAME,
                                    ContentProviderContracts.User.LAST_NAME,
                                    ContentProviderContracts.User.TEL,
                                    ContentProviderContracts.User.EMAIL,
                                    ContentProviderContracts.User.PASSWORD,
                            },
                            new int[]{R.id._id,R.id.identityNo,R.id.firstName, R.id.lastName, R.id.tel, R.id.email, R.id.password}
                    );

            new AsyncTask<Void, Void, Cursor>() {
                @Override
                protected Cursor doInBackground(Void... params) {
                    Cursor cursor = getContentResolver().query(ContentProviderContracts.User.URI, null, null, null, null, null);
                    return cursor;
                }

                @Override
                protected void onPostExecute(Cursor cursor) {
                    super.onPostExecute(cursor);
                    adapter.changeCursor(cursor);
                }
            }.execute();

            this.listUsers = (ListView) findViewById(R.id.listUsers);
            this.listUsers.setAdapter(adapter);
        } catch (Exception e) {
            Dialogs.openAlertDialog(this, "Error", e.getMessage(), "OK");
        }
    }
}
