package com.store.zumic.service;

import com.store.zumic.dto.CustomerRegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {

    private CustomerService customerService;

    CustomerRegistrationDto customerRegistrationDto;

    @BeforeEach
    void setUp() {
    }

    @Test
    void canCreateAccount() {
        //customerService.create_account();
    }
}