package com.hillel.configuration;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.hillel")
@PropertySource({"classpath:application.properties"})
public class RootApplicationContextConfig {

    @Autowired
    private Environment environment;

    @Bean("dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass(getProperty("datasource.driver-class-name"));
        dataSource.setJdbcUrl(getProperty("datasource.url"));
        dataSource.setUser(getProperty("datasource.username"));
        dataSource.setPassword(getProperty("datasource.password"));
        return dataSource;
    }

    @Bean("sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.put("hibernate.current_session_context_class", "thread");
        hibernateProperties.put("hibernate.default_batch_fetch_size", 10);
        hibernateProperties.put("hibernate.connection.pool_size", 10);
        hibernateProperties.put("hibernate.show_sql", true);

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.hillel.model");
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

    private String getProperty(String key) {
        return environment.getProperty(key);
    }

}
