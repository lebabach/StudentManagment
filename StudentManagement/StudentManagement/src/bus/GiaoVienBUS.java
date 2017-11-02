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
public class GiaoVienBUS {

    public static Vector<GiaoVienDTO> searchGiaovienByKhoiAndMonhoc(String tenKhoi, String tenmh, String tengv) {
        return GiaoVienDAO.searchGiaovienByKhoiAndMonhoc(tenKhoi, tenmh, tengv);
    }

    public static int updateGiaoVien(int gvid, String tenmh, String tengv, String gioitinh, String diachi, String sodth, String avatar) {
        return GiaoVienDAO.updateGiaoVien(gvid, tenmh, tengv, gioitinh, diachi, sodth, avatar);
    }

    public static int insertGiaoVien(String khoi, String TenMH, String TenGiaoVien, String gioitinh, String diachi, String sodth, String avatar) {
        return GiaoVienDAO.insertGiaoVien(khoi, TenMH, TenGiaoVien, gioitinh, diachi, sodth, avatar);
    }
}
