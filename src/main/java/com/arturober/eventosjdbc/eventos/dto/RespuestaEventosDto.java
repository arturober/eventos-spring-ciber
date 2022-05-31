package com.arturober.eventosjdbc.eventos.dto;

import java.util.List;

import com.arturober.eventosjdbc.eventos.vistas.EventoCreador;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RespuestaEventosDto {
    private List<EventoCreador> eventos;
}
