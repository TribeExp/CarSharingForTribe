package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepositoryDAO extends JpaRepository<CarEntity, Long> {
}