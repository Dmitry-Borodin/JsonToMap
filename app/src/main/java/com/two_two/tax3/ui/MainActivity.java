package com.two_two.tax3.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.two_two.tax3.City;
import com.two_two.tax3.JSON.JsonParserCities;
import com.two_two.tax3.JSON.ProgressDialogForJson;
import com.two_two.tax3.R;
import com.two_two.tax3.Taxic;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayList<City> listOfCities = new ArrayList<>();
    private AdapterForCities mAdapterForCities;
    private RecyclerView mRecyclerViewCities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapterForCities = new AdapterForCities(listOfCities);

        ProgressDialogForJson.pDialogProgress(this);
        JsonParserCities.sendJsonRequest(mAdapterForCities, listOfCities);
        ProgressDialogForJson.pDialogHide();
        Log.e(Taxic.TAG, "after JSON");

        mRecyclerViewCities = (RecyclerView) findViewById(R.id.recycleVIew);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewCities.setLayoutManager(mLayoutManager);
        mRecyclerViewCities.setAdapter(mAdapterForCities);

        mRecyclerViewCities.addOnItemTouchListener(new CiliesTouchListener
                (getApplicationContext(), new CiliesTouchListener.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        intent.putExtra("latitude", mAdapterForCities.getItem(position).latitude);
                        intent.putExtra("longitude", mAdapterForCities.getItem(position).longitude);
                        intent.putExtra("cityName", mAdapterForCities.getItem(position).name);
                        startActivity(intent);
                    }
                }));
    }

}
