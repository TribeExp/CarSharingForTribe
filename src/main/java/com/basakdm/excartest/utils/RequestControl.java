package com.basakdm.excartest.utils;

import java.sql.*;

public abstract class RequestControl {

    protected Connection connection;
    protected Statement statement;
    protected String username = "root";
    protected String password = "22256";
    protected String connectionUrl = "jdbc:mysql://localhost:3336/car_sharing?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    RequestControl(){
        try {
            connection = DriverManager.getConnection(connectionUrl, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Не удалось создать Connection, Statement");
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public void firstQueries() {}

}
