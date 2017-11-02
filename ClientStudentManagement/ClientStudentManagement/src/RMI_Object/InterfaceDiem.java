/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Object;

import dto.DiemDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author bach
 */
public interface InterfaceDiem extends Remote {
    //Phuong thuc de them moi diem cho sinh vien

    public  int InsertDiemHocSinh(int HocSinhID, int MonhocID, int HocKyID, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) throws RemoteException;
    //Phuong thuc de chinh sua diem cho sinh vien

    public int UpdateDiemHs(String TenKhoi, String Tenlop, String TenMH, String TenHocKy, String Ten, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) throws RemoteException;
    //Phuong thuc xoa cot diem cua sinh vien

    public Vector<DiemDTO> searchDiemByTenKhoiTenLopMonhocHocKyTenHS(String khoi, String tenlop, String monhoc, String hocky, String tenhs) throws RemoteException;

    public int deleteDiemHs(String TenKhoi, String Tenlop, String TenMH, String TenHocKy, String Ten, float DiemMieng, float Diem15Lan1, float Diem15Lan2, float Diem1TietLan1, float Diem1TietLan2, float DiemThiCuoiKi) throws RemoteException;
}
