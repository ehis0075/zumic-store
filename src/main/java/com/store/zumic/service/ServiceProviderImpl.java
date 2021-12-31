package com.store.zumic.service;


import com.store.zumic.dto.AddMealRequest;
import com.store.zumic.models.Meal;
import com.store.zumic.models.Role;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.repository.MealRepository;
import com.store.zumic.repository.ServiceProviderRepository;
import com.store.zumic.repository.AppUserRepository;
import com.store.zumic.security.authfacade.AuthenticationFacade;
import com.store.zumic.service.exception.MealAlreadyExistException;
import com.store.zumic.service.exception.ServiceProviderAlreadyExistException;
import com.store.zumic.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ServiceProviderImpl implements ServiceProviderService {

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    MealRepository mealRepository;

    @Override
    public void create(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException {

        log.info("service provider registration request --> {}", serviceProvider);

        if(serviceProviderRepository.findByEmailOrName(serviceProvider.getEmail(), serviceProvider.getName()) == null){

            log.info("Service Provider is not registered ");

            AppUser appUser = new AppUser();
            appUser.setEmail(serviceProvider.getEmail());
            appUser.setPassword(passwordEncoder.encode(serviceProvider.getPassword()));
            appUser.addRole(Role.SERVICE_PROVIDER);
            appUser.setIsVerified(true);

            appUserRepository.save(appUser);

            log.info("App user saved ---> {}", appUser);

            serviceProvider.setAppUser(appUser);
            serviceProvider.setPassword(appUser.getPassword());

            log.info("service provider before saving --> {}", serviceProvider);

            serviceProviderRepository.save(serviceProvider);

            log.info("After saving service provider to database ---> {}", serviceProvider);

        } else {
            throw new ServiceProviderAlreadyExistException("service provider with the name " +serviceProvider.getName()+ " or email "+ serviceProvider.getEmail() +" already exist");
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

            //map the new created new to a service provider in the db
            serviceProvider.addMeal(meal1);

           // serviceProviderRepository.save()
            log.info("list of meals from the service provider  ---->{}", serviceProvider.getListOfMeals());

        } else {
            throw new MealAlreadyExistException("a meal with the name " +addMealRequest.getName() + " already exist");
        }


    }


}
