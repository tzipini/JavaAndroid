package com.example.ourex.takengo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.models.entities.User;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //-- Get the data item for this position
        User user = getItem(position);

        //-- Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_user, parent, false);
        }
        //-- Lookup view for data population
        TextView firstName = (TextView) convertView.findViewById(R.id.firstName);
        TextView lastName = (TextView) convertView.findViewById(R.id.lastName);
        TextView tel = (TextView) convertView.findViewById(R.id.tel);
        TextView email = (TextView) convertView.findViewById(R.id.tel);
        TextView password = (TextView) convertView.findViewById(R.id.password);

        // Populate the data into the template view using the data object
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        tel.setText(user.getTel());
        email.setText(user.getEmail());
        password.setText(user.getPassword());

        // Return the completed view to render on screen
        return convertView;
    }
}
