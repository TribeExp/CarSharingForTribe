package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.TESTEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TESTRepository extends JpaRepository<TESTEntity, Long> {
}
