package com.store.zumic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

//    @OneToOne
//    private Address customerAddress;

    private City city;

    @OneToMany(mappedBy = "serviceProviders")
    private List<Meal> listOfMeals;

}
