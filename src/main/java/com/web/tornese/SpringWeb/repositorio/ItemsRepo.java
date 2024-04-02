package com.web.tornese.SpringWeb.repositorio;

import com.web.tornese.SpringWeb.models.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ItemsRepo extends CrudRepository<Item, Integer> {
  @Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END  from administradores where id = :id", nativeQuery = true)
  public boolean exist(int id);

  @Query(value="select * from administradores where email = :email and senha = :senha", nativeQuery = true)
  public Item Login(String email, String senha);

  @Query("SELECT SUM(valor) FROM Item")
  Double sumValor();

  @Query("SELECT local, SUM(valor) FROM Item GROUP BY local")
  List<Object[]> sumValorPorLocal();

  @Query("SELECT MAX(valor) FROM Item")
  Double sumMaiorValor();

  @Query("SELECT COUNT(i) FROM Item i")
  Long countItems();

  @Query("SELECT COUNT(i) FROM Item i WHERE i.estado <> 'Obsoleto '")
  Long countItemsNaoObsoletos();

  @Query("SELECT i.dataescondida, SUM(i.valor) FROM Item i GROUP BY i.dataescondida ORDER BY i.dataescondida ASC")
  List<Object[]> sumValorByData();

  @Query(value = "select count(1) > 0 from items where patrimonio = :numeroPatrimonio", nativeQuery = true)
  int existsByNumeroPatrimonio(@Param("numeroPatrimonio") String numeroPatrimonio);

  
  @Query(value = "SELECT * FROM items " +
      "WHERE (:nome IS NULL OR nome LIKE CONCAT('%', :nome, '%')) " +
      "AND (:local IS NULL OR local LIKE CONCAT('%', :local, '%')) " +
      "AND (:categoria IS NULL OR categoria LIKE CONCAT('%', :categoria, '%')) " +
      "AND (:estado IS NULL OR estado LIKE CONCAT('%', :estado, '%'))", nativeQuery = true)
  List<Item> findItemsByFilters(@Param("nome") String nome,
      @Param("local") String local,
      @Param("categoria") String categoria,
      @Param("estado") String estado);

      Optional<Item> findByPatrimonio(String patrimonio);

  //@Query(value="select * from administradores where nome like %:nome% or email like %:email% ", nativeQuery = true)
	//public ArrayList<Administrador> findAllByNomeEmail(@Param("nome") String nome, @Param("email") String email);
}
