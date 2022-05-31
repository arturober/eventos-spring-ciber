package com.arturober.eventosjdbc.eventos.vistas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.arturober.eventosjdbc.usuarios.Usuario;

import org.springframework.jdbc.core.RowMapper;

public class EventoCreadorRowMapper implements RowMapper<EventoCreador> {

    @Override
    public EventoCreador mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventoCreador e = new EventoCreador(rs.getInt("id"), rs.getString("titulo"), rs.getString("descripcion"), 
                rs.getDouble("precio"), LocalDate.parse(rs.getString("fecha")), rs.getString("imagen"), rs.getInt("id_creador"));
        e.setCreador(new Usuario(rs.getInt("c_id"), rs.getString("c_nombre"), rs.getString("c_correo"), rs.getString("c_avatar"), null));
        e.setAsiste(rs.getInt("asiste"));
        return e;
    }
    
}
