package com.basakdm.excartest.dao;

import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CarRepositoryDAO extends JpaRepository<CarEntity, Long> {

    Collection<CarEntity> saveAndFlush(CarDTO carDTO);
}
