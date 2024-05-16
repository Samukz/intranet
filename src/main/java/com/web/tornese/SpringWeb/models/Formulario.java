package com.web.tornese.SpringWeb.models;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "tb_formulario")
public class Formulario {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "1_nomefornecedor", length = 250, nullable = true)
  private String nomefornecedor;
  
  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    private Fornecedores fornecedor;

    @OneToMany(mappedBy = "formulario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NotaFiscal> notasFiscais;

  @Column(name = "1_1_nomefornecedor", length = 250, nullable = true)
  private String nomeoutros;

  @Column(name = "outroCnpjFornecedor", length = 250, nullable = true)
  private String outroCnpjFornecedor;

  @Column(name = "idnotafiscal", length = 250, nullable = true)
  private String idnotafiscal;

  @Column(name = "3_veiculo", length = 250, nullable = true)
  private String veiculo;

  @Column(name = "3_veiculo_obs", length = 250, nullable = true)
  private String veiculoobs;

  @Column(name = "4_entrega_realizada", length = 250, nullable = true)
  private String entregarealizada;

  @Column(name = "5_material_entregue_sim", length = 250, nullable = true)
  private String materialentregue;

  @Column(name = "5_material_entregue_parcial", length = 250, nullable = true)
  private String materialparcial;

  @Column(name = "5_parcial_of_pendente", length = 250, nullable = true)
  private String parcialofpendente;

  @Column(name = "5_divergencia_item", length = 250, nullable = true)
  private String divergenciaitem;
  
  @Column(name = "5_embalagem_danificada", length = 250, nullable = true)
  private String embalagemdanificada;

  @Column(name = "5_embalagem_danificada_info", length = 250, nullable = true)
  private String embalagemdanificadainfo;
  
  @Column(name = "5_termolabeis", length = 250, nullable = true)
  private String temolabel;

  @Column(name = "5_termolabeis_sim_nao", length = 250, nullable = true)
  private String termolabelsimnao;

  @Column(name = "6_lote_validade_fabricante", length = 250, nullable = true)
  private String lotevalidade;

  @Column(name = "7_documentos_impressos_OF", length = 250, nullable = true)
  private String documentoimpressosof;

  @Column(name = "7_documentos_impressos_MP", length = 250, nullable = true)
  private String documentoimpressosmp;

  @Column(name = "7_documentos_impressos_EMAIL", length = 250, nullable = true)
  private String documentoimpressosemail;

  @Column(name = "7_documentos_impressos_CT", length = 250, nullable = true)
  private String documentoimpressosct;

  @Column(name = "7_documentos_impressos_CC", length = 250, nullable = true)
  private String documentoimpressoscc;
  
  @Column(name = "7_documentos_impressos_LV", length = 250, nullable = true)
  private String documentoimpressoslv;

  @Column(name = "7_documentos_impressos_SNF", length = 250, nullable = true)
  private String documentoimpressossnf;

  @Column(name = "7_documentos_impressos_OUTROS", length = 250, nullable = true)
  private String documentoimpressosoutros;

  @Column(name = "8_dados_rodape_OR", length = 250, nullable = true)
  private String dadosrodapeOr;

  @Column(name = "8_dados_rodape_OF", length = 250, nullable = true)
  private String dadosrodapeof;

  @Column(name = "8_dados_rodape_NumeroAquisicao", length = 250, nullable = true)
  private String dadosrodapeaquisicao;

  @Column(name = "8_dados_rodape_numero_cc", length = 250, nullable = true)
  private String dadosrodapenumcc;

  @Column(name = "8_dados_rodape_nome_cc", length = 250, nullable = true)
  private String dadosrodapenomecc;

  @Column(name = "8_dados_rodape_numero_pedido", length = 250, nullable = true)
  private String dadosrodapenumpedido;

  @Column(name = "8_dados_rodape_nenhuma", length = 250, nullable = true)
  private String dadosrodapenenhuma;

  @Column(name = "8_dados_rodape_outros", length = 250, nullable = true)
  private String dadosrodapeoutros;

  @Column(name = "9_fornecedor_agendamento", length = 250, nullable = true)
  private String agendamento;

  @Column(name = "10_data_hora_cumpriu", length = 250, nullable = true)
  private String datahoracumprida;

  @Column(name = "11_carga_organizada_nf", length = 250, nullable = true)
  private String organizadanf;

  @Column(name = "11_carga_organizada_nf_obs", length = 250, nullable = true)
  private String organizadanfobs;

  @Column(name = "12_carga_embalada_lacrada", length = 250, nullable = true)
  private String cargaembalada;

  @Column(name = "12_carga_embalada_lacrada_obs", length = 250, nullable = true)
  private String cargaembaladaobs;
  
  @Column(name = "13_ajudante", length = 250, nullable = true)
  private String ajudante;

  @Column(name = "14_entrega_aceita", length = 250, nullable = true)
  private String entregaaceita;

  @Column(name = "15_obs_final", length = 250, nullable = true)
  private String obsfinal;

  @Column(name = "usuario", length = 250, nullable = true)
  private String usuariocadastrado;

  @Column(name = "pdflink", length = 250, nullable = true)
  private String pdflink;

  @Column(name = "Quantidade", length = 20, nullable = true)
  private String Quantidade;

  @Column(name = "Prazo", length = 20, nullable = true)
  private String Prazo;

  @Column(name = "Qualidade", length = 20, nullable = true)
  private String Qualidade;
  
  @Column(name = "Documental", length = 20, nullable = true)
  private String Documental;

  @Column(name = "Total", length = 20, nullable = true)
  private String Total;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNomefornecedor() {
    return nomefornecedor;
  }

  public void setNomefornecedor(String nomefornecedor) {
    this.nomefornecedor = nomefornecedor;
  }

  public String getNomeoutros() {
    return nomeoutros;
  }

  public void setNomeoutros(String nomeoutros) {
    this.nomeoutros = nomeoutros;
  }

  public String getIdnotafiscal() {
    return idnotafiscal;
  }

  public void setIdnotafiscal(String idnotafiscal) {
    this.idnotafiscal = idnotafiscal;
  }

  public String getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(String veiculo) {
    this.veiculo = veiculo;
  }

  public String getVeiculoobs() {
    return veiculoobs;
  }

  public void setVeiculoobs(String veiculoobs) {
    this.veiculoobs = veiculoobs;
  }

  public String getEntregarealizada() {
    return entregarealizada;
  }

  public void setEntregarealizada(String entregarealizada) {
    this.entregarealizada = entregarealizada;
  }

  public String getMaterialentregue() {
    return materialentregue;
  }

  public void setMaterialentregue(String materialentregue) {
    this.materialentregue = materialentregue;
  }

  public String getMaterialparcial() {
    return materialparcial;
  }

  public void setMaterialparcial(String materialparcial) {
    this.materialparcial = materialparcial;
  }

  public String getParcialofpendente() {
    return parcialofpendente;
  }

  public void setParcialofpendente(String parcialofpendente) {
    this.parcialofpendente = parcialofpendente;
  }

  public String getDivergenciaitem() {
    return divergenciaitem;
  }

  public void setDivergenciaitem(String divergenciaitem) {
    this.divergenciaitem = divergenciaitem;
  }

  public String getEmbalagemdanificada() {
    return embalagemdanificada;
  }

  public void setEmbalagemdanificada(String embalagemdanificada) {
    this.embalagemdanificada = embalagemdanificada;
  }

  public String getTemolabel() {
    return temolabel;
  }

  public void setTemolabel(String temolabel) {
    this.temolabel = temolabel;
  }

  public String getTermolabelsimnao() {
    return termolabelsimnao;
  }

  public void setTermolabelsimnao(String termolabelsimnao) {
    this.termolabelsimnao = termolabelsimnao;
  }

  public String getLotevalidade() {
    return lotevalidade;
  }

  public void setLotevalidade(String lotevalidade) {
    this.lotevalidade = lotevalidade;
  }

  public String getDocumentoimpressosof() {
    return documentoimpressosof;
  }

  public void setDocumentoimpressosof(String documentoimpressosof) {
    this.documentoimpressosof = documentoimpressosof;
  }

  public String getDocumentoimpressosmp() {
    return documentoimpressosmp;
  }

  public void setDocumentoimpressosmp(String documentoimpressosmp) {
    this.documentoimpressosmp = documentoimpressosmp;
  }

  public String getDocumentoimpressosemail() {
    return documentoimpressosemail;
  }

  public void setDocumentoimpressosemail(String documentoimpressosemail) {
    this.documentoimpressosemail = documentoimpressosemail;
  }

  public String getDocumentoimpressosct() {
    return documentoimpressosct;
  }

  public void setDocumentoimpressosct(String documentoimpressosct) {
    this.documentoimpressosct = documentoimpressosct;
  }

  public String getDocumentoimpressoscc() {
    return documentoimpressoscc;
  }

  public void setDocumentoimpressoscc(String documentoimpressoscc) {
    this.documentoimpressoscc = documentoimpressoscc;
  }

  public String getDocumentoimpressoslv() {
    return documentoimpressoslv;
  }

  public void setDocumentoimpressoslv(String documentoimpressoslv) {
    this.documentoimpressoslv = documentoimpressoslv;
  }

  public String getDocumentoimpressossnf() {
    return documentoimpressossnf;
  }

  public void setDocumentoimpressossnf(String documentoimpressossnf) {
    this.documentoimpressossnf = documentoimpressossnf;
  }

  public String getDocumentoimpressosoutros() {
    return documentoimpressosoutros;
  }

  public void setDocumentoimpressosoutros(String documentoimpressosoutros) {
    this.documentoimpressosoutros = documentoimpressosoutros;
  }


  public String getDadosrodapeof() {
    return dadosrodapeof;
  }

  public void setDadosrodapeof(String dadosrodapeof) {
    this.dadosrodapeof = dadosrodapeof;
  }

  public String getDadosrodapeaquisicao() {
    return dadosrodapeaquisicao;
  }

  public void setDadosrodapeaquisicao(String dadosrodapeaquisicao) {
    this.dadosrodapeaquisicao = dadosrodapeaquisicao;
  }

  public String getDadosrodapenumcc() {
    return dadosrodapenumcc;
  }

  public void setDadosrodapenumcc(String dadosrodapenumcc) {
    this.dadosrodapenumcc = dadosrodapenumcc;
  }

  public String getDadosrodapenomecc() {
    return dadosrodapenomecc;
  }

  public void setDadosrodapenomecc(String dadosrodapenomecc) {
    this.dadosrodapenomecc = dadosrodapenomecc;
  }

  public String getDadosrodapenumpedido() {
    return dadosrodapenumpedido;
  }

  public void setDadosrodapenumpedido(String dadosrodapenumpedido) {
    this.dadosrodapenumpedido = dadosrodapenumpedido;
  }

  public String getDadosrodapenenhuma() {
    return dadosrodapenenhuma;
  }

  public void setDadosrodapenenhuma(String dadosrodapenenhuma) {
    this.dadosrodapenenhuma = dadosrodapenenhuma;
  }

  public String getDadosrodapeoutros() {
    return dadosrodapeoutros;
  }

  public void setDadosrodapeoutros(String dadosrodapeoutros) {
    this.dadosrodapeoutros = dadosrodapeoutros;
  }

  public String getAgendamento() {
    return agendamento;
  }

  public void setAgendamento(String agendamento) {
    this.agendamento = agendamento;
  }

  public String getDatahoracumprida() {
    return datahoracumprida;
  }

  public void setDatahoracumprida(String datahoracumprida) {
    this.datahoracumprida = datahoracumprida;
  }

  public String getOrganizadanf() {
    return organizadanf;
  }

  public void setOrganizadanf(String organizadanf) {
    this.organizadanf = organizadanf;
  }

  public String getOrganizadanfobs() {
    return organizadanfobs;
  }

  public void setOrganizadanfobs(String organizadanfobs) {
    this.organizadanfobs = organizadanfobs;
  }

  public String getCargaembalada() {
    return cargaembalada;
  }

  public void setCargaembalada(String cargaembalada) {
    this.cargaembalada = cargaembalada;
  }

  public String getCargaembaladaobs() {
    return cargaembaladaobs;
  }

  public void setCargaembaladaobs(String cargaembaladaobs) {
    this.cargaembaladaobs = cargaembaladaobs;
  }

  public String getAjudante() {
    return ajudante;
  }

  public void setAjudante(String ajudante) {
    this.ajudante = ajudante;
  }

  public String getEntregaaceita() {
    return entregaaceita;
  }

  public void setEntregaaceita(String entregaaceita) {
    this.entregaaceita = entregaaceita;
  }

  public String getObsfinal() {
    return obsfinal;
  }

  public void setObsfinal(String obsfinal) {
    this.obsfinal = obsfinal;
  }

public String getDadosrodapeOr() {
    return dadosrodapeOr;
}

public void setDadosrodapeOr(String dadosrodapeOr) {
    this.dadosrodapeOr = dadosrodapeOr;
}

public String getEmbalagemdanificadainfo() {
    return embalagemdanificadainfo;
}

public void setEmbalagemdanificadainfo(String embalagemdanificadainfo) {
    this.embalagemdanificadainfo = embalagemdanificadainfo;
}

public Fornecedores getFornecedor() {
  return fornecedor;
}

public void setFornecedor(Fornecedores fornecedor) {
  this.fornecedor = fornecedor;
}

public List<NotaFiscal> getNotasFiscais() {
  return notasFiscais;
}

public void setNotasFiscais(List<NotaFiscal> notasFiscais) {
  this.notasFiscais = notasFiscais;
}

public String getUsuariocadastrado() {
  return usuariocadastrado;
}

public void setUsuariocadastrado(String usuariocadastrado) {
  this.usuariocadastrado = usuariocadastrado;
}

public String getPdflink() {
  return pdflink;
}

public void setPdflink(String pdflink) {
  this.pdflink = pdflink;
}

public String getQuantidade() {
  return Quantidade;
}

public void setQuantidade(String quantidade) {
  Quantidade = quantidade;
}

public String getPrazo() {
  return Prazo;
}

public void setPrazo(String prazo) {
  Prazo = prazo;
}

public String getQualidade() {
  return Qualidade;
}

public void setQualidade(String qualidade) {
  Qualidade = qualidade;
}

public String getDocumental() {
  return Documental;
}

public void setDocumental(String documental) {
  Documental = documental;
}

public String getTotal() {
  return Total;
}

public void setTotal(String total) {
  Total = total;
}

public String getOutroCnpjFornecedor() {
  return outroCnpjFornecedor;
}

public void setOutroCnpjFornecedor(String outroCnpjFornecedor) {
  this.outroCnpjFornecedor = outroCnpjFornecedor;
}





}