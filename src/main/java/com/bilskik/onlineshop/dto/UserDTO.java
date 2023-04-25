package com.bilskik.onlineshop.dto;


import com.bilskik.onlineshop.entities.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private int id;
    private String name;
    private String surename;
    private String email;
    private String gender;
    @JsonProperty("dateOfBirth")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Date of birth cannot be null!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private Role role;
}
