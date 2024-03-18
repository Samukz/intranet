package com.web.tornese.SpringWeb.models;

import javax.persistence.*;


@Entity
@Table(name = "items")
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "nome", length = 100, nullable = false)
  private String nome;

  @Column(name = "valor", length = 180, nullable = false)
  private String valor;

  @Column(name = "data", nullable = false)
  private String data;

  @Column(name = "local", length = 250, nullable = false)
  private String local;

  @Column(name = "caminho", length = 250, nullable = true)
  private String caminhoDaImagem;
  @Column(name = "tipoimagem", length = 250, nullable = true)
  private String tipoDaImagem;

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getData() {
    return data;
}

public void setData(String data) {
    this.data = data;
}

public String getLocal() {
    return local;
}

public void setLocal(String local) {
    this.local = local;
}

public String getValor() {
    return valor;
}

public void setValor(String valor) {
    this.valor = valor;
}

public String getCaminhoDaImagem() {
    return caminhoDaImagem;
}

public void setCaminhoDaImagem(String caminhoDaImagem) {
    this.caminhoDaImagem = caminhoDaImagem;
}

public String getTipoDaImagem() {
    return tipoDaImagem;
}

public void setTipoDaImagem(String tipoDaImagem) {
    this.tipoDaImagem = tipoDaImagem;
}



  
}
