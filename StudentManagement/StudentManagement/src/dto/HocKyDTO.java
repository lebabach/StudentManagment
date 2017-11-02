/*Stirng
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class HocKyDTO implements Serializable {

    int HocKyID;
    String TenHocKy;

    public int getHocKyID() {
        return HocKyID;
    }

    public void setHocKyID(int HocKyID) {
        this.HocKyID = HocKyID;
    }

    public String getTenHocKy() {
        return TenHocKy;
    }

    public void setTenHocKy(String TenHocKy) {
        this.TenHocKy = TenHocKy;
    }
}
