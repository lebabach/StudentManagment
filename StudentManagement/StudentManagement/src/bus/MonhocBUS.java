/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dao.MonhocDAO;
import dto.MonhocDTO;
import java.util.Vector;

/**
 *
 * @author PC
 */
public class MonhocBUS {
    //Phuong thuc de them moi mon hoc vao CSDL

    public static int insertMonhoc(String TenKhoi, String TenMH, int HeSo) {
        return MonhocDAO.insertMonhoc(TenKhoi, TenMH, HeSo);
    }

    //Phuong thuc de cap nhat thong tin cho mon hoc
    public static int updateMonhoc(int maMH, String tenkhoi, String tenMH, int heso) {
        return MonhocDAO.updateMonhoc(maMH, tenkhoi, tenMH, heso);
    }

    public static Vector<MonhocDTO> searchMonhocKhoiTenMH(String tenKhoi, String tenmh) {
        return MonhocDAO.searchMonhocKhoiTenMH(tenKhoi, tenmh);
    }

    public static Vector<MonhocDTO> searchMonhocIDByKhoiID(int KhoiID) {
        return MonhocDAO.searchMonhocIDByKhoiID(KhoiID);

    }
    public static int deleteMonhoc(int maMH) {
        return MonhocDAO.deleteMonhoc(maMH);
    }
}
