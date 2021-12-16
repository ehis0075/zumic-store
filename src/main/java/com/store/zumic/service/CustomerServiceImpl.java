package com.store.zumic.service;


import com.store.zumic.dto.CustomerRegistrationDto;
import com.store.zumic.models.Customer;
import com.store.zumic.repository.CustomerRepository;
import com.store.zumic.service.exception.CustomerAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    //@Autowired
    //BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void create_account(CustomerRegistrationDto registrationDto) throws CustomerAlreadyExistException {

        log.info("Registration dto --->{}", registrationDto);

        Customer savedCustomer = customerRepository.findByEmail(registrationDto.getEmail());

        if(savedCustomer == null){
            Customer customer = new Customer();
            customer.setFirstName(registrationDto.getFirstName());
            customer.setLastName(registrationDto.getLastName());
            customer.setEmail(registrationDto.getEmail());
            customer.setPassword(registrationDto.getPassword());

            customerRepository.save(customer);

            log.info("After saving Registration details to db --->{}", customer);

            //send verification token to email


        } else {
            throw new CustomerAlreadyExistException("a customer with "+ registrationDto.getEmail() +" already exist");
        }

    }

    @Override
    public void verify_account() {

    }
}
