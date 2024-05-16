package com.web.tornese.SpringWeb.models;

import javax.persistence.*;

@Entity
@Table(name = "item_unit")
public class Item_Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Mudança de 'int' para 'Long'

    @Column(name = "item_id", nullable = false)
    private Long item_id; // Mudança de 'String' para 'Long'

    @Column(name = "unidade_id", nullable = false)
    private Long unidade_id; // Mudança de 'String' para 'Long'

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Long getUnidade_id() {
        return unidade_id;
    }

    public void setUnidade_id(Long unidade_id) {
        this.unidade_id = unidade_id;
    }
}