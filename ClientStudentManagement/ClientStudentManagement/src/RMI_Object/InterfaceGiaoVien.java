/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Object;

import dto.GiaoVienDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public interface InterfaceGiaoVien extends Remote {

    public Vector<GiaoVienDTO> searchGiaovienByKhoiAndMonhoc(String tenKhoi, String tenmh, String tengv) throws RemoteException;

    public int updateGiaoVien(int gvid, String tenmh, String tengv, String gioitinh, String diachi, String sodth, String avatar) throws RemoteException;

    public int insertGiaoVien(String khoi,String TenMH, String TenGiaoVien, String gioitinh, String diachi, String sodth, String avatar) throws RemoteException;
}
