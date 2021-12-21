package com.store.zumic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ServiceProvider implements Serializable {

    private static final long serialVersionUID = 43536367737L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private City city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@LazyCollection(LazyCollectionOption.EXTRA)
    //@JoinColumn(referencedColumnName = "id")
    private List<Meal> listOfMeals;

    @Transactional(readOnly = true)
    public void addMeal(Meal meal){
        if(listOfMeals == null){
            listOfMeals = new ArrayList<>();
        }
        listOfMeals.add(meal);
    }

    @CreationTimestamp
    private Date dateCreated;

}
