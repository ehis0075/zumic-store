package com.store.zumic.service;

import com.store.zumic.dto.OrderRequest;
import com.store.zumic.models.CustomerOrder;
import com.store.zumic.models.Meal;
import com.store.zumic.repository.MealRepository;
import com.store.zumic.repository.OrderRepository;
import com.store.zumic.repository.ServiceProviderRepository;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;
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

    @Override
    public void placeOrder(OrderRequest orderRequest) {

        log.info("Order request  --> {}, {}", orderRequest);

        Meal meal = mealRepository.findByName(orderRequest.getMeal().getName());

        if(serviceProviderRepository.existsByCity(orderRequest.getServiceProvider().getCity())) {
            CustomerOrder order = new CustomerOrder();

            List<Meal> meals = new ArrayList<>();
            meals.add(meal);

            order.setMeals(meals);
            order.setServiceProvider(orderRequest.getServiceProvider());

            orderRepository.save(order);

            log.info("After saving to database --> {}", order);
        } else {
            throw new ServiceProviderNotFoundException("service provider " +orderRequest.getServiceProvider()
                    + " does not exist in " + orderRequest.getServiceProvider().getCity());
        }

    }
}
