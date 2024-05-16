package com.web.tornese.SpringWeb.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_notafiscal")
public class NotaFiscal {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;  

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "formulario_id")
  private Formulario formulario;
  
  @Column(name = "id_notafiscal", length = 250, nullable = true)
  private int idnotafiscal;

  @Column(name = "n_notafiscal", length = 250, nullable = true)
  private String numeroNotaFiscal;

  @Column(name = "n_volume", length = 250, nullable = true)
  private String numeroVolumes;

  @Column(name = "centro_de_custo", length = 250, nullable = true)
  private String centroDeCusto;

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public int getIdnotafiscal() {
    return idnotafiscal;
}

public void setIdnotafiscal(int idnotafiscal) {
    this.idnotafiscal = idnotafiscal;
}

public String getNumeroNotaFiscal() {
    return numeroNotaFiscal;
}

public void setNumeroNotaFiscal(String numeroNotaFiscal) {
    this.numeroNotaFiscal = numeroNotaFiscal;
}

public String getNumeroVolumes() {
    return numeroVolumes;
}

public void setNumeroVolumes(String numeroVolumes) {
    this.numeroVolumes = numeroVolumes;
}

public String getCentroDeCusto() {
    return centroDeCusto;
}

public void setCentroDeCusto(String centroDeCusto) {
    this.centroDeCusto = centroDeCusto;
}

public Formulario getFormulario() {
    return formulario;
}

public void setFormulario(Formulario formulario) {
    this.formulario = formulario;
}
 
  
}



