package com.store.zumic.repository;

import com.store.zumic.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Tag("CustomerRegistration")
@DisplayName("Customer should")
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    private Customer customer;

    private Customer customer1;

    @BeforeEach
    void init(){
        log.info("initialization ........");
        customer = new Customer();
        customer1 = new Customer();
    }

    @Test
    @Order(1)
    @DisplayName("be able to create an account")
    void setUp() {

        log.info("customer registration ........");

        customer.setFirstName("ola");
        customer.setLastName("june");
        customer.setEmail("olajune@gmail.com");
        customer.setPassword("olajunez123");

        assertThat(customer).isNotNull();

        assertAll(
                () ->  assertEquals("ola", customer.getFirstName()),
                () ->  assertEquals("olajune@gmail.com", customer.getEmail())
        );

    }

    @Test
    @Disabled
    @Order(2)
    @DisplayName("throws exception")
    void whenEmailIsInputted_thenThrowException(){

        log.info("throws exception here ........");

        customer1.setFirstName("bola");
        customer1.setLastName("agu");
        customer1.setEmail("olajune@gmail.com");
        customer1.setPassword("bolaagu123");

        Throwable error =
                assertThrows(ResponseStatusException.class, () ->   customer1.setEmail("olajune@gmail.com"));

        assertEquals("a customer with email olajune@gmail.com already exist", error.getMessage());

    }

    //update
    @Test
    @Order(3)
    @DisplayName("be able to update profile")
    void updateCustomerFirstNameAndLastName(){

        Customer savedCustomer = customerRepository.findByEmail("olajune@gmail.com");
        assertThat(savedCustomer).isNotNull();

        savedCustomer.setFirstName("ehis");
        savedCustomer.setLastName("jude");

        assertAll(
                () ->  assertEquals("ehis", savedCustomer.getFirstName()),
                () ->  assertEquals("jude", savedCustomer.getLastName())
        );


    }

    //delete


}