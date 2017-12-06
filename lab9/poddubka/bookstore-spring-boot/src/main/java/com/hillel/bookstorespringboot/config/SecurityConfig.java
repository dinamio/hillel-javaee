package com.hillel.bookstorespringboot.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    // Для jdbcAuthentication() нужен Datasource
    @Autowired
    DataSource dataSource;

    // Пишем свой Authentication provider
  /*  @Autowired
    AuthenticationProvider authenticationProvider;
 */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            // храниться в памяти
           /* .inMemoryAuthentication()
            .withUser("admin").password("admin").roles("ADMIN")
            .and()
            .withUser("user").password("user").roles("AUTHENTIFICATION"); */

            // берем из базы MySQL(hillel_db_lab)
           .jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select user_name, user_password, enable from user where user_name=?")
            .authoritiesByUsernameQuery("select user_name, role from user where user_name=?");

           // своя реализация Authentication provider --> класс MyAuth
          /* .authenticationProvider(authenticationProvider); */
    }

    // Переопределяем доступ к страницам
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().
//            anyRequest().authenticated().
//            and().
//                // TODO: оптимизировать путь (убрать "/book")
////                authorizeRequests().mvcMatchers("*/book/*").permitAll().and().
//            formLogin().loginPage("/security/login").defaultSuccessUrl("/book/book").permitAll()
//            .and().logout().deleteCookies("cookie","thebestcookie").permitAll()
//            .and().antMatcher("*/book*");
//    }

    // Игнорируем Security в одной странице (не работает)
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("*/book/*");
//    }
}
