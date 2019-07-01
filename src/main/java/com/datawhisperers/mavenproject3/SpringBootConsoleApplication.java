/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datawhisperers.mavenproject3;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.CaptureTransaction;
import com.datawhisperers.mavenproject3.service.DatabaseService;
import com.datawhisperers.mavenproject3.service.HelloMessageService;
import static java.lang.System.exit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author steveo
 */
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    private HelloMessageService helloService;

    @Autowired
    private DatabaseService databaseService;

    @CaptureTransaction
    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.run(args);
    }

    //access command line arguments
    //@CaptureTransaction
    @CaptureSpan
    @Override
    public void run(String... args) throws Exception {

        if (args.length > 0) {
            System.out.println(helloService.getMessage(args[0].toString()));
        } else {
            System.out.println(helloService.getMessage());
        }

        databaseService.executeDatabaseSQLService("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/drwho", "postgres", "sql", "select * from drwho_episodes");
        
        databaseService.executeDatabaseCallService("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/drwho", "postgres", "sql", "insert into steve values (1)");

        databaseService.executeDatabaseSQLService("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/drwho", "root", "my-secret-pw", "select * from drwho_episodes");
        
        databaseService.executeDatabaseCallService("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/drwho", "root", "my-secret-pw", "insert into steve values (1)");

        exit(0);
    }
}
