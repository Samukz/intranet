package com.web.tornese.SpringWeb.repositorio;

import com.web.tornese.SpringWeb.models.Unidades;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadesRepository extends JpaRepository<Unidades, Integer> {
  // Métodos adicionais, se necessário
}
