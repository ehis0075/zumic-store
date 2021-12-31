package com.store.zumic.service;


import com.store.zumic.dto.CustomerRegistrationDto;
import com.store.zumic.dto.UpdateCustomerProfileDto;
import com.store.zumic.models.City;
import com.store.zumic.models.Customer;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.exception.CustomerAlreadyExistException;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;

import java.util.List;

public interface CustomerService {

    void create_account(Customer registrationDto) throws CustomerAlreadyExistException;

    void verify_account();

    Customer getLoggedInUser();

    void updateProfile(UpdateCustomerProfileDto updateCustomerProfileDto);

    List<ServiceProvider> findAllServiceProvidersByCity(String city) throws ServiceProviderNotFoundException;

    //get All my orders

    //reset password

    //
}
