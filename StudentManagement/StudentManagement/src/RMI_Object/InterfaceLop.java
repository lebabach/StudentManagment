/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Object;

import dto.LopDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author bach
 */
public interface InterfaceLop extends Remote {

    public Vector<LopDTO> searchLopByTenKhoiAndTenLop(String tenKhoi, String tenLop) throws RemoteException;
    //Phuong thuc tim kiem khoahoc by MaSV

    public int UpdateLop(int LopID, String TenKhoi, String Tenlop, int siso, String NamHoc) throws RemoteException;

    public int insertLop(String TenKhoi, String Tenlop, String NamHoc) throws RemoteException;

    public  int deleteLop(int LopID)throws RemoteException;
}
