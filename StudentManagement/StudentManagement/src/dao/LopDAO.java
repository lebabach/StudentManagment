/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.*;
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
public class LopDAO {
    //Phuong thuc nay lay tat ca cac lop trong CSDL

    public static Vector<LopDTO> searchLopByTenKhoiAndTenLop(String tenKhoi, String tenLop) {
        Vector<LopDTO> vec = new Vector<LopDTO>();
        String sql = "EXECUTE searchLopByTenKhoiAndTenLop " + "N'" + tenKhoi + "'" + "," + "N'" + tenLop + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                LopDTO lop = new LopDTO();
                lop.setTenKhoi(rs.getString("TenKhoi"));
                lop.setLopID(rs.getInt("LopID"));
                lop.setTenlop(rs.getString("Tenlop"));
                lop.setSiso(rs.getInt("siso"));
                lop.setNamHoc(rs.getString("NamHoc"));
                vec.add(lop);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    public static int UpdateLop(int LopID, String TenKhoi, String Tenlop, int siso, String NamHoc) {
        System.out.println("UpdateLop LopID: " + LopID + " TenKhoi: " + TenKhoi + " Tenlop: " + Tenlop + " siso: " + siso + " NamHoc: " + NamHoc);
        String sql = "EXECUTE UpdateLop " + LopID + "," + "N'" + TenKhoi + "'" + "," + "N'" + Tenlop + "'" + "," + siso + "," + "N'" + NamHoc + "'";
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }

    public static int insertLop(String TenKhoi, String Tenlop, String NamHoc) {
        //System.out.println("UpdateLop LopID: "+LopID+" TenKhoi: "+TenKhoi+" Tenlop: "+Tenlop+" siso: "+siso+" NamHoc: "+NamHoc);
        String sql = "EXECUTE insertLop " + "N'" + TenKhoi + "'" + "," + "N'" + Tenlop + "'" + "," + "N'" + NamHoc + "'";
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }

    public static int deleteLop(int LopID) {
        //System.out.println("UpdateLop LopID: "+LopID+" TenKhoi: "+TenKhoi+" Tenlop: "+Tenlop+" siso: "+siso+" NamHoc: "+NamHoc);
        String sql = "EXECUTE deleteLop " + LopID;
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }
}
