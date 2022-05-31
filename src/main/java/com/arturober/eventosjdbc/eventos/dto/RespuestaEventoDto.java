package com.arturober.eventosjdbc.eventos.dto;

import com.arturober.eventosjdbc.eventos.Evento;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RespuestaEventoDto {
    private Evento evento;
}
