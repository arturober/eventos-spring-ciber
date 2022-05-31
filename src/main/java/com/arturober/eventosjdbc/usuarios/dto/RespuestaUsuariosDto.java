package com.arturober.eventosjdbc.usuarios.dto;

import java.util.List;

import com.arturober.eventosjdbc.usuarios.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RespuestaUsuariosDto {
    private List<Usuario> usuarios;
}
