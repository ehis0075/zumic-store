package com.store.zumic.utils;

import com.store.zumic.models.City;

public class ConvertStringToEnum {

    public static City convertStringToEnum(String city) {

        city = city.toUpperCase();

        City enumCity = City.valueOf(city);
        return enumCity;
    }
}
