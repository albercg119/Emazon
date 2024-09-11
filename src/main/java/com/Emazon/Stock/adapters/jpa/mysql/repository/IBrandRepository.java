package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByNombre(String nombre);


    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BrandEntity b WHERE LOWER(b.nombre) = LOWER(:nombre) AND b.id != :id")
    boolean existsByNombreExcludingId(@Param("nombre") String nombre, @Param("id") Long id);
}