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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meal implements Serializable {

    private static final long serialVersionUID = 4353636567787L;

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

//    @ManyToOne(optional = false)
//    @JoinColumn(name = "serviceProviderId", nullable = false, updatable = false)
//    private ServiceProvider serviceProvider;

    @OneToMany
    private List<CustomerOrder> customerOrders;

    @CreationTimestamp
    private Date dateCreated;

    public Meal(String name, double price, String preparationTime, String description) {
        this.name = name;
        this.price = price;
        this.preparationTime = preparationTime;
        this.description = description;
    }
}
