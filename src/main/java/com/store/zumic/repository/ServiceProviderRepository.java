package com.store.zumic.repository;

import com.store.zumic.models.City;
import com.store.zumic.models.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

    ServiceProvider findByName(String serviceProviderName);

    boolean existsByName(String serviceProviderName);

   // Optional<?> findByCity(City city);

    boolean existsByCity(City city); // String city

    //List<?> findByCity(City city);

    ServiceProvider findByCity(City city);

}
