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
 * @author bach
 */
public class LopImpl extends UnicastRemoteObject implements InterfaceLop {

    public LopImpl() throws RemoteException {
        super();
    }

    public Vector<LopDTO> searchLopByTenKhoiAndTenLop(String tenKhoi, String tenLop) throws RemoteException {
        return LopBUS.searchLopByTenKhoiAndTenLop(tenKhoi, tenLop);
    }

    public int UpdateLop(int LopID, String TenKhoi, String Tenlop, int siso, String NamHoc) throws RemoteException {
        return LopBUS.UpdateLop(LopID, TenKhoi, Tenlop, siso, NamHoc);
    }

    public int insertLop(String TenKhoi, String Tenlop, String NamHoc) throws RemoteException {
        return LopBUS.insertLop(TenKhoi, Tenlop, NamHoc);
    }

    public int deleteLop(int LopID) throws RemoteException {
        return LopBUS.deleteLop(LopID);
    }
}
