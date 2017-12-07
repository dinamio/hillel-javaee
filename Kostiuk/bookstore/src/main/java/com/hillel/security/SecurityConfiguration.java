package com.hillel.security;

import com.hillel.security.filter.JWTAuthenticationFilter;
import com.hillel.security.filter.JWTAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Configuration
@ComponentScan("com.hillel")
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthHelper authHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authHelper, authenticationManager());
        JWTAuthorizationFilter authorizationFilter = new JWTAuthorizationFilter(authHelper, authenticationManager());
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/user/register", "/start").permitAll()
                .antMatchers("/resources/**", "/css/**", "/js/**").permitAll()
                .antMatchers("/books/form", "/books/edit", "/books/delete").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .passwordParameter("password").usernameParameter("login")
                .defaultSuccessUrl("/welcome").failureForwardUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout").permitAll()
                .deleteCookies(AuthHelper.TOKEN_AUTH_NAME)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
                .addFilter(authenticationFilter)
                .addFilter(authorizationFilter);
    }

}
