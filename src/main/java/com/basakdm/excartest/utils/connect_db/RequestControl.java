package com.basakdm.excartest.utils.connect_db;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create connection to DB and describes first queries
 */
@Slf4j
@Setter
@Getter
public abstract class RequestControl {

    private Connection connection;
    private Statement statement;

    private String username = "root";
    private String password = "22256";
    private String connectionUrl = "jdbc:mysql://localhost:3336/car_sharing?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";



    RequestControl(){
        createConnection();
    }

    /**
     * First queries
     */
    public void firstQueries(){
    }

    /**
     * Create connection
     */
    private void createConnection(){
        try {
            connection = DriverManager.getConnection(connectionUrl, username, password);
            statement = connection.createStatement();
            log.info("Connection to DB :" + connectionUrl + " is successfully");
        } catch (SQLException e) {
            log.info("Connection to DB :" + connectionUrl + " failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


