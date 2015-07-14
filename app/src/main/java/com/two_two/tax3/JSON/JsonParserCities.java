package com.two_two.tax3.JSON;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.two_two.tax3.City;
import com.two_two.tax3.Taxic;
import com.two_two.tax3.ui.AdapterForCities;
import com.two_two.tax3.volley.MyApplication;
import com.two_two.tax3.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DmitryBorodin on 04.06.2015.
 * This parse default api for recent pictures. Sometimes i get error 400, so may be try register app sometimes.
 */
public class JsonParserCities {
    private static VolleySingleton volleySingleton = VolleySingleton.getInstance();
    private static RequestQueue requestQueue = volleySingleton.getRequestQueue();

    public JsonParserCities() {
        requestQueue = volleySingleton.getRequestQueue();
        volleySingleton = VolleySingleton.getInstance();
    }

    public static void sendJsonRequest(final AdapterForCities adapter, final List<City> listOfCities) {

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response == null || response.length() <= 0) {
                    Log.e(Taxic.TAG, "Response is NULL!!!");
                }
                try {
            /*Here we will save what we need from JSON. Just links to pictures in our case.*/
                    if (response != null) {
                        JSONArray arrayPictures = response.getJSONArray("cities");

                        for (int i = 0; i < arrayPictures.length(); i++) {
                            JSONObject currentjsonObject = arrayPictures.getJSONObject(i);
                            City city = new City();
                            if (currentjsonObject.has("city_name")) city.name = currentjsonObject.getString("city_name");
                            if (currentjsonObject.has("city_latitude")) city.latitude = currentjsonObject.getDouble("city_latitude");
                            if (currentjsonObject.has("city_longitude")) city.longitude = currentjsonObject.getDouble("city_longitude");
                                listOfCities.add(city);
                        }
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MyApplication.getAppContext(),"Error with JsonParser",Toast.LENGTH_LONG).show();
                }
                Log.e(Taxic.TAG, "JSON parsed");
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(Taxic.TAG, "Error: " + error.getMessage());
                Toast.makeText(MyApplication.getAppContext(), "Server not responding", Toast.LENGTH_SHORT).show();
            }
        };

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Taxic.JSONURL, listener, errorListener);

        requestQueue.add(jsonObjReq);

    }
}
