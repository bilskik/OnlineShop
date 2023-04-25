package com.bilskik.onlineshop.http;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RegisterRequest {
    private String name;
    private String surename;
    private String email;
    @JsonProperty("dateOfBirth")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Date of birth cannot be null!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private String gender;
    private String password;
}
