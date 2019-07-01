/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datawhisperers.mavenproject3.service;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.CaptureTransaction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Service;

/**
 *
 * @author steveo
 */
@Service
public class DatabaseService {

    //@CaptureTransaction
    @CaptureSpan
    public void executeDatabaseSQLService(String driver, String url, String username, String password, String sql) {
        try {

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    //@CaptureTransaction
    @CaptureSpan
    public void executeDatabaseCallService(String driver, String url, String username, String password, String sql) {
        try {

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
