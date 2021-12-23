package com.store.zumic.service;


import com.store.zumic.dto.CustomerRegistrationDto;
import com.store.zumic.dto.UpdateCustomerProfileDto;
import com.store.zumic.models.City;
import com.store.zumic.models.Customer;
import com.store.zumic.models.Role;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.repository.CustomerRepository;
import com.store.zumic.repository.ServiceProviderRepository;
import com.store.zumic.service.exception.CustomerAlreadyExistException;
import com.store.zumic.service.exception.CustomerDoesNotExistException;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;
import com.store.zumic.utils.ConvertStringToEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    //@Autowired
    //BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Override
    public void create_account(CustomerRegistrationDto registrationDto) throws CustomerAlreadyExistException {

        log.info("Customer registration request ---> {}", registrationDto);

        Customer savedCustomer = customerRepository.findByEmail(registrationDto.getEmail());

        if(savedCustomer == null){
            Customer customer = new Customer();
            customer.setFirstName(registrationDto.getFirstName());
            customer.setLastName(registrationDto.getLastName());
            customer.setEmail(registrationDto.getEmail());
            customer.setPassword(registrationDto.getPassword());
            customer.setRole(Role.valueOf("CUSTOMER"));

            customerRepository.save(customer);

            log.info("After saving customer details to db --->{}", customer);

            //send verification token to email


        } else {
            throw new CustomerAlreadyExistException("a customer with email "+ registrationDto.getEmail() +" already exist!");
        }

    }

    @Override
    public void verify_account() {

    }

    @Override
    public void updateProfile(UpdateCustomerProfileDto updateCustomerProfileDto) {

        log.info("update customer profile request --------> {}", updateCustomerProfileDto);

        Customer savedCustomer = customerRepository.findByEmail(updateCustomerProfileDto.getEmail());

        if(savedCustomer != null){
            savedCustomer.setFirstName(updateCustomerProfileDto.getFirstName());
            savedCustomer.setLastName(updateCustomerProfileDto.getLastName());

            customerRepository.save(savedCustomer);
            log.info("after updating customer profile ------> {}", savedCustomer);
        } else {

            //authentication exception
            throw new CustomerDoesNotExistException("customer with email "+ updateCustomerProfileDto.getEmail() +" does not exist");
        }

    }

    @Override
    public List<ServiceProvider> findAllServiceProvidersByCity(String city) throws ServiceProviderNotFoundException {

        log.info("getting all service providers in "+ city);
        City newCity = ConvertStringToEnum.convertStringToEnum(city); //issue

        //City newCity = City.getByName(city);

        //City newCity = CityType.getCityType(city); //issue

        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAllByCity(newCity);
        log.info("list of service providers in "+ city + " :"+ serviceProviderList);

//        if(serviceProviderList == null){
//            throw new ServiceProviderNotFoundException("No service providers found in  "+ city);
//        }
        if(serviceProviderList != null){
            return serviceProviderList;
            //throw new ResourceNotFoundException("No service provider found in "+ city);
        } else {
          throw new ServiceProviderNotFoundException("No service provider found in "+ city);
        }
//        return serviceProviderList;
    }
}
