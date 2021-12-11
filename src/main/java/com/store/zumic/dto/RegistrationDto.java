package com.store.zumic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
