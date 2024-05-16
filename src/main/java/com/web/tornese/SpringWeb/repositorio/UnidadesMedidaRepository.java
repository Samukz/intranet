package com.web.tornese.SpringWeb.repositorio;

import com.web.tornese.SpringWeb.models.Medidas; // Importe a classe Armazenagem corretamente

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadesMedidaRepository extends JpaRepository<Medidas, Integer> {
  // Métodos adicionais, se necessário
}
