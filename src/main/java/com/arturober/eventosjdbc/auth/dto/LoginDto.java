package com.arturober.eventosjdbc.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LoginDto {
    private String correo;
    private String password;
}
