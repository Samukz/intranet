package com.web.tornese.SpringWeb.models;

import javax.persistence.*;


@Entity
@Table(name = "itemcatalogo")
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "codigo", length = 255, nullable = true)
    private String codigo;

    @Column(name = "sigma", length = 255, nullable = true)
    private String sigma;

    @Column(name = "item", length = 255, nullable = true)
    private String item;

    @Column(name = "unmedida", length = 255, nullable = true)
    private String unmedida;

    @Column(name = "siglaunmedida", length = 255, nullable = true)
    private String siglaunmedida;

    @Column(name = "grupo", length = 255, nullable = true)
    private String grupo;

    @Column(name = "subgrupo", length = 255, nullable = true)
    private String subgrupo;

    @Column(name = "tipo", length = 255, nullable = true)
    private String tipo;

    @Column(name = "especificacao", length = 255, nullable = true)
    private String especificacao;

    @Column(name = "marca", length = 255, nullable = true)
    private String marca;

    @Column(name = "modelo", length = 255, nullable = true)
    private String modelo;

    @Column(name = "fabricante", length = 255, nullable = true)
    private String fabricante;

    @Column(name = "datacadastro", length = 255, nullable = true)
    private String datacadastro;

    @Column(name = "pessoa", length = 255, nullable = true)
    private String pessoa;

    @Column(name = "caminho", length = 250, nullable = true)
    private String caminhoDaImagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSigma() {
        return sigma;
    }

    public void setSigma(String sigma) {
        this.sigma = sigma;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUnmedida() {
        return unmedida;
    }

    public void setUnmedida(String unmedida) {
        this.unmedida = unmedida;
    }

    public String getSiglaunmedida() {
        return siglaunmedida;
    }

    public void setSiglaunmedida(String siglaunmedida) {
        this.siglaunmedida = siglaunmedida;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
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

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public String getCaminhoDaImagem() {
        return caminhoDaImagem;
    }

    public void setCaminhoDaImagem(String caminhoDaImagem) {
        this.caminhoDaImagem = caminhoDaImagem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNome'");
    }

 
    
    
}
