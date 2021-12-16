package com.store.zumic.service;




import com.store.zumic.dto.AddMealRequest;
import com.store.zumic.models.City;
import com.store.zumic.models.Meal;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.exception.ServiceProviderAlreadyExistException;

import java.util.List;

public interface ServiceProviderService {

    void create(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException;

    List<?> getAllServiceProviders(City city);

    void addFood(AddMealRequest addMealRequest);
}

