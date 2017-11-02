/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dao.*;
import dto.*;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class HocSinhBUS {

    public static Vector<HocSinhDTO> searchHocSinhByTenKhoiTenLopTenHocSinh(String tenKhoi, String tenLop, String tenHs) {
        return HocSinhDAO.searchHocSinhByTenKhoiTenLopTenHocSinh(tenKhoi, tenLop, tenHs);
    }

    public static int UpdateHocSinh(int HocSinhID, String tenKhoi, String tenlop, String tenhs, String gioitinh, String diachi, String sodth, String avatar) {
        return HocSinhDAO.UpdateHocSinh(HocSinhID, tenKhoi, tenlop, tenhs, gioitinh, diachi, sodth, avatar);
    }

    public static int procInsertHocSinh(String tenKhoi, String tenlop, String tenhs, String gioitinh, String diachi, String sodth, String avatar) {
        return HocSinhDAO.procInsertHocSinh(tenKhoi, tenlop, tenhs, gioitinh, diachi, sodth, avatar);
    }

    public static Vector<HocSinhDTO> searchHocSinhByHocSinhID(int HocSinhID) {
        return HocSinhDAO.searchHocSinhByHocSinhID(HocSinhID);
    }
}
