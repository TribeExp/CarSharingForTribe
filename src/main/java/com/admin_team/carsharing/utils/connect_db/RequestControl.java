package com.admin_team.carsharing.utils.connect_db;

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

    protected Connection connection;
    protected Statement statement;

    private String username = "pdhyozs0rhqutiam";
    private String password = "civ6wjr1uwue4jf7";
    private String connectionUrl = "mysql://pdhyozs0rhqutiam:civ6wjr1uwue4jf7@uf63wl4z2daq9dbb.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/czf6r6gtkiv1r8og";

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


