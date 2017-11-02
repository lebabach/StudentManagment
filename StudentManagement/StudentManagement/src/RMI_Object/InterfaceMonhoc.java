/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Object;

import dto.MonhocDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author bach
 */
public interface InterfaceMonhoc extends Remote {

    public int insertMonhoc(String TenKhoi, String TenMH, int HeSo) throws RemoteException;

    public int updateMonhoc(int maMH, String tenkhoi, String tenMH, int heso) throws RemoteException;

    public Vector<MonhocDTO> searchMonhocKhoiTenMH(String tenKhoi, String tenmh) throws RemoteException;

    public Vector<MonhocDTO> searchMonhocIDByKhoiID(int KhoiID) throws RemoteException;

    public int deleteMonhoc(int maMH) throws RemoteException;
}
