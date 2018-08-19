package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.RoleRepositoryDAO;
import com.basakdm.excartest.dao.UserRepositoryDAO;
import com.basakdm.excartest.entity.Role;
import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepositoryDAO roleRepositoryDAO;

    @Override
    public UserEntity createUser(String email, String password) {
        UserEntity newUser = new UserEntity();
        Role userRole = roleRepositoryDAO.findByRole("USER");
        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        newUser.setMail(email);
        newUser.setActive(true);
        newUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        return userRepositoryDAO.saveAndFlush(newUser);
    }

    @Override
    public UserEntity createAdmin(String email, String password) {
        UserEntity newUser = new UserEntity();
        Role userRole = roleRepositoryDAO.findByRole("ADMIN");
        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        newUser.setMail(email);
        newUser.setActive(true);
        newUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        return userRepositoryDAO.saveAndFlush(newUser);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepositoryDAO.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepositoryDAO.findById(id);
    }

    @Override
    public void delete(long id) {
        Optional<UserEntity> userOld = findById(id);
        if(userOld.isPresent()) userRepositoryDAO.deleteById(id);
    }

    @Override
    public void update(UserEntity userEntity) {
        Long id = userEntity.getId();
        Optional<UserEntity> userOld = findById(id);
        if(userOld.isPresent()) userRepositoryDAO.save(userEntity);
    }

    @Override
    public String getPasswordById(Long userId) {
        return bCryptPasswordEncoder.encode(findById(userId).get().getPassword());
    }

    @Override
    public Optional<UserEntity> findByMail(String email) {
        return userRepositoryDAO.findByMailEquals(email);
    }

    @Override
    public long getCurrentUserId() throws Exception {
        UserEntity userEntity = getCurrentUser()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userEntity.getId();
    }

    @Override
    public Optional<UserEntity> getCurrentUser() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (null == auth) {
            throw new Exception("Authentication context not found");
        }

        Object obj = auth.getPrincipal();
        String username = "";

        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }

        return userRepositoryDAO.findByMailEquals(username);
    }

    @Override
    public Set<Role> getCurrentUserRoles() throws Exception{
        UserEntity userEntity = getCurrentUser()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userEntity.getRoles();
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepositoryDAO.saveAndFlush(userEntity);
    }


}
