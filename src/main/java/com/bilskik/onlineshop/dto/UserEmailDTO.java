package com.bilskik.onlineshop.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Component
public class UserEmailDTO {
    private String email;
}
