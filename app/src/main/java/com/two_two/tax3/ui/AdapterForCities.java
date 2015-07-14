package com.two_two.tax3.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.two_two.tax3.City;
import com.two_two.tax3.R;
import com.two_two.tax3.Taxic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 10.07.2015.
 */
public class AdapterForCities extends RecyclerView.Adapter<AdapterForCities.CitiesViewHolder> {
    private List<City> listOfCities;

    public AdapterForCities(List<City> listOfCities) {
        this.listOfCities = listOfCities;
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_name_item, parent, false);
        Log.d(Taxic.TAG, listOfCities.toString());
        return new CitiesViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder viewHolder, int i) {
        City city = listOfCities.get(i);
        viewHolder.cityName.setText(city.name);
    }

    @Override
    public int getItemCount() {
        return listOfCities.size();
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    public static class CitiesViewHolder extends RecyclerView.ViewHolder{
        protected TextView cityName;

        public CitiesViewHolder(View itemView) {
            super(itemView);
            this.cityName = (TextView) itemView.findViewById(R.id.cityNameTextView);
        }
    }

    public City getItem(int position){
        return listOfCities.get(position);
    }

}
