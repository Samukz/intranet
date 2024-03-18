package com.web.tornese.SpringWeb.repositorio;

import com.web.tornese.SpringWeb.models.Item;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepo extends CrudRepository<Item, Integer> {
  @Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END  from administradores where id = :id", nativeQuery = true)
  public boolean exist(int id);

  @Query(value="select * from administradores where email = :email and senha = :senha", nativeQuery = true)
  public Item Login(String email, String senha);
  
  //@Query(value="select * from administradores where nome like %:nome% or email like %:email% ", nativeQuery = true)
	//public ArrayList<Administrador> findAllByNomeEmail(@Param("nome") String nome, @Param("email") String email);
}
