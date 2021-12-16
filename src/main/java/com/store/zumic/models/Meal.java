package com.store.zumic.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private Double price;

    private String preparationTime;

    private String description;

    //add category: breakfast, lunch, dinner

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceProvider", referencedColumnName = "name")
    private ServiceProvider serviceProvider;

    @OneToMany
    private List<CustomerOrder> customerOrders;

}
