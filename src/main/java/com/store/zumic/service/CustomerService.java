package com.store.zumic.service;


import com.store.zumic.dto.RegistrationDto;
import com.store.zumic.service.exception.CustomerAlreadyExistException;

public interface CustomerService {

    void create_account(RegistrationDto registrationDto) throws CustomerAlreadyExistException;

    void verify_account();


}
