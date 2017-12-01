package com.hillel.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by eugen on 12/1/17.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication().
                withUser("admin").password("admin").roles("ADMI")
                .and().
              withUser("user").password("user").roles("fdlksamflk");*/
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from users where username=?")
        .authoritiesByUsernameQuery("select username,role from users where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                anyRequest().authenticated().
                and().
                formLogin().loginPage("/login").defaultSuccessUrl("/server").permitAll()
        .and().logout().deleteCookies("cookie","thebestcookie").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().;
    }
}
