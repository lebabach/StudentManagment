/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class SqlDataAccessHelper {

    public static Connection conn ;
    public static Statement stmt;

    public void DisplayError(SQLException ex) {
        System.out.println("Error Message : " + ex.getMessage());
        System.out.println("SQL State : " + ex.getSQLState());
        System.out.println("Error Code : " + ex.getErrorCode());
    }

    public SqlDataAccessHelper(String user, String pass) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql = "jdbc:sqlserver://localhost:1433;instanceName=.;databaseName=QuanLyHocSinh";
            conn = DriverManager.getConnection(sql, user, pass);
            System.out.println("Successful!!! conn========================: "+conn);
        } catch (SQLException ex) {
            Logger.getLogger(SqlDataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqlDataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ResultSet execute(String sql) throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        return rs;
    }

    public static ResultSet executeprocedure(String nameProcedure) {
        ResultSet rs = null;
        try {
            CallableStatement stmt = conn.prepareCall(nameProcedure);
            stmt.execute();
            rs = stmt.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(SqlDataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    // Ham nay thuc thi cau lenh truy van de lay du lieu tu CSDL thong PROCEDURE
    public static ResultSet executequery(String query) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            System.out.println("conn=================== "+conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(SqlDataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    // Ham nay thuc thi viec insert va update
    public static void execute_query(String query) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(SqlDataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Ham nay thuc thi viec insert va update
    public static int execute__query(String query) {
        int i = 0;
        try {
            Statement stmt = conn.createStatement();
            i = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(SqlDataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
}
