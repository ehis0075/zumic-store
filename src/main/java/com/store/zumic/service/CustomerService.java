package com.store.zumic.service;


import com.store.zumic.dto.AddMealRequest;
import com.store.zumic.dto.CustomerRegistrationDto;
import com.store.zumic.dto.OrderRequest;
import com.store.zumic.dto.UpdateCustomerProfileDto;
import com.store.zumic.models.City;
import com.store.zumic.models.Customer;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.exception.CustomerAlreadyExistException;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {

    void create_account(Customer registrationDto) throws CustomerAlreadyExistException;

    void verify_account();

    Customer getLoggedInUser();

    void updateProfile(UpdateCustomerProfileDto updateCustomerProfileDto);

    List<ServiceProvider> findAllServiceProvidersByCity(String city) throws ServiceProviderNotFoundException;

    List<Customer> findAllCustomerByDate(String date) throws ServiceProviderNotFoundException, ParseException, MissingServletRequestParameterException;

    void placeOrder(OrderRequest orderRequest);

    //get All my orders

    //reset password

    //
}
