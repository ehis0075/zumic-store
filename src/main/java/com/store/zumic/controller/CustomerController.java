package com.store.zumic.controller;



import com.store.zumic.dto.CustomerRegistrationDto;
import com.store.zumic.dto.UpdateCustomerProfileDto;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.CustomerService;
import com.store.zumic.service.exception.ResourceNotFoundException;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/registration")
    ResponseEntity<?> registration(@RequestBody CustomerRegistrationDto registrationDto ){

        try {
            customerService.create_account(registrationDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("registration successful");
    }

    @PostMapping("/update-profile")
    ResponseEntity<?> updateProfile(@RequestBody UpdateCustomerProfileDto updateCustomerProfileDto) {

        try {
            customerService.updateProfile(updateCustomerProfileDto);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok().body("customer profile successfully updated");
    }

    @GetMapping("/find-serviceProviders/{city}")
    ResponseEntity<?> getAllServiceProvidersInACity(@PathVariable @NotNull String city)  {

        List<ServiceProvider>  serviceProviderList;
        try {
            serviceProviderList = customerService.findAllServiceProvidersByCity(city);
        } catch (ResourceNotFoundException e)  {
            return ResponseEntity.badRequest().body("No service provider found in ");
        }
//        }catch (Exception e) {
//                return ResponseEntity.badRequest().body(e.getLocalizedMessage());
//            }
        return ResponseEntity.ok().body(serviceProviderList);
        //return ResponseEntity.ok().body("done.");
    }

//    @PostMapping("/task/{liftId}")
//    public ResponseEntity<?> addTask(@RequestBody @NotNull TaskDto taskDto, @PathVariable Long liftId){
//
//        log.info("adding task request --> {}", taskDto);
//        try {
//            advertiserService.addTask(taskDto, liftId);
//        }catch (ResourceNotFoundException ex){
//            return ResponseEntity.badRequest().body("lift subscription with id"+ liftId +"does not exist!");
//        }
//        return ResponseEntity.ok().body("new task successfully added.");
//    }



}
