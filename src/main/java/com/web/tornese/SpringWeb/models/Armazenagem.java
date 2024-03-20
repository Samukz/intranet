package com.web.tornese.SpringWeb.models;

import javax.persistence.*;



@Entity
@Table(name = "Armazenagem")
public class Armazenagem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "local", length = 250, nullable = false)
  private String local;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }


  
}