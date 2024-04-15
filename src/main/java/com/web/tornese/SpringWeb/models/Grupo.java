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

  @Column(name = "subgrupo", length = 250, nullable = false)
  private String subgrupo;


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

  public String getSubgrupo() {
    return subgrupo;
  }

  public void setSubgrupo(String subgrupo) {
    this.subgrupo = subgrupo;
  }
   
}