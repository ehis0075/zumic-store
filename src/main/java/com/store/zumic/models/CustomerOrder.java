package com.store.zumic.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    private Address address;

    @NotNull
    @Column(unique = true)
    private String phoneNumber;

    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_id", referencedColumnName = "id")
    private List<Meal> meals;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serviceProvider_id", referencedColumnName = "id")
    private ServiceProvider serviceProvider;

    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @CreationTimestamp
    private Date dateCreated;

    //@OneToOne
    //private CustomerTransaction transaction;

}
