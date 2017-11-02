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
public class GiaoVienImpl extends UnicastRemoteObject implements InterfaceGiaoVien {

    public GiaoVienImpl() throws RemoteException {
        super();
    }

    public Vector<GiaoVienDTO> searchGiaovienByKhoiAndMonhoc(String tenKhoi, String tenmh, String tengv) throws RemoteException {
        return GiaoVienBUS.searchGiaovienByKhoiAndMonhoc(tenKhoi, tenmh, tengv);
    }

    public int updateGiaoVien(int gvid, String tenmh, String tengv, String gioitinh, String diachi, String sodth, String avatar) throws RemoteException {
        return GiaoVienBUS.updateGiaoVien(gvid, tenmh, tengv, gioitinh, diachi, sodth, avatar);
    }

    public int insertGiaoVien(String khoi, String TenMH, String TenGiaoVien, String gioitinh, String diachi, String sodth, String avatar) throws RemoteException {
        return GiaoVienBUS.insertGiaoVien(khoi, TenMH, TenGiaoVien, gioitinh, diachi, sodth, avatar);
    }
}
