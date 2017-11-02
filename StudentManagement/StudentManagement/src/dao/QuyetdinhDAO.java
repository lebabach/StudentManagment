/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.QuyetdinhDTO;
import java.sql.Date;
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
public class QuyetdinhDAO {

    public static int InsertQuyetdinh(String maSV, String yeucau, String lydo, String tentruong, String tenkhoa, String tenlop,String ngaykt) {
        System.out.println("truong: " + tentruong + " khoa: " + tenkhoa + " ten lop: " + tenlop);
        String sql = "EXECUTE InsertQuyetdinh " + "'" + maSV + "'" + "," + "N'" + yeucau + "'" + "," + "N'" + lydo + "'" + "," + "N'" + tentruong + "'" + "," + "N'" + tenkhoa + "'" + "," + "N'" + tenlop + "'"+ "," + "'" + ngaykt + "'";
        return SqlDataAccessHelper.execute__query(sql);
    }
    //Phuong thuc nay de chen quyet dinh cua sinh vien vao CSDL
    public static int InsertQuyetdinhBaoLuu(String maSV, String ngaykt, String yeucau, String lydo) {
        System.out.println("Ly do : " + lydo);
        String sql = "EXECUTE InsertQuyetdinhBaoLuu " + "'" + maSV + "'" + "," + "'" + ngaykt + "'" + "," + "N'" + yeucau + "'" + "," + "N'" + lydo + "'";
        return SqlDataAccessHelper.execute__query(sql);
    }

    //Phuong thuc nay de chen quyet dinh phuc khao cua sinh vien vao CSDL
    public static int InsertQuyetdinhPhuckhao(String maSV, String yeucau, String monhoc, String lydo, String tentruong, String tenkhoa, String tenlop) {
        String sql = "EXECUTE InsertQuyetdinhPhuckhao " + "'" + maSV + "'" + "," + "N'" + yeucau + "'" + "," + "N'" + monhoc + "'" + "," + "N'" + lydo + "'"+ "," + "N'" + tentruong + "'"+ "," + "N'" + tenkhoa + "'"+ "," + "N'" + tenlop + "'";
        return SqlDataAccessHelper.execute__query(sql);
    }

