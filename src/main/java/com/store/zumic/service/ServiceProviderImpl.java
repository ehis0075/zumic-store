package com.store.zumic.service;


import com.store.zumic.models.City;
import com.store.zumic.models.Meal;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.repository.MealRepository;
import com.store.zumic.repository.ServiceProviderRepository;
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
    public void create(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException {

        log.info("Before saving customer info --> {}", serviceProvider);

        ServiceProvider serviceProvider2 = serviceProviderRepository.findByName(serviceProvider.getName());

        if(serviceProvider2 == null){
            ServiceProvider serviceProvider1 = new ServiceProvider();
            serviceProvider1.setName(serviceProvider.getName());
            serviceProvider1.setAddress(serviceProvider.getAddress());
            serviceProvider1.setPhoneNumber(serviceProvider.getPhoneNumber());
            serviceProvider1.setCity(serviceProvider.getCity());

            serviceProviderRepository.save(serviceProvider1);

            log.info("After saving customer saved ---> {}", serviceProvider1);

        } else {
            throw new ServiceProviderAlreadyExistException("service provider with that name already exist");
        }

    }

    @Override
    public List<?> getAllServiceProviders(City city) {
        return serviceProviderRepository.findByCity(city);
    }

    @Override
    public void addFood(Meal meal, String nameOfRestaurant) {

        log.info("add food request --> {}, {}", meal, nameOfRestaurant);

        ServiceProvider serviceProvider = serviceProviderRepository.findByName(nameOfRestaurant);

        if(serviceProvider != null){
            Meal meal1 = new Meal();
            meal1.setDescription(meal.getDescription());
            meal1.setDescription(meal.getDescription());
            meal1.setPrice(meal.getPrice());

            mealRepository.save(meal1);

            log.info("saved meal --> {}, {}", meal1);

            List<Meal> meals = new ArrayList<>();
            meals.add(meal1);

            //map the new created new to a service provider in the db
            serviceProvider.setListOfMeals(meals);
            log.info("list of meals --> {}, {}", meals);

        }

    }


}
