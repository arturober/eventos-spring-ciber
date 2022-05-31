package com.arturober.eventosjdbc.usuarios;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private final @NonNull UsuariosService usuService;

    @GetMapping
    public Iterable<Usuario> findAll() {
        usuService.findAll().forEach(u -> u.setAvatar(
            ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + u.getAvatar()));
        return usuService.findAll();
    }

    @GetMapping("/yo")
    public ResponseEntity<Usuario> getUsuarioLogueado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario u = usuService.findById(Integer.parseInt(auth.getCredentials().toString()));
        if(u == null) {
            return ResponseEntity.notFound().build();
        } else {
            u.setAvatar(
                    ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + u.getAvatar());
            return ResponseEntity.ok().body(u);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable int id) {
        Usuario u = usuService.findById(id);
        if(u == null) {
            return ResponseEntity.notFound().build();
        } else {
            u.setAvatar(
                    ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + u.getAvatar());
            return ResponseEntity.ok().body(u);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable int id) {
        Usuario e = usuService.update(usuario, id);
        if(e == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(e);
        }
    }
}