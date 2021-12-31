package com.store.zumic.service;


import com.store.zumic.dto.UpdateCustomerProfileDto;
import com.store.zumic.models.City;
import com.store.zumic.models.Customer;
import com.store.zumic.models.Role;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.repository.CustomerRepository;
import com.store.zumic.repository.ServiceProviderRepository;
import com.store.zumic.repository.AppUserRepository;
import com.store.zumic.security.authfacade.AuthenticationFacade;
import com.store.zumic.service.exception.CustomerAlreadyExistException;
import com.store.zumic.service.exception.CustomerDoesNotExistException;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;
import com.store.zumic.models.AppUser;
import com.store.zumic.utils.ConvertStringToEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public void create_account(Customer registrationDto) throws CustomerAlreadyExistException {

        log.info("Customer registration request ---> {}", registrationDto);

        if(customerRepository.findByEmail(registrationDto.getEmail()) == null){

            log.info("Customer is not registered ");

            AppUser appUser = new AppUser();
            appUser.setEmail(registrationDto.getEmail());
            appUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
            appUser.addRole(Role.CUSTOMER);
            appUser.setIsVerified(true);

            appUserRepository.save(appUser);

            log.info("App user saved ---> {}", appUser);

            registrationDto.setAppUser(appUser);
            registrationDto.setPassword(appUser.getPassword());

            log.info("Customer before saving --> {}", registrationDto);

            customerRepository.save(registrationDto);

            log.info("After saving customer details to db --->{}", registrationDto);

            //send verification token to email

        } else {
            throw new CustomerAlreadyExistException("a customer with email "+ registrationDto.getEmail() +" already exist!");
        }

    }

    @Override
    public void verify_account() {

    }


    @Override
    public Customer getLoggedInUser() {

        String name = authenticationFacade.getAuthentication().getName();
        log.info("Authentication facade --> {}", name);

        return customerRepository.findByEmail(name);
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
