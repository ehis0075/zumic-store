package com.store.zumic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMealRequest {

    private String name;

    private Double price;

    private String preparationTime;

    private String description;

    //private String serviceProviderName;

}
