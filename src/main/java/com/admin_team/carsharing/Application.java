package com.admin_team.carsharing;

import com.admin_team.carsharing.utils.connect_db.ConnectMySQL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        new ConnectMySQL().firstQueries();
    }
}
