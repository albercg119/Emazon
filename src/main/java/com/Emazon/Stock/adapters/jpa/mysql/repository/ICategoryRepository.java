package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.CategoryEntity;
import com.Emazon.Stock.adapters.utilities.CategoryRepositoryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query(CategoryRepositoryConstants.FIND_BY_NOMBRE_QUERY)
    Optional<CategoryEntity> findByNombre(@Param(CategoryRepositoryConstants.PARAM_NOMBRE) String nombre);

    @Query(CategoryRepositoryConstants.EXIST_BY_NAME_EXCLUDING_ID_QUERY)
    boolean existsByNombreExcludingId(
            @Param(CategoryRepositoryConstants.PARAM_NOMBRE) String nombre,
            @Param(CategoryRepositoryConstants.PARAM_ID) Long id
    );
}