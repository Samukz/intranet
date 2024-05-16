package com.web.tornese.SpringWeb.repositorio;

import com.web.tornese.SpringWeb.models.Administrador;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdministradoresRepo extends CrudRepository<Administrador, Integer> {
  @Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END  from administradores where id = :id", nativeQuery = true)
  public boolean exist(int id);

  @Query(value="select * from administradores where email = :email and senha = :senha", nativeQuery = true)
  public Administrador Login(String email, String senha);

  // Adicione este método para buscar todos os administradores
  @Query(value="select * from administradores", nativeQuery = true)
  public List<Administrador> findAllAdministradores();
}

