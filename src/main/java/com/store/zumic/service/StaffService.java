package com.store.zumic.service;


import com.store.zumic.dto.UpdateServiceProviderProfileDto;
import com.store.zumic.dto.UpdateStaffProfileDto;
import com.store.zumic.models.Customer;
import com.store.zumic.models.Staff;
import com.store.zumic.service.exception.StaffAlreadyExistException;

import java.util.List;

public interface StaffService {

    void create_account(Staff registrationDto) throws StaffAlreadyExistException;

    public Staff getLoggedInUser();

    List<Customer> getAllCustomers();

    void updateProfile(UpdateStaffProfileDto updateStaffProfileDto);

}
