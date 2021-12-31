package com.store.zumic.service;

import com.store.zumic.dto.OrderRequest;
import com.store.zumic.models.*;
import com.store.zumic.repository.CustomerRepository;
import com.store.zumic.repository.MealRepository;
import com.store.zumic.repository.OrderRepository;
import com.store.zumic.repository.ServiceProviderRepository;
import com.store.zumic.security.authfacade.AuthenticationFacade;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;
import com.store.zumic.utils.ConvertStringToEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    MealRepository mealRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public void placeOrder(OrderRequest orderRequest) {

        log.info("Order request--> {} "+ orderRequest);

        Meal meal = mealRepository.findByName(orderRequest.getMeal());

        String loggedInUserEmail = authenticationFacade.getAuthentication().getName();

        Customer savedCustomer = customerRepository.findByEmail(loggedInUserEmail);

        ServiceProvider serviceProvider = serviceProviderRepository.findByName(orderRequest.getServiceProviderName());

        City enumCity = ConvertStringToEnum.convertStringToEnum(orderRequest.getCity());

        log.info("placing order in "+ enumCity+ " city"+ " from "+ serviceProvider);

        if(serviceProvider != null) {

            CustomerOrder order = new CustomerOrder();
            order.setPhoneNumber(orderRequest.getPhoneNumber());
            order.setServiceProvider(serviceProvider);
            order.setAddress(orderRequest.getAddress());
            order.setCity(enumCity);

            List<Meal> meals = new ArrayList<>();
            meals.add(meal);

            orderRepository.save(order);

            savedCustomer.addOrder(order);

            log.info("After saving to database --> {}", order);
        } else {
            throw new ServiceProviderNotFoundException("service provider " +orderRequest.getServiceProviderName()
                    + " does not exist in " + serviceProvider.getCity());
        }

    }
}
