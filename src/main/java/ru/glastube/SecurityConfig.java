package ru.glas***;

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
                        "select login as username, password, nickname from user where login like 'pom'")
                .authoritiesByUsernameQuery("select login as username, password, nickname from user where login like 'pom'");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
            .antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/bye").access("hasRole('ROLE_USER')")
            .and()
            .formLogin()
            .defaultSuccessUrl("/");



    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                System.out.println(rawPassword.toString());
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                System.out.println("Matches = " + rawPassword.toString() + " " + encodedPassword);
                return true;
            }
        };
    }

}
