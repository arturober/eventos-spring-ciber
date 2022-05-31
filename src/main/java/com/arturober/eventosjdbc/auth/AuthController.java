package com.arturober.eventosjdbc.auth;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.arturober.eventosjdbc.auth.dto.LoginDto;
import com.arturober.eventosjdbc.auth.dto.TokenResponseDto;
import com.arturober.eventosjdbc.usuarios.Usuario;
import com.arturober.eventosjdbc.usuarios.UsuariosService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final @NonNull UsuariosService usuService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginDto login) throws NoSuchAlgorithmException {
        Usuario u = usuService.login(login.getCorreo(), login.getPassword());
        if(u != null) {
            String token = getToken(u);
            TokenResponseDto resp = new TokenResponseDto(token);
            return ResponseEntity.ok().body(resp);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/registro")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registro(@RequestBody Usuario usuario) throws NoSuchAlgorithmException {
        usuService.insert(usuario);
    }

    @GetMapping("/validate")
    public void validateToken() {
        // No hace nada, solo sirve para que el token se valide
    }

    private String getToken(Usuario user) {	
        Algorithm algorithm = Algorithm.HMAC256("token101");
        String token = JWT.create()
            .withIssuer("arturober")
            .withClaim("id", user.getId())
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis() + (24*60*60*1000))) // Caduca en un día
            .sign(algorithm);
		
		return token;
	}
}
