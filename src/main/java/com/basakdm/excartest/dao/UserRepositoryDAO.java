package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryDAO extends JpaRepository<UsersEntity, Long> {
    
}
