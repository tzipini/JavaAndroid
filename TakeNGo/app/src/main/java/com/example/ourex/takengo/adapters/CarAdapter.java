package com.example.ourex.takengo.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ourex.takengo.R;
import com.example.ourex.takengo.models.entities.Car;
import com.example.ourex.takengo.utils.Dialogs;

import java.util.ArrayList;

public class CarAdapter extends ArrayAdapter<Car> {
    protected Context context;
    public CarAdapter(Context context, ArrayList<Car> cars) {
        super(context, 0, cars);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //-- Get the data item for this position
        Car car = getItem(position);

        //-- Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_car, parent, false);
        }
        //-- Lookup view for data population
        TextView carNo = (TextView) convertView.findViewById(R.id.carNo);
        RadioButton selectCar = (RadioButton)convertView.findViewById(R.id.selectCar);

        selectCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Dialogs.openAlertDialog(context,"CAR","CLICK ON CAR","OK");
            }
        });
        // Populate the data into the template view using the data object
        carNo.setText(super.getContext().getString(R.string.carsNo) + car.getCarNo().toString());

        // Return the completed view to render on screen
        return convertView;
    }
}


