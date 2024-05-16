package com.web.tornese.SpringWeb.Servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tornese.SpringWeb.models.Unidades;
import com.web.tornese.SpringWeb.repositorio.UnidadesRepository;


import java.util.List;

@Service
public class UnidadeService {

    @Autowired
    private UnidadesRepository unidadeRepository;

    public List<Unidades> listarUnidades() {
        List<Unidades> unidades = unidadeRepository.findAll();
        System.out.println("Unidades encontradas: " + unidades.size()); // Adicione esta linha para log
        return unidades;
    }
}
