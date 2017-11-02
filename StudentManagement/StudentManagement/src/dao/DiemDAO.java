/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DiemDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SqlDataAccessHelper;

/**
 *
 * @author PC
 */
public class DiemDAO {
    //Phuong thuc de them moi diem cho sinh vien
/*
    @HocSinhID INT,
    @MonhocID int,
    @HocKyID INT,
     */

    public static int InsertDiemHocSinh(int HocSinhID, int MonhocID, int HocKyID, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) {
        System.out.println("=============== HocSinhID : "+HocSinhID+" MonhocID: "+HocKyID+" diem mieng: "+DiemMieng+" diem 15 lan 1: "+Diem15Lan1+" diem 15 lan 2: "+Diem15Lan2+" diem1tiet lan 1: "+Diem1TietLan1+" diem 1 tiet lan 2: "+Diem1TietLan2+" diemcuoi ki: "+DiemThiCuoiKi);
        String sql = "EXECUTE InsertDiemHocSinh " + HocSinhID + "," + MonhocID + "," + HocKyID + "," + DiemMieng + "," + Diem15Lan1 + "," + Diem15Lan2 + "," + Diem1TietLan1 + "," + Diem1TietLan2 + "," + DiemThiCuoiKi;
        return SqlDataAccessHelper.execute__query(sql);
    }

    public static int deleteDiemHs(String TenKhoi, String Tenlop, String TenMH, String TenHocKy, String Ten, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) {
        System.out.println("=============== TenKhoi : "+TenKhoi+" Tenlop: "+Tenlop+" diem mieng: "+DiemMieng+" diem 15 lan 1: "+Diem15Lan1+" diem 15 lan 2: "+Diem15Lan2+" diem1tiet lan 1: "+Diem1TietLan1+" diem 1 tiet lan 2: "+Diem1TietLan2+" diemcuoi ki: "+DiemThiCuoiKi);
        String sql = "EXECUTE deleteDiemHs " + "N'" + TenKhoi + "'" + "," + "N'" + Tenlop + "'" + "," + "N'" + TenMH + "'" + "," + "N'" + TenHocKy + "'" + "," + "N'" + Ten + "'" + "," + DiemMieng + "," + Diem15Lan1 + "," + Diem15Lan2 + "," + Diem1TietLan1 + "," + Diem1TietLan2 + "," + DiemThiCuoiKi;
        return SqlDataAccessHelper.execute__query(sql);
    }

    //Phuong thuc de chinh sua diem cho sinh vien
    public static int UpdateDiemHs(String TenKhoi, String Tenlop, String TenMH, String TenHocKy, String Ten, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) {
        //System.out.println("=============== khoi : "+TenKhoi+" tenlop: "+Tenlop+" mamonhoc: "+TenMH+" hoc ky: "+TenHocKy+" tenhoc sinh: "+Ten+" diem mieng: "+DiemMieng+" diem 15 lan 1: "+Diem15Lan1+" diem 15 lan 2: "+Diem15Lan2+" diem1tiet lan 1: "+Diem1TietLan1+" diem 1 tiet lan 2: "+Diem1TietLan2+" diemcuoi ki: "+DiemThiCuoiKi);
        String sql = "EXECUTE UpdateDiemHs " + "N'" + TenKhoi + "'" + "," + "N'" + Tenlop + "'" + "," + "N'" + TenMH + "'" + "," + "N'" + TenHocKy + "'" + "," + "N'" + Ten + "'" + "," + DiemMieng + "," + Diem15Lan1 + "," + Diem15Lan2 + "," + Diem1TietLan1 + "," + Diem1TietLan2 + "," + DiemThiCuoiKi;
        return SqlDataAccessHelper.execute__query(sql);
    }

    public static Vector<DiemDTO> searchDiemByTenKhoiTenLopMonhocHocKyTenHS(String khoi, String tenlop, String monhoc, String hocky, String tenhs) {
       // System.out.println("vao duoc searchDiemByTenKhoiTenLopMonhocHocKyTenHS: " + khoi + " lop: " + tenlop + " tenHS: " + tenhs + " monhoc: " + monhoc + " hochy: " + hocky);
        Vector<DiemDTO> vec = new Vector<DiemDTO>();
        String sql = "EXECUTE searchDiemByTenKhoiTenLopMonhocHocKyTenHS " + "N'" + khoi + "'" + "," + "N'" + tenlop + "'" + "," + "N'" + monhoc + "'" + "," + "N'" + hocky + "'" + "," + "N'" + tenhs + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                DiemDTO diem = new DiemDTO();
                diem.setHocSinhID(rs.getInt("HocSinhID"));
                diem.setTenKhoi(rs.getString("TenKhoi"));
                diem.setTenLop(rs.getString("Tenlop"));
                diem.setTenHocSinh(rs.getString("Ten"));
                diem.setTenMH(rs.getString("TenMH"));
                diem.setDiemMieng(rs.getInt("DiemMieng"));
                diem.setDiem15Lan1(rs.getFloat("Diem15Lan1"));
                diem.setDiem15Lan2(rs.getFloat("Diem15Lan2"));
                diem.setDiem1TietLan1(rs.getFloat("Diem1TietLan1"));
                diem.setDiem1TietLan2(rs.getFloat("Diem1TietLan2"));
                diem.setDiemThiCuoiKi(rs.getFloat("DiemThiCuoiKi"));
                diem.setTenHocKy(rs.getString("TenHocKy"));
                diem.setHeSo(rs.getInt("HeSo"));
                //System.out.println("===========diem mieng: " + rs.getFloat("DiemMieng") + " diem15lan1: " + rs.getFloat("Diem15Lan1") + " diem15lan2: " + rs.getFloat("Diem15Lan2") + " diem1tietlan1: " + rs.getFloat("Diem1TietLan1") + " diem1tietlan2: " + rs.getFloat("Diem1TietLan2") + " diemcuoiki: " + rs.getFloat("DiemThiCuoiKi"));
                float diemTB = (((rs.getFloat("DiemMieng") + rs.getFloat("Diem15Lan1") + rs.getFloat("Diem15Lan2") + (rs.getFloat("Diem1TietLan1") + rs.getFloat("Diem1TietLan2")) * 2) / 7) * 2 + rs.getFloat("DiemThiCuoiKi")) / 3;
                //System.out.println("diemTB=============== : "+diemTB);
                DecimalFormat df = new DecimalFormat("0.0");
                String dtb = df.format(diemTB);
                String dtbnew = dtb.replaceAll(",", ".");
                //System.out.println("ten: " + rs.getString("Ten") + " diem mieng: " + (rs.getFloat("DiemMieng")  + " diem tB moi: " + Float.valueOf(dtbnew)));

                diem.setTBMon(Float.valueOf(dtbnew));
                vec.add(diem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }
}
