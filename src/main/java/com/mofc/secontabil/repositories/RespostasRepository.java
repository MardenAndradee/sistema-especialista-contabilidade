package com.mofc.secontabil.repositories;

import com.mofc.secontabil.models.Respostas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostasRepository extends JpaRepository<Respostas, Long> {
}
