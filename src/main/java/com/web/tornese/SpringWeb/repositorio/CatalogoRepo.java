package com.web.tornese.SpringWeb.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.tornese.SpringWeb.models.Catalogo;

public interface CatalogoRepo extends CrudRepository<Catalogo, Integer> {
    // O método findAll() já está disponível por padrão
}
