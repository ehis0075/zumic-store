package com.store.zumic.service;


import com.store.zumic.dto.AddMealRequest;
import com.store.zumic.dto.UpdateMealRequest;
import com.store.zumic.dto.UpdateServiceProviderProfileDto;
import com.store.zumic.models.Customer;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.exception.ServiceProviderAlreadyExistException;


public interface ServiceProviderService {

    void create(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException;

    void addFood(AddMealRequest addMealRequest);

    public ServiceProvider getLoggedInUser();

    void updateProfile(UpdateServiceProviderProfileDto updateCustomerProfileDto);

    void deleteMeal(String mealName);

    void editMeal(UpdateMealRequest updateMealRequest);

    //delete a meal

    //get All the Customers that has ordered from me

    //get All the Customers that has ordered from me By City
}

