package com.hillel.repository;

import lombok.SneakyThrows;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.sql.Connection;

public class Connector {

    private static final String DB_URL    = "jdbc:mysql://localhost:3306/bookstore?serverTimezone=UTC";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME  = "root";
    private static final String PASSWORD  = "root";

    private static volatile Connector instance;

    public static Connector getInstance() {
        if (instance == null) {
            synchronized (Connector.class) {
                if (instance == null) {
                    instance = new Connector();
                }
            }
        }
        return instance;
    }

    private DataSource dataSource;

    @SneakyThrows
    private Connector() {
        Class.forName(DB_DRIVER);
        dataSource = dataSourcePool();
    }

    @SneakyThrows
    public Connection getConnection() {
//        if (connection == null || connection.isClosed()) {
//            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//        }
        return dataSource.getConnection();
    }

    private DataSource dataSourcePool() throws Exception {
        ConnectionFactory factory = new DriverManagerConnectionFactory(DB_URL, USERNAME, PASSWORD);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(factory, null);
        GenericObjectPool<? extends Connection> objectPool = new GenericObjectPool<>(poolableConnectionFactory);
//        poolableConnectionFactory.setPool(objectPool);
        objectPool.setMaxTotal(10);
        objectPool.setMaxIdle(5);
        objectPool.setMinIdle(2);
        return new PoolingDataSource<>(objectPool);
    }

}
