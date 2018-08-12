package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.UserRepositoryDAO;
import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.enum_ent.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositoryDAO userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByMail(email)
                .orElseThrow(() -> new RuntimeException("User with such a mail doesn't exist"));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Roles role : user.getRole()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        }


        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), grantedAuthorities);
    }
}