package com.store.zumic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStaffProfileDto {

    private String firstName;

    private String lastName;

    private String email;
}
