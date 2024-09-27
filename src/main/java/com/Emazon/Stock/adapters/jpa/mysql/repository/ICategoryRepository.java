package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByNombre(String nombre);

}