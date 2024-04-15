package com.web.tornese.SpringWeb.models;

import javax.persistence.*;

@Entity
@Table(name = "item_unit")
public class Item_Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "item_id", length = 100, nullable = false)
    private String item_id;

    @Column(name = "unidade_id", length = 100, nullable = false)
    private String unidade_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getUnidade_id() {
        return unidade_id;
    }

    public void setUnidade_id(String unidade_id) {
        this.unidade_id = unidade_id;
    }

    
    
}
