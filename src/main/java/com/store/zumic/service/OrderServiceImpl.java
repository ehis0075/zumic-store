package com.store.zumic.service;

import com.store.zumic.dto.OrderRequest;
import com.store.zumic.models.CustomerOrder;
import com.store.zumic.models.Meal;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.repository.MealRepository;
import com.store.zumic.repository.OrderRepository;
import com.store.zumic.repository.ServiceProviderRepository;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    MealRepository mealRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequest orderRequest) {

        log.info("Order request  --> {}", orderRequest);

        Meal meal = mealRepository.findByName(orderRequest.getMeal());

        //Optional<ServiceProvider> serviceProvider = serviceProviderRepository.findById(orderRequest.getServiceProviderId());

        ServiceProvider serviceProvider1 = serviceProviderRepository.findByName(orderRequest.getServiceProviderName());

        //check if the service provider is in that city

        if(serviceProviderRepository.existsByCity(serviceProvider1.getCity())) {
            CustomerOrder order = new CustomerOrder();

            order.setServiceProvider(serviceProvider1);
            order.setAddress(orderRequest.getAddress());
            order.setCity(orderRequest.getCity());

            List<Meal> meals = new ArrayList<>();
            meals.add(meal);

            order.setMeals(meals);

            orderRepository.save(order);

            log.info("After saving to database --> {}", order);
        } else {
            throw new ServiceProviderNotFoundException("service provider " +orderRequest.getServiceProviderName()
                    + " does not exist in " + serviceProvider1.getCity());
        }

    }
}
