package com.bilskik.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.intellij.lang.annotations.RegExp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
public class User implements UserDetails {

    @NotNull(message = "username cannot be null!")
    public String name;
    @NotNull(message = "surename cannot be null!")
    public String surename;
    @Column(unique = true)
    @Email(message = "invalid email address!")
    public String email;
    @NotNull(message = "gender cannot be null!")
    public String gender;
    @JsonProperty("dateOfBirth")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Date of birth cannot be null!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate dateOfBirth;
    public String password;
    @Enumerated(EnumType.STRING)
    public Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
