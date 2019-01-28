package com.example.ourex.takengo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.models.entities.Branch;

import java.util.ArrayList;
import java.util.Random;

public class BranchAdapter extends ArrayAdapter<Branch> {
    public BranchAdapter(Context context, ArrayList<Branch> branches) {
        super(context, 0, branches);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //-- Get the data item for this position
        Branch branch = getItem(position);

        //-- Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_branch, parent, false);
        }
        //-- Lookup view for data population
        TextView number = (TextView) convertView.findViewById(R.id.number);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        RatingBar rating= (RatingBar) convertView.findViewById(R.id.rating);

        // Populate the data into the template view using the data object
        number.setText(branch.getNumber().toString());
        name.setText(branch.getName());
        rating.setRating(new Random().nextInt(5) + 1);


        // Return the completed view to render on screen
        return convertView;
    }
}
