package com.web.tornese.SpringWeb.models;

import javax.persistence.*;


@Entity
@Table(name = "fornecedores")
public class Fornecedores {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "razao", length = 250, nullable = false)
  private String razao;

  @Column(name = "cnpj", length = 250, nullable = false)
  private String cnpj;

  @Column(name = "dado1", length = 250, nullable = false)
  private String dado1;

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getRazao() {
    return razao;
}

public void setRazao(String razao) {
    this.razao = razao;
}

public String getCnpj() {
    return cnpj;
}

public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
}

public String getDado1() {
    return dado1;
}

public void setDado1(String dado1) {
    this.dado1 = dado1;
}

  

}