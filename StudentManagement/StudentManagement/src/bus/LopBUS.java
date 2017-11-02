/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dao.LopDAO;
import dto.LopDTO;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author PC
 */
public class LopBUS {
    //Phuong thuc nay lay tat ca cac lop trong CSDL

    public static Vector<LopDTO> searchLopByTenKhoiAndTenLop(String tenKhoi, String tenLop) {
        return LopDAO.searchLopByTenKhoiAndTenLop(tenKhoi, tenLop);
    }
    //Phuong thuc tim kiem khoahoc by MaSV

    public static int UpdateLop(int LopID, String TenKhoi, String Tenlop, int siso, String NamHoc) {
        return LopDAO.UpdateLop(LopID, TenKhoi, Tenlop, siso, NamHoc);
    }

    public static int insertLop(String TenKhoi, String Tenlop, String NamHoc) {
        return LopDAO.insertLop(TenKhoi, Tenlop, NamHoc);
    }

    public static int deleteLop(int LopID) {
        return LopDAO.deleteLop(LopID);
    }
}
