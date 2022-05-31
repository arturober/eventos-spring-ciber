package com.arturober.eventosjdbc.eventos;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Evento {
    @Id
    private Integer id;
    private String titulo;
    private String descripcion;
    private Double precio;
    private LocalDate fecha;
    private String imagen;
    private Integer idCreador;
}
