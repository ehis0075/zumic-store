package com.store.zumic.service;


import com.store.zumic.dto.AddMealRequest;
import com.store.zumic.models.City;
import com.store.zumic.models.Meal;
import com.store.zumic.models.Role;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.repository.MealRepository;
import com.store.zumic.repository.ServiceProviderRepository;
import com.store.zumic.service.exception.MealAlreadyExistException;
import com.store.zumic.service.exception.ServiceProviderAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ServiceProviderImpl implements ServiceProviderService {

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    MealRepository mealRepository;

    @Override
    public void create(ServiceProvider serviceProvider) throws IllegalArgumentException {

        log.info("service provider registration request --> {}", serviceProvider);

        ServiceProvider serviceProvider2 = serviceProviderRepository.findByName(serviceProvider.getName());

        serviceProvider2 = serviceProviderRepository.findByEmail(serviceProvider.getEmail());

        if(serviceProvider2 == null & serviceProvider2 == null){
            ServiceProvider serviceProvider1 = new ServiceProvider();
            serviceProvider1.setName(serviceProvider.getName());
            serviceProvider1.setEmail(serviceProvider.getEmail());
            serviceProvider1.setAddress(serviceProvider.getAddress());
            serviceProvider1.setPhoneNumber(serviceProvider.getPhoneNumber());
            serviceProvider1.setCity(serviceProvider.getCity());
            serviceProvider1.setRole(Role.valueOf("SERVICE_PROVIDER"));

            serviceProviderRepository.save(serviceProvider1);

            log.info("After saving to database ---> {}", serviceProvider1);

        } else {
            throw new IllegalArgumentException("service provider with the name " +serviceProvider2.getName()+ " or email "+ serviceProvider2.getEmail() +" already exist");
        }

    }

    @Override
    public void addFood(AddMealRequest addMealRequest) {

        log.info("add food request --> {}, {}", addMealRequest);

        ServiceProvider serviceProvider = serviceProviderRepository.findByName(addMealRequest.getServiceProviderName());
        log.info("service provider from db ----->{}", serviceProvider);

        if(serviceProvider != null){
            Meal meal1 = new Meal();
            meal1.setDescription(addMealRequest.getDescription());
            meal1.setName(addMealRequest.getName());
            meal1.setPreparationTime(addMealRequest.getPreparationTime());
            meal1.setPrice(addMealRequest.getPrice());

            mealRepository.save(meal1);

            log.info("saved meal --> {}", meal1);

            log.info("<--------- adding meal to list of meals -------->");
            log.info("list of meals from the service provider  ---->{}", serviceProvider.getListOfMeals());

            //map the new created new to a service provider in the db
            serviceProvider.addMeal(meal1);

        } else {
            throw new MealAlreadyExistException("a meal with the name " +addMealRequest.getName() + " already exist");
        }


    }


}
