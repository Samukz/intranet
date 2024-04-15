package com.web.tornese.SpringWeb.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.web.tornese.SpringWeb.models.Catalogo;

public interface CatalogoRepo extends CrudRepository<Catalogo, Integer> {
    
    
}
