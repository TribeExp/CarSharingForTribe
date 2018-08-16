package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.UserRepositoryDAO;
import com.basakdm.excartest.entity.UserEntity;
import com.basakdm.excartest.enum_ent.Roles;
import com.basakdm.excartest.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;
    @Autowired
    private UserDetailsService userDetailsService;

    private AuthenticationManager getAuthenticationManager(){
        return authentication -> {
            String email = authentication.getName();
            String password = authentication.getCredentials().toString();
            UserEntity user = userRepositoryDAO.findByMailEquals(email)
                    .orElseThrow(() -> new RuntimeException("User with such a mail doesn't exist"));

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            /*for (Roles role : user.getRole()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
            }*/

            return new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);
        };
    }

    @Override
    public String findLoggedInEmail() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    @Override
    public boolean autoLogin(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        getAuthenticationManager().authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return true;
        }
        return false;
    }

}
