package com.arturober.eventosjdbc.eventos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.arturober.eventosjdbc.eventos.dto.RespuestaEventoDto;
import com.arturober.eventosjdbc.eventos.dto.RespuestaEventosDto;
import com.arturober.eventosjdbc.eventos.vistas.EventoCreador;
import com.arturober.eventosjdbc.usuarios.Usuario;
import com.arturober.eventosjdbc.usuarios.UsuariosService;
import com.arturober.eventosjdbc.usuarios.dto.RespuestaUsuariosDto;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/eventos")
public class EventosController {
    private final @NonNull EventosService evService;
    private final @NonNull UsuariosService usuService;

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String nombre) {
        String urlServidor = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer idAuth = Integer.parseInt(auth.getCredentials().toString());
        try {
            Iterable<EventoCreador> eventos = nombre == null ? evService.findAll(idAuth) : evService.findByNombre(idAuth, nombre);
            List<EventoCreador> eventosResp = StreamSupport.stream(eventos.spliterator(), false)
                    .map(e -> {
                        e.setImagen(urlServidor + "/" + e.getImagen());
                        e.getCreador().setAvatar(urlServidor + "/" + e.getCreador().getAvatar());
                        e.setMio(e.getCreador().getId() == idAuth);
                        return e;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(new RespuestaEventosDto(eventosResp));
        } catch (Exception e) {
            Map<String, Object> resp = new HashMap<>();
            resp.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
            
                
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaEventoDto> findById(@PathVariable int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer idAuth = Integer.parseInt(auth.getCredentials().toString());
        EventoCreador e = evService.findById(idAuth, id);
        
        if (e == null) {
            return ResponseEntity.notFound().build();
        }

        String urlServidor = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        e.setImagen(urlServidor + "/" + e.getImagen());
        e.getCreador().setAvatar(urlServidor + "/" + e.getCreador().getAvatar());
        e.setMio(e.getCreador().getId() == idAuth);
        return ResponseEntity.ok().body(new RespuestaEventoDto(e));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public RespuestaEventoDto insert(@RequestBody Evento evento) {
        Evento e = evService.insert(evento);
        e.setImagen(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + e.getImagen());
        return new RespuestaEventoDto(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(@RequestBody Evento evento, @PathVariable int id) {
        Evento e = evService.update(evento, id);
        if (e == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        try {
            evService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/asistir")
    public RespuestaUsuariosDto usuariosAsisten(@PathVariable(name = "id") int idEvento) {
        List<Usuario> usuarios = usuService.getAsistenEvento(idEvento);
        usuarios.forEach(u -> u.setAvatar(
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + u.getAvatar()));
        return new RespuestaUsuariosDto(usuarios);
    }

    @PostMapping("/{id}/asistir")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void asistir(@PathVariable(name = "id") int idEvento) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer idAuth = Integer.parseInt(auth.getCredentials().toString());
        System.out.println(idAuth + " - " + idEvento);
        evService.asistirEvento(idAuth, idEvento);
    }

    @DeleteMapping("/{id}/asistir")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void borrarAsistir(@PathVariable(name = "id") int idEvento) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer idAuth = Integer.parseInt(auth.getCredentials().toString());
        evService.borrarAsistirEvento(idAuth, idEvento);
    }
}
