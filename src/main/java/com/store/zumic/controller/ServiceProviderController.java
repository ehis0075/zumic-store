package com.store.zumic.controller;


import com.store.zumic.dto.AddMealRequest;
import com.store.zumic.models.City;
import com.store.zumic.models.Meal;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.ServiceProviderService;
//import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("api/serviceProvider")
@Slf4j
//@RequiredArgsConstructor
public class ServiceProviderController {

    //private final ServiceProviderService serviceProviderService;
    @Autowired
    ServiceProviderService serviceProviderService;


    @PostMapping("/create_account")
    public ResponseEntity<?> registration(@RequestBody ServiceProvider serviceProvider){

        try {
            serviceProviderService.create(serviceProvider);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("registration successful");
    }

    @PostMapping("/add_meal")
    ResponseEntity<?> addMeal(@RequestBody AddMealRequest addMealRequest){

        try {
            serviceProviderService.addFood(addMealRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body("meal was successfully added");
    }

    @PostMapping("/find_ServiceProvider/{city}")
    ResponseEntity<?> find(@PathVariable String city){

        try {
            serviceProviderService.getAServiceProvider(city);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().build();
    }



}
