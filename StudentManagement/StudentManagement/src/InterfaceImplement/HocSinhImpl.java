/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceImplement;

import RMI_Object.*;
import bus.*;
import dto.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class HocSinhImpl extends UnicastRemoteObject implements InterfaceHocSinh {

    public HocSinhImpl() throws RemoteException {
        super();
    }

    public Vector<HocSinhDTO> searchHocSinhByTenKhoiTenLopTenHocSinh(String tenKhoi, String tenLop, String tenHs) throws RemoteException {
        System.out.println("vao duoc HocSinhImpl==========");
        return HocSinhBUS.searchHocSinhByTenKhoiTenLopTenHocSinh(tenKhoi, tenLop, tenHs);
    }

    public int UpdateHocSinh(int HocSinhID,String tenKhoi, String tenlop, String tenhs, String gioitinh, String diachi, String sodth, String avatar) throws RemoteException {
        return HocSinhBUS.UpdateHocSinh(HocSinhID,tenKhoi, tenlop, tenhs, gioitinh, diachi, sodth, avatar);
    }

    public int procInsertHocSinh(String tenKhoi, String tenlop, String tenhs, String gioitinh, String diachi, String sodth, String avatar) throws RemoteException {
        return HocSinhBUS.procInsertHocSinh(tenKhoi, tenlop, tenhs, gioitinh, diachi, sodth, avatar);
    }

    public Vector<HocSinhDTO> searchHocSinhByHocSinhID(int HocSinhID) throws RemoteException {
        return HocSinhBUS.searchHocSinhByHocSinhID(HocSinhID);
    }
}
