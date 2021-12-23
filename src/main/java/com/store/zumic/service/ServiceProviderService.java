package com.store.zumic.service;


import com.store.zumic.dto.AddMealRequest;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.exception.ServiceProviderAlreadyExistException;


public interface ServiceProviderService {

    void create(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException;

    void addFood(AddMealRequest addMealRequest);

    //edit service provider profile

    //edit a meal

    //delete a meal

    //get All the Customers that has ordered from me

    //get All the Customers that has ordered from me By City
}

