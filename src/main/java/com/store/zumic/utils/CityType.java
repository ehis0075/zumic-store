package com.store.zumic.utils;

import com.store.zumic.models.City;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;

import java.util.Arrays;

public class CityType {

    public static City getCityType(final String value) throws ServiceProviderNotFoundException {

        for (final City city: City.values()){
            if(city.toString().equalsIgnoreCase(value)) {
                return city;
            }
        }
        //final String message = "UnKnown City "+ value + ", allowed values are " + Arrays.toString(City.values());
       // throw new Exception(message);
        throw new ServiceProviderNotFoundException("No service providers found in  "+ value);
    }
}
