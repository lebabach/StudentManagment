/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Object;

import dto.QuyetdinhDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author bach
 */
public interface InterfaceQuyetdinh extends Remote {

    public int InsertQuyetdinh(String maSV, String yeucau, String lydo, String tentruong, String tenkhoa, String tenlop,String ngaykt) throws RemoteException;

    //Phuong thuc nay de chen quyet dinh bao luu cua sinh vien vao CSDL
    public int InsertQuyetdinhBaoLuu(String maSV, String ngaykt, String yeucau, String lydo) throws RemoteException;

    //Phuong thuc nay de chen quyet dinh khac bao luu cua sinh vien vao CSDL
    public int InsertQuyetdinhPhuckhao(String maSV, String yeucau, String monhoc, String lydo, String tentruong, String tenkhoa, String tenlop) throws RemoteException;

    //Phuong thuc tim kiem quyet dinh theo truong,khoa,lop,yeucau,sinhvien
    public Vector<QuyetdinhDTO> SearchQuyetdinh(String truong, String khoa, String lop, String yeucau, String svien) throws RemoteException;

    //Phuong thuc lay tat ca cac quyet dinh
    public Vector<QuyetdinhDTO> GetAllQuyetDinh() throws RemoteException;

    //Phuong thuc tim kiem quyet dinh theo truong
    public Vector<QuyetdinhDTO> SearchQuyetdinhByTruong(String tentruong) throws RemoteException;

    //Phuong thuc tim kiem quyet dinh theo yeu cau
    public Vector<QuyetdinhDTO> SearchQuyetdinhByYeucau(String yeucau) throws RemoteException;

    //Phuong thuc tim kiem quyet dinh theo truong va yeu cau
    public Vector<QuyetdinhDTO> SearchQuyetdinhByTruongYeucau(String tentruong, String yeucau) throws RemoteException;

    //Phuong thuc lay tat ca cac quyet dinh phuc khao
    public Vector<QuyetdinhDTO> GetAllQuyetdinhPhucKhao() throws RemoteException;

    //Phuong thuc lay tat ca cac quyet dinh phuc khao theo truong
    public Vector<QuyetdinhDTO> SearchQuyetdinhPhucKhaoByTruong(String tentruong) throws RemoteException;

    //Phuong thuc lay tat ca cac quyet dinh phuc khao theo yeu cau
    public Vector<QuyetdinhDTO> SearchQuyetdinhPhucKhaoByYeucau(String yeucau) throws RemoteException;

    //Phuong thuc lay tat ca cac quyet dinh phuc khao theo yeu cau
    public Vector<QuyetdinhDTO> SearchQuyetdinhPhucKhaoByTruongYeucau(String tentruong, String yeucau) throws RemoteException;

    //Phương thức lấy quyết định theo QuyetdinhID
    public Vector<QuyetdinhDTO> SearchQuyetdinhByID(int id) throws RemoteException;

    //BACH
    public int UpdateTinhTrangSinhvien(String maSV, String tinhtrang) throws RemoteException;

    public int InsertQuyetDinhByAdmin(String maSV, String ngaybd, String ngaykt, String yeucau, String lydo) throws RemoteException;

    //Update tinh trang khong chap nhan
    public int UpdateQuyetDinhKhongChapNhan(int maQD) throws RemoteException ;

    //Update tinh trang chap nhan
    public int UpdateTinhTrangQuyetDinh(int maQD) throws RemoteException;

    public int InsertQuyetDinhChuyenLop(String maSV, String yeucau, String lydo, String tentruong, String tenkhoa, String tenlop)  throws RemoteException;
}
