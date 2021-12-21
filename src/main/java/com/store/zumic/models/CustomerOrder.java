package com.store.zumic.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 4879999367737L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private City city;

    @NotNull
    @Column(unique = true)
    private String phoneNumber;

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
