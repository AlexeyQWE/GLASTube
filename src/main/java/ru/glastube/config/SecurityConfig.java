package ru.glas***.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;
import java.io.*;
import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select user.login as username, user.password as password, user.enabled from user where user.login like ?")
                .authoritiesByUsernameQuery("select user.login as username, user.password as password, user.enabled from user where user.login like ?")
                .passwordEncoder(new StandardPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
            .antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/bye").access("hasRole('ROLE_USER')")
            .and()
            .formLogin()
            .defaultSuccessUrl("/sign_in");



    }
}
