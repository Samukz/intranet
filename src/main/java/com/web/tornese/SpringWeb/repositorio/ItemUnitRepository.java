package com.web.tornese.SpringWeb.repositorio;

import com.web.tornese.SpringWeb.models.Item_Unit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemUnitRepository extends JpaRepository<Item_Unit, Integer> {
  // Métodos adicionais, se necessário
}
