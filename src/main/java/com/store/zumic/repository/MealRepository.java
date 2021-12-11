package com.store.zumic.repository;

import com.store.zumic.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {

    Meal findByName(String name);

}
