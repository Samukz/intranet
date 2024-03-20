package com.web.tornese.SpringWeb.models;

import javax.persistence.*;



@Entity
@Table(name = "Grupo")
public class Grupo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "grupo", length = 250, nullable = false)
  private String grupo;

  @Column(name = "descricao", length = 250, nullable = false)
  private String descricao;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getGrupo() {
    return grupo;
  }

  public void setGrupo(String grupo) {
    this.grupo = grupo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

 
  
}