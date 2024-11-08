package com.emzon.user.adapters.jpa.mysql.repository;

import com.emzon.user.adapters.jpa.mysql.adapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByDocumentId(String documentId);
}
