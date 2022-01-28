package com.store.zumic.repository;

import com.store.zumic.models.AppUser;
import com.store.zumic.models.Customer;
import com.store.zumic.models.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.contentOf;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Tag("CustomerRegistration")
@DisplayName("Customer should")
class CustomerRepositoryTest {
  
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AppUserRepository appUserRepository;

    private Customer customer;

    private Customer customer1;

    private AppUser appUser;

    @BeforeEach
    void init(){
        log.info("creating an app user ........");

        appUser = new AppUser();

        appUser.setEmail("olajune@gmail.com");
        appUser.setPassword("olajunez123");
        appUser.addRole(Role.CUSTOMER);

        appUserRepository.save(appUser);

        log.info("app user created ---->" +appUser);

//        log.info("customer registration ........");
//
//        customer = new Customer();
//        customer.setFirstName("ola");
//        customer.setLastName("june");
//        customer.setEmail(appUser.getEmail());
//        customer.setPassword(appUser.getPassword());
//
//        customerRepository.save(customer);
//
//        log.info("customer account created ---->" +customer);
    }

    @Test
    @DisplayName("be able to create an account")
    void setUp() {

        log.info("customer registration ........");

        customer = new Customer();
        customer.setFirstName("ola");
        customer.setLastName("june");
        customer.setEmail(appUser.getEmail());
        customer.setPassword(appUser.getPassword());

        customerRepository.save(customer);

        log.info("customer account created ---->" +customer);

        assertThat(customer).isNotNull();

        assertAll(
                () ->  assertEquals("ola", customer.getFirstName()),
                () ->  assertEquals("olajune@gmail.com", customer.getEmail())
        );

    }

    @Test
    @Disabled
    @DisplayName("throws customer already exist exception")
   void whenAnEmailThatAlreadyExistIsInputted_thenThrowException() throws ResponseStatusException {
        //Throws ResponseStatusException(){
        log.info("throws exception here ........");

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("olajune@gmail.com");
        appUser1.setPassword("bolaagu123");
        appUser1.addRole(Role.CUSTOMER);

        appUserRepository.save(appUser1);

        log.info("app user created ---->" +appUser1);

        customer1 = new Customer();
        customer1.setFirstName("bola");
        customer1.setLastName("agu");
        customer1.setEmail(appUser1.getEmail());
        customer1.setPassword(appUser1.getPassword());

        customerRepository.save(customer1);

        log.info("customer account created ---->" +customer1);

        Throwable error =
                assertThrows(ResponseStatusException.class, () ->   appUser1.getEmail());

      // assertEquals("a customer with email olajune@gmail.com already exist", error.getMessage());
    }

    //update
    @Test
    @DisplayName("be able to update profile")
    void updateCustomerFirstNameAndLastName(){

        log.info("updating ...");

        Customer savedCustomer = customerRepository.findByEmail("olajune@gmail.com");
        assertThat(savedCustomer).isNotNull();

        log.info("customer gotten from db---->" +savedCustomer);

        savedCustomer.setFirstName("ehis");
        savedCustomer.setLastName("jude");

        customerRepository.save(savedCustomer);

        log.info("after updating the customer profile ---->" +savedCustomer);

        assertAll(
                () ->  assertEquals("ehis", savedCustomer.getFirstName()),
                () ->  assertEquals("jude", savedCustomer.getLastName())
        );
    }

    //delete


}