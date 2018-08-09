package com.basakdm.excartest;

import com.basakdm.excartest.utils.connect_bd.ConnectMySQL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        new ConnectMySQL().firstQueries();
    }
}
