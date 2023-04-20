package com.arturober.eventosjdbc.eventos;

import java.util.List;

import com.arturober.eventosjdbc.eventos.vistas.EventoCreador;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventosRepository extends CrudRepository<Evento, Integer>  {
    final static String selectSql = "SELECT e.*, c.id as c_id, c.nombre as c_nombre, c.correo as c_correo, c.avatar as c_avatar, " + 
    "EXISTS(SELECT * FROM usuario_asiste_evento WHERE usuario = :usuario AND evento = e.id) as asiste " + 
    "FROM evento e JOIN usuario c ON e.id_creador = c.id";

    @Query(selectSql)
    List<EventoCreador> getAllWithCreador(Integer usuario);

    @Query(selectSql + " WHERE e.id = :evento")
    EventoCreador getByIdWithCreador(Integer usuario, Integer evento);

    @Query("INSERT INTO usuario_asiste_evento VALUES (:usuario,:evento)")
    void asistirEvento(Integer usuario, Integer evento);

    @Query("DELETE FROM usuario_asiste_evento WHERE evento = :evento AND usuario = :usuario")
    void borrarAsistirEvento(Integer usuario, Integer evento);
}
