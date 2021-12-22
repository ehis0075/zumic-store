package com.store.zumic.repository;

import com.store.zumic.models.City;
import com.store.zumic.models.Meal;
import com.store.zumic.models.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Tag("AddingAMealToCatalog")
@DisplayName("Service Provider should be")
class MealRepositoryTest {

    @Autowired
    ServiceProviderRepository serviceProviderRepository;


    private ServiceProvider serviceProvider;

    @BeforeEach
    @DisplayName("be able to create an account")
    void canCreateAnAccount() {
        log.info("registration ..............");
        serviceProvider = new ServiceProvider();
        serviceProvider.setName("mama k");
        serviceProvider.setEmail("jjocha@gmail.com");
        serviceProvider.setAddress("no 3 saki street, yaba, lagos");
        serviceProvider.setCity(City.LAGOS);
        serviceProvider.setPhoneNumber("09087665434");

        assertThat(serviceProvider).isNotNull();

        assertAll(
                () ->  assertEquals("mama k", serviceProvider.getName()),
                () ->  assertEquals("jjocha@gmail.com", serviceProvider.getEmail())
        );
    }


    @Test
    @DisplayName("able to add a meal to its catalog")
    @Transactional
    public void serviceProviderCanAddMeal(){
        log.info("adding meal ..............");
        ServiceProvider saved = serviceProviderRepository.save(serviceProvider);

        Meal meal = new Meal();
        meal.setName("Gelato");
        meal.setPrice(23.00);
        meal.setPreparationTime("1 hr 20 mins");
        meal.setDescription("bread and tea");

        saved.addMeal(meal);

        assertThat(meal).isNotNull();
        assertNotNull(serviceProvider.getListOfMeals());

//        assertEquals(1, serviceProvider.getListOfMeals().size());
//        assertEquals(23.00, meal.getPrice());
//        assertEquals("bread and tea", meal.getDescription());

        assertAll(
                () ->  assertEquals(1, serviceProvider.getListOfMeals().size()),
                () ->  assertEquals(23.00, meal.getPrice())
        );
    }

    //two meal cannot have same name
    @Test
    @DisplayName("throws an error ")
    public void whenServiceProviderInputAMealName_throwExceptionWhenNameAlreadyExist(){

        log.info("adding meal with the same name ..............");
        ServiceProvider saved = serviceProviderRepository.save(serviceProvider);

        Meal meal = new Meal();
        meal.setName("Gelato");
        meal.setPrice(54.00);
        meal.setPreparationTime("2 hr 20 mins");
        meal.setDescription("rice and spag");

        saved.addMeal(meal);

        assertThrows(Error.class, () ->  meal.setName("Gelato"));


    }

    //delete a meal

    //edit a meal

}