package com.web.tornese.SpringWeb.repositorio;

import com.web.tornese.SpringWeb.models.Grupo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GruposRepository extends JpaRepository<Grupo, Integer> {
  // Métodos adicionais, se necessário

  List<Grupo> findByGrupo(String grupo);
  
}
