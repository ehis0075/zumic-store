package com.store.zumic.dto;



import com.store.zumic.models.Meal;
import com.store.zumic.models.ServiceProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @OneToOne
    private Meal meal;

    @OneToOne
    private ServiceProvider serviceProvider;

}
