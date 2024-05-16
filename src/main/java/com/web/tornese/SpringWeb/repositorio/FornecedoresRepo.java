package com.web.tornese.SpringWeb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tornese.SpringWeb.models.Fornecedores;

public interface FornecedoresRepo extends JpaRepository<Fornecedores, Integer> {
    // Métodos adicionais, se necessário


    
  }