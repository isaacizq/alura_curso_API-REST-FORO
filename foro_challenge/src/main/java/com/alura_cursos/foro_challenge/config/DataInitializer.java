package com.alura_cursos.foro_challenge.config;

import com.alura_cursos.foro_challenge.models.Usuario;
import com.alura_cursos.foro_challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) { // Solo insertar si la tabla está vacía
            Usuario admin = new Usuario();
            admin.setEmail("admin@foro.com");
            admin.setContrasena(passwordEncoder.encode("123456")); // Contraseña encriptada
            usuarioRepository.save(admin);
            Usuario user = new Usuario();
            user.setEmail("usuario@foro.com");
            user.setContrasena(passwordEncoder.encode("password123")); // Contraseña encriptada
            usuarioRepository.save(user);

            System.out.println("Usuarios iniciales insertados correctamente");
        }
    }
}
