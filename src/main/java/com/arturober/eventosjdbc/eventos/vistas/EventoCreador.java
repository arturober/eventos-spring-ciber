package com.arturober.eventosjdbc.eventos.vistas;

import java.time.LocalDate;

import com.arturober.eventosjdbc.eventos.Evento;
import com.arturober.eventosjdbc.usuarios.Usuario;

import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class EventoCreador extends Evento {
    @PersistenceCreator
    public EventoCreador(Integer id, String titulo, String descripcion, Double precio, LocalDate fecha, String imagen, Integer idCreador) {
        super(id, titulo, descripcion, precio, fecha, imagen, idCreador);
    }

    @Embedded(onEmpty = OnEmpty.USE_EMPTY, prefix = "c_")
    private Usuario creador;

    private int asiste;
    
    @Transient
    private boolean mio;
}
