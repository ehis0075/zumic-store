package com.store.zumic.service;

import com.store.zumic.dto.UpdateServiceProviderProfileDto;
import com.store.zumic.dto.UpdateStaffProfileDto;
import com.store.zumic.models.AppUser;
import com.store.zumic.models.Customer;
import com.store.zumic.models.Role;
import com.store.zumic.models.Staff;
import com.store.zumic.repository.AppUserRepository;
import com.store.zumic.repository.CustomerRepository;
import com.store.zumic.repository.StaffRepository;
import com.store.zumic.security.authfacade.AuthenticationFacade;
import com.store.zumic.service.exception.StaffAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class StaffServiceImpl implements StaffService{

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AppUserRepository appUserRepository;



    @Override
    public void create_account(Staff registrationDto) throws StaffAlreadyExistException {

        log.info("staff registration request ---> {}", registrationDto);

        if(staffRepository.findByEmail(registrationDto.getEmail()) == null){

            log.info("Staff is not registered ");

            AppUser appUser = new AppUser();
            appUser.setEmail(registrationDto.getEmail());
            appUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
            appUser.addRole(Role.STAFF);
            appUser.setIsVerified(true);

            appUserRepository.save(appUser);

            log.info("App user saved ---> {}", appUser);

            registrationDto.setAppUser(appUser);
            registrationDto.setPassword(appUser.getPassword());

            log.info("Staff before saving --> {}", registrationDto);

            staffRepository.save(registrationDto);

            log.info("After saving staff details to db --->{}", registrationDto);


        } else {
            throw new StaffAlreadyExistException("a staff with email "+ registrationDto.getEmail() +" already exist!");
        }

    }

    @Override
    public Staff getLoggedInUser() {

        String name = authenticationFacade.getAuthentication().getName();
        log.info("Authentication facade --> {}", name);

        return staffRepository.findByEmail(name);
    }

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return customers;
    }

    @Override
    public void updateProfile(UpdateStaffProfileDto updateStaffProfileDto) {

        log.info("before saving staff info to db -->" + updateStaffProfileDto);

        Staff savedStaff = getLoggedInUser();

        //staffRepository.findByEmail(updateServiceProviderProfileDto.getEmail());

        if (savedStaff != null) {
            savedStaff.setFirstName(updateStaffProfileDto.getFirstName());
            savedStaff.setLastName(updateStaffProfileDto.getLastName());
            savedStaff.setEmail(updateStaffProfileDto.getEmail());

            staffRepository.save(savedStaff);

            log.info("after saving staff info to db -->" + savedStaff);

        } else {
            throw new StaffAlreadyExistException("staff with email " + updateStaffProfileDto.getEmail() + " already exist");
        }
    }

}
