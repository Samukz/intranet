package com.web.tornese.SpringWeb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.tornese.SpringWeb.models.Formulario;

@Repository
public interface FormularioRepo extends JpaRepository<Formulario, Integer> {
    @Query("SELECT DISTINCT f FROM Formulario f JOIN FETCH f.fornecedor LEFT JOIN FETCH f.notasFiscais nf ORDER BY nf.id")
    List<Formulario> findAllWithFornecedorAndNotas();

    FormularioRepo getFornecedorById(Long id);
}



