package com.store.zumic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateServiceProviderProfileDto {

    private String name;

    private String email;

    private String address;

    private String phoneNumber;

    private String city;
}
