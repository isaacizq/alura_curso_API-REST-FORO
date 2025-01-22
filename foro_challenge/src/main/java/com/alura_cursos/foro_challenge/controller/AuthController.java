package com.alura_cursos.foro_challenge.controller;
import com.alura_cursos.foro_challenge.models.Usuario;
import com.alura_cursos.foro_challenge.repository.UsuarioRepository;
import com.alura_cursos.foro_challenge.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Map<String, String>> autenticar(@RequestBody Map<String, String> credenciales) {
        String email = credenciales.get("email");
        String contrasena = credenciales.get("contrasena");

        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar la contraseña (puedes implementar encriptación si lo necesitas)
        if (!usuario.getContrasena().equals(contrasena)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Generar y devolver el token
        String token = authService.generarToken(email);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
