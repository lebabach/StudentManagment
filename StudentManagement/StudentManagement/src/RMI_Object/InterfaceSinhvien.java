/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Object;

import dto.SinhvienDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author bach
 */
public interface InterfaceSinhvien extends Remote {

    public int CheckSinhvienLogin(String MaSV, String pass) throws RemoteException;
    //Phuong thuc nay dung de lay tat ca cac sinh vien tu CSDL

    public Vector<SinhvienDTO> GetAllSinhvien() throws RemoteException;
    //Phuong thuc them moi sinh vien

    public int Insert_Sinhvien(String pass, String tenSV, String tentruong, String tenkhoa, String tenlop, String gioitinh, String email, String diachi, String avatar) throws RemoteException;
    //Phuong thuc nay de update thong tin cho sinh vien

    public int UpdateSinhvien(String MaSV, String pass, String ten, String gioitinh, String email, String diachi) throws RemoteException;

    //Phuong thuc nay de update thong tin cho sinh vien boi Admin
    public int UpdateSinhvienByAdmin(String MaSV, String pass, String ten, String gioitinh, String email, String diachi, String avatar) throws RemoteException;

    //Phuong thuc dung de tim kiem sinh vien theo MaSV
    public Vector<SinhvienDTO> SearchSinhvienByID(String MaSV) throws RemoteException;

    //Phuong thuc dung de tim kiem sinh vien theo TenSV
    public Vector<SinhvienDTO> SearchSinhvienByIDOrTen(String name) throws RemoteException;

    //Phuong thuc tim kiem sinh vien theo Truong
    public Vector<SinhvienDTO> SearchSinhvienByTruong(String tentruong) throws RemoteException;

    //Phuong thuc tim kiem sinh vien theo Truong,Ten/Ma SV
    public Vector<SinhvienDTO> SearchSinhvienByTruongTen(String tentruong, String value) throws RemoteException;

    //Phuong thuc dung de tim kiem sinh vien theo truong,khoa
    public Vector<SinhvienDTO> SearchSinhvienByTruongKhoa(String tentruong, String tenkhoa) throws RemoteException;

    //Phuong thuc dung de tim kiem sinh vien theo truong,khoa,ten/ma sinh vien
    public Vector<SinhvienDTO> SearchSinhvienByTruongKhoaTen(String tentruong, String tenkhoa, String value) throws RemoteException;
    //Phuong thuc dung de tim kiem sinh vien theo Truong,Lop,Khoa

    public Vector<SinhvienDTO> SearchSinhvienByTruongKhoaLopTen(String tentruong, String tenkhoa, String tenlop, String tenSV) throws RemoteException;
    //Phuong thuc dung de tim kiem sinh vien theo Truong,Lop,Khoa

    public Vector<SinhvienDTO> SearchSinhvienByTruongKhoaLop(String tentruong, String tenkhoa, String tenlop) throws RemoteException;
}
