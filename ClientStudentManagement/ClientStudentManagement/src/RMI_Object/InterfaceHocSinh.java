/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Object;

import dto.HocSinhDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public interface InterfaceHocSinh extends Remote {

    public Vector<HocSinhDTO> searchHocSinhByTenKhoiTenLopTenHocSinh(String tenKhoi, String tenLop, String tenHs) throws RemoteException;

    public int UpdateHocSinh(int HocSinhID,String tenKhoi, String tenlop, String tenhs, String gioitinh, String diachi, String sodth, String avatar) throws RemoteException;

    public int procInsertHocSinh(String tenKhoi, String tenlop, String tenhs, String gioitinh, String diachi, String sodth, String avatar) throws RemoteException;

    public  Vector<HocSinhDTO> searchHocSinhByHocSinhID(int HocSinhID)throws RemoteException;
}
