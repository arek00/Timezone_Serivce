package com.arek00.timezone.services;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.arek00.timezone.R;
import com.arek00.timezone.content.City;

import java.util.List;

/**
 * Created by Admin on 2015-01-25.
 */
public class MyAdapter extends ArrayAdapter<City> {
    Context context;

    public MyAdapter(Context context, City[] cities) {
        super(context, R.layout.list_item, cities);
        this.context = context;
    }


    public View getView(int position, View view, ViewGroup group) {
        View line = view;

        if (line == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            line = inflater.inflate(R.layout.list_item, null);
        }

        City city = getItem(position);

        String cityName = city.getName();
        String timezone = "UTC: " + city.getUTCOffset();

        ((TextView) line.findViewById(R.id.cityField)).setText(cityName);
        ((TextView) line.findViewById(R.id.timezoneField)).setText(timezone);

        return line;
    }
}

