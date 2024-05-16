package com.web.tornese.SpringWeb.Servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tornese.SpringWeb.models.Catalogo;
import com.web.tornese.SpringWeb.repositorio.CatalogoRepo;

import java.util.List;

@Service
public class CatalogoService {

    @Autowired
    private CatalogoRepo catalogoRepo;

    public List<Catalogo> buscarTodosProdutos() {
        return (List<Catalogo>) catalogoRepo.findAll();
    }
}
