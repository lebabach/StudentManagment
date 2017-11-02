/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceImplement;

import RMI_Object.InterfaceDiem;
import bus.DiemBUS;
import dto.DiemDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author bach
 */
public class DiemImpl extends UnicastRemoteObject implements InterfaceDiem {

    public DiemImpl() throws RemoteException {
        super();
    }

    public Vector<DiemDTO> searchDiemByTenKhoiTenLopMonhocHocKyTenHS(String khoi, String tenlop, String monhoc, String hocky, String tenhs) throws RemoteException {
        return DiemBUS.searchDiemByTenKhoiTenLopMonhocHocKyTenHS(khoi, tenlop, monhoc, hocky, tenhs);
    }

    public int UpdateDiemHs(String TenKhoi, String Tenlop, String TenMH, String TenHocKy, String Ten, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) throws RemoteException {
        return DiemBUS.UpdateDiemHs(TenKhoi, Tenlop, TenMH, TenHocKy, Ten, DiemMieng, Diem15Lan1, Diem15Lan2, Diem1TietLan1, Diem1TietLan2, DiemThiCuoiKi);
    }

    public int InsertDiemHocSinh(int HocSinhID, int MonhocID, int HocKyID, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) throws RemoteException {
        return DiemBUS.InsertDiemHocSinh(HocSinhID, MonhocID, HocKyID, DiemMieng, Diem15Lan1, Diem15Lan2, Diem1TietLan1, Diem1TietLan2, DiemThiCuoiKi);
    }

    public int deleteDiemHs(String TenKhoi, String Tenlop, String TenMH, String TenHocKy, String Ten, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) throws RemoteException {
        return DiemBUS.deleteDiemHs(TenKhoi, Tenlop, TenMH, TenHocKy, Ten, DiemMieng, Diem15Lan1, Diem15Lan2, Diem1TietLan1, Diem1TietLan2, DiemThiCuoiKi);
    }
}
