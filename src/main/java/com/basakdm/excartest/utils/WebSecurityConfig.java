package com.basakdm.excartest.utils;

import com.basakdm.excartest.dao.RoleRepositoryDAO;
import com.basakdm.excartest.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private RoleRepositoryDAO roleRepositoryDAO;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://carsharing-d2e1c.firebaseapp.com"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/api/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("https://carsharing-d2e1c.firebaseapp.com/**")
                        .allowedHeaders("*");
            }
        };
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        Role user = new Role();
        if (roleRepositoryDAO.findByRole("USER") == null){
            user.setRole("USER");
            user.setId(2);
            roleRepositoryDAO.save(user);
        }
        if (roleRepositoryDAO.findByRole("ADMIN") == null){
            user.setRole("ADMIN");
            user.setId(1);
            roleRepositoryDAO.saveAndFlush(user);
        }

        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/auth/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/auth/**").permitAll()
                    //.antMatchers("/cars/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and().csrf().disable()
                    .formLogin()
                    .loginProcessingUrl("/auth/login")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .permitAll();
                //.and().cors();
                //.and().httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/auth/**")
                .and().ignoring().antMatchers(HttpMethod.GET, "/auth/**");
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser().password().authorities();
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder())
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery);
    }
}

