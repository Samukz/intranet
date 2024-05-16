package com.web.tornese.SpringWeb.models;

import java.util.Objects;

import javax.persistence.*;



@Entity
@Table(name = "Grupo")
public class Grupo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "ngrupo", length = 250, nullable = false)
  private String ngrupo;

  @Column(name = "grupo", length = 250, nullable = false)
  private String grupo;

  @Column(name = "nsubgrupo", length = 250, nullable = false)
  private String nsubgrupo;

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

  public String getNgrupo() {
    return ngrupo;
  }

  public void setNgrupo(String ngrupo) {
    this.ngrupo = ngrupo;
  }

  public String getNsubgrupo() {
    return nsubgrupo;
  }

  public void setNsubgrupo(String nsubgrupo) {
    this.nsubgrupo = nsubgrupo;
  }
   

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Grupo grupo = (Grupo) o;
    return Objects.equals(grupo, grupo.grupo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(grupo);
  }
}