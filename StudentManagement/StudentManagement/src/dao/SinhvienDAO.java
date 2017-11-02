/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.SinhvienDTO;
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
public class SinhvienDAO {
    // Phuong thuc nay de kiem tra dang nhap cua Sinhvien

    public static int CheckSinhvienLogin(String MaSV, String pass) {
        int i = 0;
        ResultSet rs = null;
        String sql = "EXECUTE CheckSinhvienLogin " + "'" + MaSV + "'" + "," + "'" + pass + "'";
        try {
            System.out.println("SqlDataAccessHelper================================12121 : " + SqlDataAccessHelper.conn);
            rs = SqlDataAccessHelper.executequery(sql);
            while (rs.next()) {
                if (Integer.parseInt(rs.getString("matinhtrang")) == 1) {
                    System.out.println("Tình Trạng: " + rs.getString("matinhtrang"));
                    i++;
                }
            }
        } catch (SQLException ex) {
            System.out.println("resultset try catch: " + rs);
            Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    // Phuong thuc lay tat ca cac sinh vien tu CSDL
    public static Vector<SinhvienDTO> GetAllSinhvien() {
        Vector<SinhvienDTO> vec = new Vector<SinhvienDTO>();
        String sql = "EXECUTE GetAllSinhvien";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                SinhvienDTO sinhvien = new SinhvienDTO();
                sinhvien.setMaSV(rs.getString("Mã Sinh Viên"));
                sinhvien.setPass(rs.getString("Password"));
                sinhvien.setTentruong(rs.getString("Tên Trường"));
                sinhvien.setTenkhoa(rs.getString("Tên Khoa"));
                sinhvien.setTenlop(rs.getString("Tên Lớp"));
                sinhvien.setTenSV(rs.getString("Tên Sinh Viên"));
                sinhvien.setGioitinh(rs.getString("Giới Tính"));
                sinhvien.setEmail(rs.getString("Email"));
                sinhvien.setDiachi(rs.getString("Địa Chỉ"));
                sinhvien.setAvatar(rs.getString("Avatar"));
                sinhvien.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(sinhvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc them moi sinh vien
    public static int Insert_Sinhvien(String pass, String tenSV, String tentruong, String tenkhoa, String tenlop, String gioitinh, String email, String diachi, String avatar) {
        System.out.println("PASS==" + pass + " Ten SV : " + tenSV + " Truong : " + tentruong + " tenkhoa : " + tenkhoa + " tenlop : " + tenlop + " gioi tinh : " + gioitinh + " Email : " + email + " Dia chi : " + diachi + " Avatar : " + avatar);
        String sql = "EXECUTE Insert_Sinhvien " + "N'" + pass + "'" + "," + "N'" + tenSV + "'" + "," + "N'" + tentruong + "'" + "," + "N'" + tenkhoa + "'" + "," + "N'" + tenlop + "'" + "," + "N'" + gioitinh + "'" + "," + "N'" + email + "'" + "," + "N'" + diachi + "'" + "," + "N'" + avatar + "'";
        int i = SqlDataAccessHelper.execute__query(sql);
        return i;
    }

    //Phuong thuc nay de update thong tin cho sinh vien
    public static int UpdateSinhvien(String MaSV, String pass, String ten, String gioitinh, String email, String diachi) {
        String sql = "EXECUTE UpdateSinhvien " + "'" + MaSV + "'" + "," + "N'" + pass + "'" + "," + "N'" + ten + "'" + "," + "N'" + gioitinh + "'" + "," + "N'" + email + "'" + "," + "N'" + diachi + "'";
        return SqlDataAccessHelper.execute__query(sql);
    }

    //Phuong thuc nay de update thong tin cho sinh vien boi Admin
    public static int UpdateSinhvienByAdmin(String MaSV, String pass, String ten, String gioitinh, String email, String diachi, String avatar) {
        String sql = "EXECUTE UpdateSinhvienByAdmin " + "N'" + MaSV + "'" + "," + "N'" + pass + "'" + "," + "N'" + ten + "'" + "," + "N'" + gioitinh + "'" + "," + "N'" + email + "'" + "," + "N'" + diachi + "'" + "," + "N'" + avatar + "'";
        return SqlDataAccessHelper.execute__query(sql);
    }

    //Phuong thuc dung de tim kiem sinh vien theo MaSV
    public static Vector<SinhvienDTO> SearchSinhvienByID(String MaSV) {
        System.out.println("ma sinh vien: "+MaSV);
        Vector<SinhvienDTO> vec = new Vector<SinhvienDTO>();
        String sql = "EXECUTE SearchSinhvienByID " + "'" + MaSV + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                SinhvienDTO sinhvien = new SinhvienDTO();
                sinhvien.setMaSV(rs.getString("Mã Sinh Viên"));
                sinhvien.setPass(rs.getString("Password"));
                sinhvien.setTentruong(rs.getString("Tên Trường"));
                sinhvien.setTenkhoa(rs.getString("Tên Khoa"));
                sinhvien.setTenlop(rs.getString("Tên Lớp"));
                sinhvien.setTenSV(rs.getString("Tên Sinh Viên"));
                sinhvien.setGioitinh(rs.getString("Giới Tính"));
                sinhvien.setEmail(rs.getString("Email"));
                sinhvien.setDiachi(rs.getString("Địa Chỉ"));
                sinhvien.setAvatar(rs.getString("Avatar"));
                sinhvien.setTinhtrang(rs.getString("Tình Trạng"));
                sinhvien.setMakhoa(rs.getString("makhoa"));
                vec.add(sinhvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc dung de tim kiem sinh vien theo TenSV
    public static Vector<SinhvienDTO> SearchSinhvienByIDOrTen(String name) {
        Vector<SinhvienDTO> vec = new Vector<SinhvienDTO>();
        String sql = "EXECUTE SearchSinhvienByIDOrTen " + "N'" + name + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                SinhvienDTO sinhvien = new SinhvienDTO();
                sinhvien.setMaSV(rs.getString("Mã Sinh Viên"));
                sinhvien.setPass(rs.getString("Password"));
                sinhvien.setTentruong(rs.getString("Tên Trường"));
                sinhvien.setTenkhoa(rs.getString("Tên Khoa"));
                sinhvien.setTenlop(rs.getString("Tên Lớp"));
                sinhvien.setTenSV(rs.getString("Tên Sinh Viên"));
                sinhvien.setGioitinh(rs.getString("Giới Tính"));
                sinhvien.setEmail(rs.getString("Email"));
                sinhvien.setDiachi(rs.getString("Địa Chỉ"));
                sinhvien.setAvatar(rs.getString("Avatar"));
                sinhvien.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(sinhvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc tim kiem sinh vien theo Truong
    public static Vector<SinhvienDTO> SearchSinhvienByTruong(String tentruong) {
        Vector<SinhvienDTO> vec = new Vector<SinhvienDTO>();
        String sql = "EXECUTE SearchSinhvienByTruong " + "N'" + tentruong + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                SinhvienDTO sinhvien = new SinhvienDTO();
                sinhvien.setMaSV(rs.getString("Mã Sinh Viên"));
                sinhvien.setPass(rs.getString("Password"));
                sinhvien.setTentruong(rs.getString("Tên Trường"));
                sinhvien.setTenkhoa(rs.getString("Tên Khoa"));
                sinhvien.setTenlop(rs.getString("Tên Lớp"));
                sinhvien.setTenSV(rs.getString("Tên Sinh Viên"));
                sinhvien.setGioitinh(rs.getString("Giới Tính"));
                sinhvien.setEmail(rs.getString("Email"));
                sinhvien.setDiachi(rs.getString("Địa Chỉ"));
                sinhvien.setAvatar(rs.getString("Avatar"));
                sinhvien.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(sinhvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc tim kiem sinh vien theo Truong,Ten/Ma SV
    public static Vector<SinhvienDTO> SearchSinhvienByTruongTen(String tentruong, String value) {
        Vector<SinhvienDTO> vec = new Vector<SinhvienDTO>();
        String sql = "EXECUTE SearchSinhvienByTruongTen " + "N'" + tentruong + "'" + "," + "N'" + value + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                SinhvienDTO sinhvien = new SinhvienDTO();
                sinhvien.setMaSV(rs.getString("Mã Sinh Viên"));
                sinhvien.setPass(rs.getString("Password"));
                sinhvien.setTentruong(rs.getString("Tên Trường"));
                sinhvien.setTenkhoa(rs.getString("Tên Khoa"));
                sinhvien.setTenlop(rs.getString("Tên Lớp"));
                sinhvien.setTenSV(rs.getString("Tên Sinh Viên"));
                sinhvien.setGioitinh(rs.getString("Giới Tính"));
                sinhvien.setEmail(rs.getString("Email"));
                sinhvien.setDiachi(rs.getString("Địa Chỉ"));
                sinhvien.setAvatar(rs.getString("Avatar"));
                sinhvien.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(sinhvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc dung de tim kiem sinh vien theo truong,khoa
    public static Vector<SinhvienDTO> SearchSinhvienByTruongKhoa(String tentruong, String tenkhoa) {
        Vector<SinhvienDTO> vec = new Vector<SinhvienDTO>();
        String sql = "EXECUTE SearchSinhvienByTruongKhoa " + "N'" + tentruong + "'" + "," + "N'" + tenkhoa + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                SinhvienDTO sinhvien = new SinhvienDTO();
                sinhvien.setMaSV(rs.getString("Mã Sinh Viên"));
                sinhvien.setPass(rs.getString("Password"));
                sinhvien.setTentruong(rs.getString("Tên Trường"));
                sinhvien.setTenkhoa(rs.getString("Tên Khoa"));
                sinhvien.setTenlop(rs.getString("Tên Lớp"));
                sinhvien.setTenSV(rs.getString("Tên Sinh Viên"));
                sinhvien.setGioitinh(rs.getString("Giới Tính"));
                sinhvien.setEmail(rs.getString("Email"));
                sinhvien.setDiachi(rs.getString("Địa Chỉ"));
                sinhvien.setAvatar(rs.getString("Avatar"));
                sinhvien.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(sinhvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc dung de tim kiem sinh vien theo truong,khoa,ten/ma sinh vien
    public static Vector<SinhvienDTO> SearchSinhvienByTruongKhoaTen(String tentruong, String tenkhoa, String value) {
        Vector<SinhvienDTO> vec = new Vector<SinhvienDTO>();
        String sql = "EXECUTE SearchSinhvienByTruongKhoaTen " + "N'" + tentruong + "'" + "," + "N'" + tenkhoa + "'" + "," + "N'" + value + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                SinhvienDTO sinhvien = new SinhvienDTO();
                sinhvien.setMaSV(rs.getString("Mã Sinh Viên"));
                sinhvien.setPass(rs.getString("Password"));
                sinhvien.setTentruong(rs.getString("Tên Trường"));
                sinhvien.setTenkhoa(rs.getString("Tên Khoa"));
                sinhvien.setTenlop(rs.getString("Tên Lớp"));
                sinhvien.setTenSV(rs.getString("Tên Sinh Viên"));
                sinhvien.setGioitinh(rs.getString("Giới Tính"));
                sinhvien.setEmail(rs.getString("Email"));
                sinhvien.setDiachi(rs.getString("Địa Chỉ"));
                sinhvien.setAvatar(rs.getString("Avatar"));
                sinhvien.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(sinhvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc dung de tim kiem sinh vien theo Truong,Lop,Khoa
    public static Vector<SinhvienDTO> SearchSinhvienByTruongKhoaLopTen(String tentruong, String tenkhoa, String tenlop, String tenSV) {
        Vector<SinhvienDTO> vec = new Vector<SinhvienDTO>();
        String sql = "EXECUTE SearchSinhvienByTruongKhoaLopTen " + "N'" + tentruong + "'" + "," + "N'" + tenkhoa + "'" + "," + "N'" + tenlop + "'" + "," + "N'" + tenSV + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                SinhvienDTO sinhvien = new SinhvienDTO();
                sinhvien.setMaSV(rs.getString("Mã Sinh Viên"));
                sinhvien.setPass(rs.getString("Password"));
                sinhvien.setTentruong(rs.getString("Tên Trường"));
                sinhvien.setTenkhoa(rs.getString("Tên Khoa"));
                sinhvien.setTenlop(rs.getString("Tên Lớp"));
                sinhvien.setTenSV(rs.getString("Tên Sinh Viên"));
                sinhvien.setGioitinh(rs.getString("Giới Tính"));
                sinhvien.setEmail(rs.getString("Email"));
                sinhvien.setDiachi(rs.getString("Địa Chỉ"));
                sinhvien.setAvatar(rs.getString("Avatar"));
                sinhvien.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(sinhvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc dung de tim kiem sinh vien theo Truong,Lop,Khoa
    public static Vector<SinhvienDTO> SearchSinhvienByTruongKhoaLop(String tentruong, String tenkhoa, String tenlop) {
        Vector<SinhvienDTO> vec = new Vector<SinhvienDTO>();
        String sql = "EXECUTE SearchSinhvienByTruongKhoaLop " + "N'" + tentruong + "'" + "," + "N'" + tenkhoa + "'" + "," + "N'" + tenlop + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                SinhvienDTO sinhvien = new SinhvienDTO();
                sinhvien.setMaSV(rs.getString("Mã Sinh Viên"));
                sinhvien.setPass(rs.getString("Password"));
                sinhvien.setTentruong(rs.getString("Tên Trường"));
                sinhvien.setTenkhoa(rs.getString("Tên Khoa"));
                sinhvien.setTenlop(rs.getString("Tên Lớp"));
                sinhvien.setTenSV(rs.getString("Tên Sinh Viên"));
                sinhvien.setGioitinh(rs.getString("Giới Tính"));
                sinhvien.setEmail(rs.getString("Email"));
                sinhvien.setDiachi(rs.getString("Địa Chỉ"));
                sinhvien.setAvatar(rs.getString("Avatar"));
                sinhvien.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(sinhvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhvienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }
}
