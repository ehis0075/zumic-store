package com.store.zumic.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 47890999367737L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private City city;

    @NotNull
    @Column(unique = true)
    private String phoneNumber;

    //remove
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "meal_id", referencedColumnName = "id")
//    private List<Meal> meals;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serviceProvider_id", referencedColumnName = "id")
    private ServiceProvider serviceProvider;

//    @OneToOne(cascade = CascadeType.ALL)
//    //@JoinColumn(name = "customer_id", referencedColumnName = "id")
//    private Customer customer;

    @JsonFormat(pattern="dd/MM/yyyy")
    @CreationTimestamp
    private Date dateCreated;

//    public void addMeal(Meal meal){
//        if(listOfMeals == null){
//            listOfMeals = new ArrayList<>();
//        }
//        listOfMeals.add(meal);
//    }

    //@OneToOne
    //private CustomerTransaction transaction;

}
