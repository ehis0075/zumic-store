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
public class Customer implements Serializable {

    private static final long serialVersionUID = 23444536367737L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    private String password;

    private boolean isVerified = false;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private List<CustomerOrder> orders;

    @CreationTimestamp
    private Date dateCreated;
}
