package com.store.zumic.repository;

import com.store.zumic.models.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
}
