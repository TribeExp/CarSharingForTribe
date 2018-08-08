package com.basakdm.excartest.dao;

import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity._CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepositoryDAO extends JpaRepository<_CarEntity, Long> {

}
