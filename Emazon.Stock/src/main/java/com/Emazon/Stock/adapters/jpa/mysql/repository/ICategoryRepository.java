package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByNombre(String nombre);

    // Nuevo método de consulta que excluye un ID específico
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CategoryEntity c WHERE LOWER(c.nombre) = LOWER(:nombre) AND c.id != :id")
    boolean existsByNombreExcludingId(@Param("nombre") String nombre, @Param("id") Long id);
}