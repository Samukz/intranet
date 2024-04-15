package com.web.tornese.SpringWeb.models;

import javax.persistence.*;

@Entity
@Table(name = "unidadeMedida")
public class Medidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "unidademedida", length = 255, nullable = false)
    private String unidademedida;

    @Column(name = "siglaun", length = 255, nullable = false)
    private String siglaun;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnidademedida() {
        return unidademedida;
    }

    public void setUnidademedida(String unidademedida) {
        this.unidademedida = unidademedida;
    }

    public String getSiglaun() {
        return siglaun;
    }

    public void setSiglaun(String siglaun) {
        this.siglaun = siglaun;
    }
    
}
