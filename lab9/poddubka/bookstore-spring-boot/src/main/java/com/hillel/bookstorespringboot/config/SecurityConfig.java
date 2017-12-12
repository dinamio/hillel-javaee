package com.hillel.bookstorespringboot.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
           .jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select user_name, user_password, enable from user where user_name=?")
            .authoritiesByUsernameQuery("select user_name, role from user where user_name=?")
            .passwordEncoder(passwordEncoder())
        ;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/book").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/book")
                .deleteCookies("cookie","thebestcookie").permitAll()
        ;

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();

        return bcrypt;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/security/*");
    }
}
