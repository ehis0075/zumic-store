package com.store.zumic.controller;



import com.store.zumic.dto.OrderRequest;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.OrderService;
import com.store.zumic.service.exception.ServiceProviderAlreadyExistException;
import com.store.zumic.service.exception.ServiceProviderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("api/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place-order")
    ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest){

        try {
            orderService.placeOrder(orderRequest);
        } catch (ServiceProviderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok().body("successful order ");

    }

//    @PostMapping("/pay")
//    ResponseEntity<?> pay(@RequestBody ){
//
//        try {
//
//        } catch (ServiceProviderNotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
//        }
//        return ResponseEntity.ok().build();
//    }
}
