package com.store.zumic.service;


import com.store.zumic.dto.CustomerRegistrationDto;
import com.store.zumic.models.City;
import com.store.zumic.service.exception.CustomerAlreadyExistException;

public interface CustomerService {

    void create_account(CustomerRegistrationDto registrationDto) throws CustomerAlreadyExistException;

    void verify_account();

}
