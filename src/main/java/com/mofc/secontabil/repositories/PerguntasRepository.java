package com.mofc.secontabil.repositories;

import com.mofc.secontabil.models.Perguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntasRepository extends JpaRepository<Perguntas, Long> {
}
