package com.example.ourex.takengo.adapters.array;

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
        TextView _id = (TextView) convertView.findViewById(R.id._id);
        TextView name = (TextView) convertView.findViewById(R.id.name);

        _id.setText(branch.getId().toString());
        name.setText(branch.getName());
        return convertView;
    }
}
