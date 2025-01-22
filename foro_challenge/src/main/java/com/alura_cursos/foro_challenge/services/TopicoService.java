package com.alura_cursos.foro_challenge.services;
import com.alura_cursos.foro_challenge.models.Topico;
import com.alura_cursos.foro_challenge.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    public Topico crearTopico(Topico topico) {
        return topicoRepository.save(topico);
    }

    public Optional<Topico> obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }

    public Topico actualizarTopico(Long id, Topico topicoActualizado) {
        return topicoRepository.findById(id).map(topico -> {
            topico.setTitulo(topicoActualizado.getTitulo());
            topico.setMensaje(topicoActualizado.getMensaje());
            return topicoRepository.save(topico);
        }).orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
    }
}
