/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.AdministratorDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SqlDataAccessHelper;

/**
 *
 * @author PC
 */
public class AdministratorDAO {
    // Phuong thuc nay de kiem tra dang nhap cua Admin
    public static int CheckAdminLogin(String UserName,String pass){
        int i=0;
        String sql = "EXECUTE CheckAdminLogin "+"'"+UserName+"'"+","+"'"+pass+"'";
        System.out.println("vao duoc admin: ");
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
}
