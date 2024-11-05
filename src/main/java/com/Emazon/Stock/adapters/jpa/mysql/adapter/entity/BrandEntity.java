package com.Emazon.Stock.adapters.jpa.mysql.adapter.entity;

import com.Emazon.Stock.adapters.utilities.BrandEntityConstants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = BrandEntityConstants.TABLE_NAME)
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BrandEntityConstants.ID_COLUMN)
    private Long id;

    @Column(name = BrandEntityConstants.NOMBRE_COLUMN, length = BrandEntityConstants.NOMBRE_MAX_LENGTH)
    private String nombre;

    @Column(name = BrandEntityConstants.DESCRIPCION_COLUMN, length = BrandEntityConstants.DESCRIPCION_MAX_LENGTH)
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