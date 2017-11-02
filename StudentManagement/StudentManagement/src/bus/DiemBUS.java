/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dao.DiemDAO;
import dto.DiemDTO;
import java.util.Vector;

/**
 *
 * @author PC
 */
public class DiemBUS {
    //Phuong thuc de them moi diem cho sinh vien

    public static int InsertDiemHocSinh(int HocSinhID, int MonhocID, int HocKyID, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) {
        return DiemDAO.InsertDiemHocSinh(HocSinhID, MonhocID, HocKyID, DiemMieng, Diem15Lan1, Diem15Lan2, Diem1TietLan1, Diem1TietLan2, DiemThiCuoiKi);
    }

    //Phuong thuc de chinh sua diem cho sinh vien
    public static int UpdateDiemHs(String TenKhoi, String Tenlop, String TenMH, String TenHocKy, String Ten, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) {
        return DiemDAO.UpdateDiemHs(TenKhoi, Tenlop, TenMH, TenHocKy, Ten, DiemMieng, Diem15Lan1, Diem15Lan2, Diem1TietLan1, Diem1TietLan2, DiemThiCuoiKi);
    }

    public static Vector<DiemDTO> searchDiemByTenKhoiTenLopMonhocHocKyTenHS(String khoi, String tenlop, String monhoc, String hocky, String tenhs) {
        return DiemDAO.searchDiemByTenKhoiTenLopMonhocHocKyTenHS(khoi, tenlop, monhoc, hocky, tenhs);
    }

    public static int deleteDiemHs(String TenKhoi, String Tenlop, String TenMH, String TenHocKy, String Ten, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) {
        return DiemDAO.deleteDiemHs(TenKhoi, Tenlop, TenMH, TenHocKy, Ten, DiemMieng, Diem15Lan1, Diem15Lan2, Diem1TietLan1, Diem1TietLan2, DiemThiCuoiKi);
    }
}
