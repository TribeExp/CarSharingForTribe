package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryDAO extends JpaRepository<UserEntity, Long> {
    
}
