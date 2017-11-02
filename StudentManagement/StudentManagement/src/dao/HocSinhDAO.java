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
 * @author Administrator
 */
public class HocSinhDAO {

    public static Vector<HocSinhDTO> searchHocSinhByTenKhoiTenLopTenHocSinh(String tenKhoi, String tenLop, String tenHs) {
        System.out.println("vao duoc searchHocSinhByTenKhoiTenLopTenHocSinh: " + tenKhoi + " lop: " + tenLop + " tenHS: " + tenHs);
        Vector<HocSinhDTO> vec = new Vector<HocSinhDTO>();
        String sql = "EXECUTE searchHocSinhByTenKhoiTenLopTenHocSinh " + "N'" + tenKhoi + "'" + "," + "N'" + tenLop + "'" + "," + "N'" + tenHs + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                HocSinhDTO hocSinh = new HocSinhDTO();
                hocSinh.setTenKhoi(rs.getString("TenKhoi"));
                hocSinh.setTenlop(rs.getString("Tenlop"));
                hocSinh.setHocSinhID(rs.getInt("HocSinhID"));
                hocSinh.setTen(rs.getString("Ten"));
                hocSinh.setGioiTinh(rs.getString("GioiTinh"));
                hocSinh.setDiachi(rs.getString("Diachi"));
                hocSinh.setSoDienThoai(rs.getString("SoDienThoai"));
                hocSinh.setAvatar(rs.getString("Avatar"));
                vec.add(hocSinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    public static Vector<HocSinhDTO> searchHocSinhByHocSinhID(int HocSinhID) {
        System.out.println("vao duoc searchHocSinhByHocSinhID: " + HocSinhID);
        Vector<HocSinhDTO> vec = new Vector<HocSinhDTO>();
        String sql = "EXECUTE searchHocSinhByHocSinhID " + HocSinhID;
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                HocSinhDTO hocSinh = new HocSinhDTO();
                hocSinh.setTenKhoi(rs.getString("TenKhoi"));
                hocSinh.setTenlop(rs.getString("Tenlop"));
                hocSinh.setKhoiID(rs.getInt("KhoiID"));
                hocSinh.setTen(rs.getString("Ten"));
                vec.add(hocSinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }
//UpdateHocSinh

    public static int UpdateHocSinh(int HocSinhID, String tenKhoi, String tenlop, String tenhs, String gioitinh, String diachi, String sodth, String avatar) {
        //System.out.println("UpdateHocSinh tenkhoi: "+tenKhoi+" tenlop: "+tenlop+" tenhs: "+tenhs+" gioitinh: "+gioitinh+" diachi: "+diachi+" sodth: "+sodth+" avatar: "+avatar);
        String sql = "EXECUTE UpdateHocSinh "  + HocSinhID + "," + "N'" +tenKhoi + "'" + "," + "N'" + tenlop + "'" + "," + "N'" + tenhs + "'" + "," + "N'" + gioitinh + "'" + "," + "N'" + diachi + "'" + "," + "N'" + sodth + "'" + "," + "N'" + avatar + "'";
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }

    public static int procInsertHocSinh(String tenKhoi, String tenlop, String tenhs, String gioitinh, String diachi, String sodth, String avatar) {
        System.out.println("procInsertHocSinh tenkhoi: " + tenKhoi + " tenlop: " + tenlop + " tenhs: " + tenhs + " gioitinh: " + gioitinh + " diachi: " + diachi + " sodth: " + sodth + " avatar: " + avatar);
        String sql = "EXECUTE procInsertHocSinh " +  "N'" + tenKhoi + "'" + "," + "N'" + tenlop + "'" + "," + "N'" + tenhs + "'" + "," + "N'" + gioitinh + "'" + "," + "N'" + diachi + "'" + "," + "N'" + sodth + "'" + "," + "N'" + avatar + "'";
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }
}
