package com.store.zumic.repository;


import com.store.zumic.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findByEmail(String email);

}