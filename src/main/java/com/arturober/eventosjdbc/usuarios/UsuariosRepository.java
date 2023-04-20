package com.arturober.eventosjdbc.usuarios;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuariosRepository extends CrudRepository<Usuario, Integer> {
    Usuario findByCorreoAndPassword(String correo, String password); 

    @Query("SELECT u.* FROM usuario u JOIN usuario_asiste_evento uae ON uae.usuario = u.id WHERE uae.evento = :evento")
    List<Usuario> getAsistenEvento(Integer evento);
}
