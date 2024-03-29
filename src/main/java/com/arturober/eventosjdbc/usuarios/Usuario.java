package com.arturober.eventosjdbc.usuarios;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Usuario {
    @Id
    private Integer id;
    private String nombre;
    private String correo;
    private String avatar;
    private String password;
}
