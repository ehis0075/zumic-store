package com.store.zumic.utils;

import com.store.zumic.models.City;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CityJpaConverter implements AttributeConverter<City, String> {

    @Override
    public String convertToDatabaseColumn(City city) {
        if(city == null) {
            return null;
        }
        return city.toString();
    }

    @Override
    public City convertToEntityAttribute(String string) {
        if(string == null){
            return null;
        }
        try {
            return City.valueOf(string);
        } catch(IllegalArgumentException e){
            return null;
        }
    }
}
