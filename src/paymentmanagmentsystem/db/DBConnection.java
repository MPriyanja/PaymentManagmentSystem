/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paymentmanagmentsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mihiran
 */
public class DBConnection {

    Connection con = this.getConnection();

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_management", "root", "");
        } catch (Exception ex) {
            System.out.println("Error : "+ex);
        }
        return conn;
    }
    
    public boolean validateUser(String userName, String password) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet result = null;
        boolean permissionStatus = false;
        try {
            String query = "SELECT USER_NAME,PASSWORD FROM SYS_USERS WHERE USER_NAME = ? AND PASSWORD = ? ";
            stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, password);

            result = stmt.executeQuery();
            while (result.next()) {
                permissionStatus = true;
            }
            System.out.println(query);
        } catch (Exception ex) {
            throw ex;
        }
        result.close();
        stmt.close();

        return permissionStatus;

    }

}
