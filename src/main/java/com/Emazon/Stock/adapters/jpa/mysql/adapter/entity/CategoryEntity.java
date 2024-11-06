package com.Emazon.Stock.adapters.jpa.mysql.adapter.entity;

import com.Emazon.Stock.adapters.utilities.CategoryEntityConstants;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = CategoryEntityConstants.TABLE_NAME)
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CategoryEntityConstants.ID_COLUMN)
    private Long id;

    @Column(name = CategoryEntityConstants.NOMBRE_COLUMN)
    private String nombre;

    @Column(name = CategoryEntityConstants.DESCRIPCION_COLUMN)
    private String descripcion;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}