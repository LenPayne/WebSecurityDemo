/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.lambtoncollege.websecdemo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/* Relies on DB:
 CREATE DATABASE websecdemo;
 USE websecdemo;
 CREATE TABLE users (username VARCHAR(255) NOT NULL PRIMARY KEY, 
 password VARCHAR(255) NOT NULL);
 */
/**
 *
 * @author c0587637
 */
@SessionScoped
@Named
public class Login implements Serializable {

    private boolean loggedIn;
    private String username;
    private String password;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void doLogin() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/websecdemo",
                "root",
                "");
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM users WHERE username = '"
                + username + "' AND password = '" + password + "' LIMIT 1";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            loggedIn = true;
        } else {
            loggedIn = false;
        }
    }

}
