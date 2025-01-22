package com.alura_cursos.foro_challenge.config;

import com.alura_cursos.foro_challenge.repository.UsuarioRepository;
import com.alura_cursos.foro_challenge.services.AuthService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtService.validarToken(token)) {
                String email = jwtService.obtenerEmailDelToken(token);

                // Verificar si el usuario está registrado
                if (usuarioRepository.findByEmail(email).isPresent()) {
                    try {
                        filterChain.doFilter(request, response);
                    } catch (java.io.IOException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            }
        }

        // Si no pasa la validación, retorna 401 (No autorizado)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
