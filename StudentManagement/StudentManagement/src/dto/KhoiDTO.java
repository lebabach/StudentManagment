/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class KhoiDTO implements Serializable {

   int KhoiID;
   String TenKhoi;

    public int getKhoiID() {
        return KhoiID;
    }

    public void setKhoiID(int KhoiID) {
        this.KhoiID = KhoiID;
    }

    public String getTenKhoi() {
        return TenKhoi;
    }

    public void setTenKhoi(String TenKhoi) {
        this.TenKhoi = TenKhoi;
    }
  
}
