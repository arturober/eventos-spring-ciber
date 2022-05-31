package com.arturober.eventosjdbc.eventos;

import java.util.List;

import com.arturober.eventosjdbc.eventos.vistas.EventoCreador;
import com.arturober.eventosjdbc.eventos.vistas.EventoCreadorRowMapper;
import com.arturober.eventosjdbc.utils.ImageUtils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventosService {
    private final @NonNull EventosRepository eventRepo;
    private final @NonNull ImageUtils imgUtils;
    private final @NonNull JdbcTemplate jdbcTemplate;

    public Iterable<EventoCreador> findAll(int idAuth) {
        return eventRepo.getAllWithCreador(idAuth);
    }

    public Iterable<EventoCreador> findByNombre(int idAuth, String nombre) {
        List<EventoCreador> eventos = jdbcTemplate.query("SELECT e.*, c.id as c_id, c.nombre as c_nombre, c.correo as c_correo, c.avatar as c_avatar, " + 
        "EXISTS(SELECT * FROM usuario_asiste_evento WHERE usuario = " + idAuth + " AND evento = e.id) as asiste " + 
        "FROM evento e JOIN usuario c ON e.id_creador = c.id WHERE e.titulo LIKE '%" + nombre + "%'", new EventoCreadorRowMapper());
        // '  UNION SELECT 1, u.nombre, u.correo, 4, '2021-12-12', u.password, 7, 8, 9, 10, 11, 12 FROM usuario u; -- 
        return eventos;
    }

    public EventoCreador findById(int idAuth, int id) {
        return eventRepo.getByIdWithCreador(idAuth, id);
    }

    public Evento insert(Evento e) {
        String ruta = imgUtils.saveImageBase64("eventos", e.getImagen());
        e.setImagen(ruta);
        return eventRepo.save(e);
    }

    public Evento update(Evento e, int id) {
        if(eventRepo.existsById(id)) {
            e.setId(id);
            return eventRepo.save(e);
        }
        return null; // No existe
    }

    public void delete(int id) {
        eventRepo.deleteById(id);
    }

    public void asistirEvento(Integer usuario, Integer evento) {
        eventRepo.asistirEvento(usuario, evento);
    }

    public void borrarAsistirEvento(Integer usuario, Integer evento) {
        eventRepo.borrarAsistirEvento(usuario, evento);
    }
}
