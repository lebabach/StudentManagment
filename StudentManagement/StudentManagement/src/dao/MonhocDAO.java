/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MonhocDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SqlDataAccessHelper;

/**
 *
 * @author PC
 */
public class MonhocDAO {
    //Phuong thuc de them moi mon hoc vao CSDL
/*
 @TenKhoi NVARCHAR(20),
@TenMH nvarchar(30),
@HeSo int
 */
    public static int insertMonhoc(String TenKhoi, String TenMH, int HeSo) {
        String sql = "EXECUTE insertMonhoc " + "N'" + TenKhoi + "'" + ","  + "N'" + TenMH + "'" + "," +  HeSo ;
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }

    //Phuong thuc de cap nhat thong tin cho mon hoc
    public static int updateMonhoc(int maMH,String tenkhoi, String tenMH, int heso) {
        String sql = "EXECUTE updateMonhoc " + maMH + "," + "N'" + tenkhoi + "'" + "," +"N'"+ tenMH + "'"+","  + heso ;
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }

    //Phuong thuc de cap nhat thong tin cho mon hoc
    public static int deleteMonhoc(int maMH) {
        String sql = "EXECUTE deleteMonhoc " + maMH ;
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }

    public static Vector<MonhocDTO> searchMonhocKhoiTenMH(String tenKhoi, String tenmh) {
        Vector<MonhocDTO> vec = new Vector<MonhocDTO>();
        String sql = "EXECUTE searchMonhocKhoiTenMH " + "N'" + tenKhoi + "'" + "," + "N'" + tenmh + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                MonhocDTO monhoc = new MonhocDTO();
                monhoc.setTenKhoi(rs.getString("TenKhoi"));
                monhoc.setMonhocID(rs.getInt("MonhocID"));
                monhoc.setTenMH(rs.getString("TenMH"));
                monhoc.setHeSo(rs.getInt("HeSo"));
                vec.add(monhoc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    public static Vector<MonhocDTO> searchMonhocIDByKhoiID(int KhoiID) {
        System.out.println("vao duoc searchMonhocIDByKhoiID: " + KhoiID);
        Vector<MonhocDTO> vec = new Vector<MonhocDTO>();
        String sql = "EXECUTE searchMonhocIDByKhoiID " + KhoiID;
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                MonhocDTO monhoc = new MonhocDTO();
                monhoc.setMonhocID(rs.getInt("MonhocID"));
                vec.add(monhoc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }
}
