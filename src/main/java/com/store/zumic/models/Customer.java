package com.store.zumic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 23444536367737L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @NotNull
    //@Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Role> roles;

    @OneToMany(fetch = FetchType.EAGER)
    private List<CustomerOrder> listOfOrders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    private AppUser appUser;

    @CreationTimestamp
    private Date dateCreated;

    public void addOrder(CustomerOrder order){

        if (listOfOrders == null){
            this.listOfOrders = new ArrayList<>();
        }
        this.listOfOrders.add(order);
    }

    public void addRole(Role userRole){

        if (roles == null){
            this.roles = new ArrayList<>();
        }
        this.roles.add(userRole);
    }
}
