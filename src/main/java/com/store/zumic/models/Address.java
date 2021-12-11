package com.store.zumic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseNumber;

    private String streetName;

    private String f;

    private City city;

    @OneToOne
    private CustomerOrder customerOrder;

    @OneToOne
    private ServiceProvider serviceProvider;
}
