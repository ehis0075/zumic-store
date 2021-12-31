package com.store.zumic.dto;


import com.store.zumic.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private AppUser appUser;
}
