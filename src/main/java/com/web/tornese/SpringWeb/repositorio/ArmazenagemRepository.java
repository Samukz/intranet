package com.web.tornese.SpringWeb.repositorio;

import com.web.tornese.SpringWeb.models.Armazenagem; // Importe a classe Armazenagem corretamente

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmazenagemRepository extends JpaRepository<Armazenagem, Integer> {
  // Métodos adicionais, se necessário
}
