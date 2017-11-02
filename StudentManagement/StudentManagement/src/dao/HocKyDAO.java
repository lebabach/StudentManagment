/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.HocKyDTO;
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
public class HocKyDAO {
    // Phuong thuc nay de lay hoc ky dua vao truong 
     public static Vector<HocKyDTO> GetHocKyByTruong(String tentruong){
        Vector<HocKyDTO> vec = new Vector<HocKyDTO>();
        String sql = "EXECUTE GetHocKyByTruong "+"N'"+tentruong+"'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                HocKyDTO hocky = new HocKyDTO();
                vec.add(hocky);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HocKyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }
}
