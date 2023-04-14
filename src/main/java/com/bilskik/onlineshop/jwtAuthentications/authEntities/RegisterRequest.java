package com.bilskik.onlineshop.jwtAuthentications.authEntities;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RegisterRequest {
    private String name;
    private String lastName;
    private String email;

    private String password;
}
