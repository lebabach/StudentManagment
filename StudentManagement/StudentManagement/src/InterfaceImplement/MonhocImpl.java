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
public class MonhocImpl extends UnicastRemoteObject implements InterfaceMonhoc {

    public MonhocImpl() throws RemoteException {
        super();
    }

    public Vector<MonhocDTO> searchMonhocKhoiTenMH(String tenKhoi, String tenmh) throws RemoteException {
        return MonhocBUS.searchMonhocKhoiTenMH(tenKhoi, tenmh);
    }

    public Vector<MonhocDTO> searchMonhocIDByKhoiID(int KhoiID) throws RemoteException {
        return MonhocBUS.searchMonhocIDByKhoiID(KhoiID);
    }

    public int updateMonhoc(int maMH, String tenkhoi, String tenMH, int heso) throws RemoteException {
        return MonhocBUS.updateMonhoc(maMH, tenkhoi, tenMH, heso);
    }

    public int insertMonhoc(String TenKhoi, String TenMH, int HeSo) throws RemoteException {
        return MonhocBUS.insertMonhoc(TenKhoi, TenMH, HeSo);
    }

    public int deleteMonhoc(int maMH) throws RemoteException {
        return MonhocBUS.deleteMonhoc(maMH);
    }
}
