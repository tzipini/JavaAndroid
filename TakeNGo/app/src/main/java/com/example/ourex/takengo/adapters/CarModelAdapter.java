package com.example.ourex.takengo.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.models.entities.CarModel;

import java.util.ArrayList;

public class CarModelAdapter extends ArrayAdapter<CarModel> {
    public CarModelAdapter(Context context, ArrayList<CarModel> branches) {
        super(context, 0, branches);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //-- Get the data item for this position
        CarModel carModel = getItem(position);

        //-- Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_car_model, parent, false);
        }
        //-- Lookup view for data population
        TextView modelId = (TextView) convertView.findViewById(R.id.modelId);
        TextView companyName = (TextView) convertView.findViewById(R.id.companyName);
        TextView modelName = (TextView) convertView.findViewById(R.id.modelName);
        TextView engineSize = (TextView) convertView.findViewById(R.id.engineSize);
        TextView gearType = (TextView) convertView.findViewById(R.id.gearType);
        TextView seats = (TextView) convertView.findViewById(R.id.seats);


        // Populate the data into the template view using the data object
        modelId.setText(carModel.getModelId());
        companyName.setText(carModel.getCompanyName());
        modelName.setText(carModel.getModelName());
        engineSize.setText(carModel.getEngineSize().toString());
        gearType.setText(carModel.getGearType().toString());
        seats.setText(carModel.getSeats().toString());

        // Return the completed view to render on screen
        return convertView;
    }
}


