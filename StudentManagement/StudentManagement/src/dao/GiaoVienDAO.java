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
public class GiaoVienDAO {

    public static Vector<GiaoVienDTO> searchGiaovienByKhoiAndMonhoc(String tenKhoi, String tenmh, String tengv) {
        System.out.println("vao duoc searchGiaovienByKhoiAndMonhoc: tenKhoi : " + tenKhoi + " tenmh: " + tenmh);
        Vector<GiaoVienDTO> vec = new Vector<GiaoVienDTO>();
        String sql = "EXECUTE searchGiaovienByKhoiAndMonhoc " + "N'" + tenKhoi + "'" + "," + "N'" + tenmh + "'" + "," + "N'" + tengv + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                GiaoVienDTO gv = new GiaoVienDTO();
                gv.setGiaoVienID(rs.getInt("GiaoVienID"));
                gv.setTenKhoi(rs.getString("TenKhoi"));
                gv.setTenMH(rs.getString("TenMH"));
                gv.setTenGiaoVien(rs.getString("TenGiaoVien"));
                gv.setGioitinh(rs.getString("GioiTinh"));
                gv.setDiachi(rs.getString("Diachi"));
                gv.setSoDienThoai(rs.getString("SoDienThoai"));
                gv.setAvatar(rs.getString("Avatar"));
                vec.add(gv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    public static int updateGiaoVien(int gvid, String tenmh, String tengv, String gioitinh, String diachi, String sodth, String avatar) {
        //System.out.println("UpdateHocSinh tenkhoi: "+tenKhoi+" tenlop: "+tenlop+" tenhs: "+tenhs+" gioitinh: "+gioitinh+" diachi: "+diachi+" sodth: "+sodth+" avatar: "+avatar);
        String sql = "EXECUTE updateGiaoVien " + gvid + "," + "N'" + tenmh + "'" + "," + "N'" + tengv + "'" + "," + "N'" + gioitinh + "'" + "," + "N'" + diachi + "'" + "," + "N'" + sodth + "'" + "," + "N'" + avatar + "'";
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }

    public static int insertGiaoVien(String khoi,String TenMH, String TenGiaoVien, String gioitinh, String diachi, String sodth, String avatar) {
        //System.out.println("UpdateHocSinh tenkhoi: "+tenKhoi+" tenlop: "+tenlop+" tenhs: "+tenhs+" gioitinh: "+gioitinh+" diachi: "+diachi+" sodth: "+sodth+" avatar: "+avatar);
        String sql = "EXECUTE insertGiaoVien " +"N'" + khoi + "'" + "," + "N'" + TenMH + "'" + "," + "N'" + TenGiaoVien + "'" + "," + "N'" + gioitinh + "'" + "," + "N'" + diachi + "'" + "," + "N'" + sodth + "'" + "," + "N'" + avatar + "'";
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }
}
