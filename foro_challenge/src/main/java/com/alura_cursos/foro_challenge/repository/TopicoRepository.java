package com.alura_cursos.foro_challenge.repository;

import com.alura_cursos.foro_challenge.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico,Long> {

}
