package com.Emazon.Stock.adapters.jpa.mysql.repository;

import com.Emazon.Stock.adapters.jpa.mysql.adapter.entity.BrandEntity;
import com.Emazon.Stock.adapters.utilities.BrandRepositoryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByNombre(String nombre);

    @Query(BrandRepositoryConstants.EXISTS_BY_NOMBRE_EXCLUDING_ID)
    boolean existsByNombreExcludingId(
            @Param(BrandRepositoryConstants.PARAM_NOMBRE) String nombre,
            @Param(BrandRepositoryConstants.PARAM_ID) Long id
    );
}