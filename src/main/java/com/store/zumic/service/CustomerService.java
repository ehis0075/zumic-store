package com.store.zumic.service;


import com.store.zumic.dto.CustomerRegistrationDto;
import com.store.zumic.dto.UpdateCustomerProfileDto;
import com.store.zumic.models.City;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.exception.CustomerAlreadyExistException;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;

import java.util.List;

public interface CustomerService {

    void create_account(CustomerRegistrationDto registrationDto) throws CustomerAlreadyExistException;

    void verify_account();

    void updateProfile(UpdateCustomerProfileDto updateCustomerProfileDto);

    List<ServiceProvider> findAllServiceProvidersByCity(String city) throws ServiceProviderNotFoundException;

}
