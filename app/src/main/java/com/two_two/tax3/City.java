package com.two_two.tax3;

/**
 * Created by Stealps on 09.07.2015.
 */
public class City {
    public String name;
    public double latitude;
    public double longitude;

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
