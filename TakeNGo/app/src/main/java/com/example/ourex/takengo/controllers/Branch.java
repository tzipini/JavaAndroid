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
import com.example.ourex.takengo.adapters.array.BranchAdapter;
import com.example.ourex.takengo.contentProviders.ContentProviderContracts;
import com.example.ourex.takengo.models.backend.IDBManager;
import com.example.ourex.takengo.models.entities.*;
import com.example.ourex.takengo.utils.Constants;
import com.example.ourex.takengo.utils.Dialogs;
import com.example.ourex.takengo.utils.Globals;

import java.util.ArrayList;

public class Branch extends AppCompatActivity {

    protected EditText branchName;
    protected EditText branchNo;
    protected ListView listBranches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.branch);
            branchName = (EditText) findViewById(R.id.branchName);
            branchNo = (EditText) findViewById(R.id.branchNo);
            loadBranches();

        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    protected void addBranch(View view) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(ContentProviderContracts.Branch.ID, this.branchNo.getText().toString());
        contentValues.put(ContentProviderContracts.Branch.NAME, this.branchName.getText().toString());

        new AsyncTask<Void, Void, Uri>() {

            @Override
            protected void onPostExecute(Uri uriResult) {
                super.onPostExecute(uriResult);
                loadBranches();
            }

            @Override
            protected Uri doInBackground(Void... params) {
                return getContentResolver().insert(ContentProviderContracts.Branch.URI, contentValues);
            }
        }.execute();
    }


    public void clearBranch(View view) {
        branchName.setText("");
        branchNo.setText("");

    }

    @SuppressLint("StaticFieldLeak")
    private void loadBranches() {
        try {
            final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                    (
                            this,
                            R.layout.row_branch,
                            null,
                            new String[]{
                                    ContentProviderContracts.Branch.ID,
                                    ContentProviderContracts.Branch.NAME
                            },
                            new int[]{R.id._id,R.id.name}
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

            this.listBranches = (ListView) findViewById(R.id.listBranches);
            this.listBranches.setAdapter(adapter);
        } catch (Exception e) {
            Dialogs.openAlertDialog(this, "Error", e.getMessage(), "OK");
        }
    }

}