    //Phuong thuc lay tat ca cac quyet dinh
    public static Vector<QuyetdinhDTO> GetAllQuyetDinh() {
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        String sql = "EXECUTE GetAllQuyetdinh";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc tim kiem quyet dinh theo truong,khoa,lop,yeucau,sinhvien
    public static Vector<QuyetdinhDTO> SearchQuyetdinh(String truong, String khoa, String lop, String yeucau, String svien) {
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        System.out.println("truong: " + truong + " khoa: " + khoa + " lop: " + lop + " yeu cau: " + yeucau + " svien: " + svien);
        String sql = "EXECUTE SearchQuyetdinh " + "N'" + truong + "'" + "," + "N'" + khoa + "'" + "," + "N'" + lop + "'" + "," + "N'" + yeucau + "'" + "," + "N'" + svien + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc tim kiem quyet dinh theo truong
    public static Vector<QuyetdinhDTO> SearchQuyetdinhByTruong(String tentruong) {
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        String sql = "EXECUTE SearchQuyetdinhByTruong " + "N'" + tentruong + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc tim kiem quyet dinh theo yeu cau
    public static Vector<QuyetdinhDTO> SearchQuyetdinhByYeucau(String yeucau) {
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        String sql = "EXECUTE SearchQuyetdinhByYeucau " + "N'" + yeucau + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc tim kiem quyet dinh theo truong va yeu cau
    public static Vector<QuyetdinhDTO> SearchQuyetdinhByTruongYeucau(String tentruong, String yeucau) {
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        String sql = "EXECUTE SearchQuyetdinhByTruongYeucau " + "N'" + tentruong + "'" + "," + "N'" + yeucau + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc lay tat ca cac quyet dinh phuc khao
    public static Vector<QuyetdinhDTO> GetAllQuyetdinhPhucKhao() {
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        String sql = "EXECUTE GetAllQuyetdinhPhucKhao";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setMonhoc(rs.getString("Tên Môn Học"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc lay tat ca cac quyet dinh phuc khao theo truong
    public static Vector<QuyetdinhDTO> SearchQuyetdinhPhucKhaoByTruong(String tentruong) {
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        String sql = "EXECUTE SearchQuyetdinhPhucKhaoByTruong " + "N'" + tentruong + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setMonhoc(rs.getString("Tên Môn Học"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc lay tat ca cac quyet dinh phuc khao theo yeu cau
    public static Vector<QuyetdinhDTO> SearchQuyetdinhPhucKhaoByYeucau(String yeucau) {
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        String sql = "EXECUTE SearchQuyetdinhPhucKhaoByYeucau " + "N'" + yeucau + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setMonhoc(rs.getString("Tên Môn Học"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phuong thuc lay tat ca cac quyet dinh phuc khao theo yeu cau
    public static Vector<QuyetdinhDTO> SearchQuyetdinhPhucKhaoByTruongYeucau(String tentruong, String yeucau) {
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        String sql = "EXECUTE SearchQuyetdinhPhucKhaoByTruongYeucau " + "N'" + tentruong + "'" + "," + "N'" + yeucau + "'";
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setMonhoc(rs.getString("Tên Môn Học"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Phương thức lấy quyết định theo QuyetdinhID
    public static Vector<QuyetdinhDTO> SearchQuyetdinhByID(int id) {
        System.out.println("SearchQuyetdinhByID: "+id);
        Vector<QuyetdinhDTO> vec = new Vector<QuyetdinhDTO>();
        String sql = "EXECUTE SearchQuyetdinhByID " + id;
        ResultSet rs = SqlDataAccessHelper.executequery(sql);
        try {
            while (rs.next()) {
                QuyetdinhDTO quyetdinh = new QuyetdinhDTO();
                quyetdinh.setQuyetdinhID(Integer.valueOf(rs.getString("ID")));
                quyetdinh.setMaSV(rs.getString("Mã Sinh Viên"));
                quyetdinh.setNgaybd(rs.getString("Ngày Bắt Đầu"));
                quyetdinh.setNgaykt(rs.getString("Ngày Kết Thúc"));
                quyetdinh.setYeucau(rs.getString("Yêu Cầu"));
                quyetdinh.setTentruong(rs.getString("Tên Trường"));
                quyetdinh.setTenkhoa(rs.getString("Tên Khoa"));
                quyetdinh.setTenlop(rs.getString("Tên Lớp"));
                quyetdinh.setLydo(rs.getString("Lý Do"));
                quyetdinh.setTinhtrang(rs.getString("Tình Trạng"));
                vec.add(quyetdinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyetdinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //BACH
    public static int UpdateTinhTrangSinhvien(String maSV, String tinhtrang) {
        String sql = "EXECUTE UpdateTinhTrangSinhvien " + "'" + maSV + "'" + "," + "N'" + tinhtrang + "'";
        return SqlDataAccessHelper.execute__query(sql);
    }

    public static int InsertQuyetDinhByAdmin(String maSV, String ngaybd, String ngaykt, String yeucau, String lydo) {
        System.out.println("Ly do : " + lydo);
        String sql = "EXECUTE InsertQuyetDinhByAdmin " + "'" + maSV + "'" + "," + "N'" + ngaybd + "'" + "," + "N'" + ngaykt + "'" + "," + "N'" + yeucau + "'" + "," + "N'" + lydo + "'";
        return SqlDataAccessHelper.execute__query(sql);
    }

    //Update tinh trang khong chap nhan
    public static int UpdateQuyetDinhKhongChapNhan(int maQD) {
        System.out.println("UpdateQuyetDinhKhongChapNhan====: "+maQD);
        String sql = "EXECUTE UpdateQuyetDinhKhongChapNhan " + maQD;
        return SqlDataAccessHelper.execute__query(sql);
    }

    //Update tinh trang chap nhan
    public static int UpdateTinhTrangQuyetDinh(int maQD) {
        System.out.println("UpdateTinhTrangQuyetDinh: " + maQD);
        String sql = "EXECUTE UpdateTinhTrangQuyetDinh " + maQD;
        return SqlDataAccessHelper.execute__query(sql);
    }

    public static int InsertQuyetDinhChuyenLop(String maSV, String yeucau, String lydo, String tentruong, String tenkhoa, String tenlop) {
        System.out.println("truong: " + tentruong + " khoa: " + tenkhoa + " ten lop: " + tenlop);
        String sql = "EXECUTE InsertQuyetDinhChuyenLop " + "'" + maSV + "'" + "," + "N'" + yeucau + "'" + "," + "N'" + lydo + "'" + "," + "N'" + tentruong + "'" + "," + "N'" + tenkhoa + "'" + "," + "N'" + tenlop + "'";
        return SqlDataAccessHelper.execute__query(sql);
    }
}
