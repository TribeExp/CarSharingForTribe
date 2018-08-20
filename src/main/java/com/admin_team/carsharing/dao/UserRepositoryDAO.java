package com.admin_team.carsharing.dao;

import com.admin_team.carsharing.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryDAO extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByMailEquals(String email);
    List<UserEntity> findAll(Specification<UserEntity> spec);
}
