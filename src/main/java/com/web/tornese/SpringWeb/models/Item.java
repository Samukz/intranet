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

  @Column(name = "notafiscal", length = 250, nullable = true)
  private String caminhoDaNotaFiscal;

  @Column(name = "armazenagem", length = 250, nullable = true)
  private String armazenagem;

  @Column(name = "pagamento", length = 250, nullable = true)
  private String pagamento;

  @Column(name = "categoria", length = 250, nullable = true)
  private String categoria;

  @Column(name = "patrimonio", length = 250, nullable = true)
  private String patrimonio;

  @Column(name = "serie", length = 250, nullable = true)
  private String serie;

  @Column(name = "marca", length = 250, nullable = true)
  private String marca;

  @Column(name = "modelo", length = 250, nullable = true)
  private String modelo;

  @Column(name = "estado", length = 250, nullable = true)
  private String estado;

  @Lob
  @Column(name = "obs", nullable = true)
  private String obs;

  @Column(name = "pessoa", length = 250, nullable = true)
  private String pessoa;

  @Column(name = "datacadastro", length = 250, nullable = true)
  private String datacadastro;

  @Column(name = "dataescondida", length = 250, nullable = true)
  private String dataescondida;


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

public String getCaminhoDaNotaFiscal() {
    return caminhoDaNotaFiscal;
}

public void setCaminhoDaNotaFiscal(String caminhoDaNotaFiscal) {
    this.caminhoDaNotaFiscal = caminhoDaNotaFiscal;
}

public String getArmazenagem() {
    return armazenagem;
}

public void setArmazenagem(String armazenagem) {
    this.armazenagem = armazenagem;
}

public String getPagamento() {
    return pagamento;
}

public void setPagamento(String pagamento) {
    this.pagamento = pagamento;
}

public String getCategoria() {
    return categoria;
}

public void setCategoria(String categoria) {
    this.categoria = categoria;
}

public String getPatrimonio() {
    return patrimonio;
}

public void setPatrimonio(String patrimonio) {
    this.patrimonio = patrimonio;
}

public String getSerie() {
    return serie;
}

public void setSerie(String serie) {
    this.serie = serie;
}

public String getMarca() {
    return marca;
}

public void setMarca(String marca) {
    this.marca = marca;
}

public String getModelo() {
    return modelo;
}

public void setModelo(String modelo) {
    this.modelo = modelo;
}

public String getEstado() {
    return estado;
}

public void setEstado(String estado) {
    this.estado = estado;
}

public String getObs() {
    return obs;
}

public void setObs(String obs) {
    this.obs = obs;
}

public String getPessoa() {
    return pessoa;
}

public void setPessoa(String pessoa) {
    this.pessoa = pessoa;
}

public String getDatacadastro() {
    return datacadastro;
}

public void setDatacadastro(String datacadastro) {
    this.datacadastro = datacadastro;
}

public String getDataescondida() {
    return dataescondida;
}

public void setDataescondida(String dataescondida) {
    this.dataescondida = dataescondida;
}

  
}
