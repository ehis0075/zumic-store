package com.store.zumic.event;


import com.store.zumic.models.Customer;
import com.store.zumic.repository.AppUserRepository;
import com.store.zumic.security.authfacade.AuthenticationFacade;
import com.store.zumic.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler
@Slf4j
public class RestEvent {

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    AppUserRepository appUserRepository;

    @HandleAfterCreate
    public void handleAfterCreate(Customer customer) {

        String loggedInUserEmail = authenticationFacade.getAuthentication().getName();

        Optional<AppUser> appUser = appUserRepository.findByEmail(loggedInUserEmail);

        System.out.println("here is the user that was fetched --------> : "+ appUser.toString());
    }


}
