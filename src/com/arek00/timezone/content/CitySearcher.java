package com.arek00.timezone.content;

import android.util.Log;

/**
 * Created by Admin on 2015-01-25.
 */
public class CitySearcher {

    public City citySearch(String name) {
        for (City city : City.values()) {
            String cityName = city.getName();
            if (name.toLowerCase().equals(cityName.toLowerCase())) {

                Log.i("CitySearcher","Found City");


                return city;
            }
        }

        Log.i("CitySearcher","Didn't Found City");

        return null;
    }
}