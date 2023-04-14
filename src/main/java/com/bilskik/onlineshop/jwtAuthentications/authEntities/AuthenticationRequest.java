package com.bilskik.onlineshop.jwtAuthentications.authEntities;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AuthenticationRequest {
    private String email;
    String password;
}
