package com.bilskik.onlineshop.http;


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
