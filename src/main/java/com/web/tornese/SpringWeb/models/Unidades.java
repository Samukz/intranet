package com.web.tornese.SpringWeb.models;

import javax.persistence.*;

@Entity
@Table(name = "unidades")
public class Unidades {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "centrodecusto", length = 250, nullable = false)
  private String centrodecusto;

  @Column(name = "nomeunidade", length = 250, nullable = false)
  private String nomeunidade;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCentrodecusto() {
    return centrodecusto;
  }

  public void setCentrodecusto(String centrodecusto) {
    this.centrodecusto = centrodecusto;
  }

  public String getNomeunidade() {
    return nomeunidade;
  }

  public void setNomeunidade(String nomeunidade) {
    this.nomeunidade = nomeunidade;
  }


  
}