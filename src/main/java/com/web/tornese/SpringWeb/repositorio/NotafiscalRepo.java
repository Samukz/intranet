package com.web.tornese.SpringWeb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tornese.SpringWeb.models.NotaFiscal;

public interface NotafiscalRepo extends JpaRepository<NotaFiscal, Integer> {
    
}