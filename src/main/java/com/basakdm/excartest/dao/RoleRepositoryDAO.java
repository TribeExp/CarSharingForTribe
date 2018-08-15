package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepositoryDAO extends JpaRepository<Role, Integer>{
    Role findByRole(String role);
}
