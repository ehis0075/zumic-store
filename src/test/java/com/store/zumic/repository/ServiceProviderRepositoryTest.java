package com.store.zumic.repository;

import com.store.zumic.models.City;
import com.store.zumic.models.Meal;
import com.store.zumic.models.ServiceProvider;
import com.store.zumic.service.ServiceProviderService;
import com.store.zumic.utils.ConvertStringToEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ServiceProviderRepositoryTest {

    @Autowired
    ServiceProviderRepository serviceProviderRepository;


    private ServiceProvider serviceProvider;

//    @BeforeAll
//    static void testClassSetUp(){
//        log.info("Before All.........");
//    }

    @BeforeEach
    void init(){
        log.info("Before each..............");
        serviceProvider = new ServiceProvider();
        serviceProvider.setName("mama k");
        serviceProvider.setAddress("no 3 saki street, yaba, lagos");
        serviceProvider.setCity(City.LAGOS);
        serviceProvider.setPhoneNumber("09087665434");
    }

//    @AfterEach
//    void testTearDown(){
//        log.info("Tear down.................");
//    }

//    @AfterAll
//    void testTearDown1(){
//        log.info("After all.................");
//    }


    @Test
    @Transactional
    public void serviceProviderCanAddMeal(){
        log.info("adding meal ..............");
        ServiceProvider saved = serviceProviderRepository.save(serviceProvider);

        Meal meal = new Meal();
        meal.setName("breakfast");
        meal.setPrice(23.00);
        meal.setPreparationTime("1 hr 20 mins");
        meal.setDescription("bread and tea");

        saved.addMeal(meal);

        assertThat(meal).isNotNull();
        assertNotNull(serviceProvider.getListOfMeals());

//        assertEquals(1, serviceProvider.getListOfMeals().size());
//        assertEquals(23.00, meal.getPrice());

        assertAll(
                () ->  assertEquals(1, serviceProvider.getListOfMeals().size()),
                () ->  assertEquals(23.00, meal.getPrice())
        );
    }


  
}