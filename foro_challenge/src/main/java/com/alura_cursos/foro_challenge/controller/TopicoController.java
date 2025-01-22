package com.alura_cursos.foro_challenge.controller;
import com.alura_cursos.foro_challenge.models.Topico;
import com.alura_cursos.foro_challenge.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {


    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoService.listarTopicos();
    }

    @PostMapping
    public ResponseEntity<Topico> crearTopico(@RequestBody Topico topico) {
        return new ResponseEntity<>(topicoService.crearTopico(topico), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody Topico topico) {
        return ResponseEntity.ok(topicoService.actualizarTopico(id, topico));
    }
}
