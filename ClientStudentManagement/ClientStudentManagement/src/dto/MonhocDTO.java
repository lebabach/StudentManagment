/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class MonhocDTO implements Serializable {

    private int MonhocID;
    private String TenMH;
    private int HeSo;
    String TenKhoi;

    public String getTenKhoi() {
        return TenKhoi;
    }

    public void setTenKhoi(String TenKhoi) {
        this.TenKhoi = TenKhoi;
    }

    public int getHeSo() {
        return HeSo;
    }

    public void setHeSo(int HeSo) {
        this.HeSo = HeSo;
    }

    public int getMonhocID() {
        return MonhocID;
    }

    public void setMonhocID(int MonhocID) {
        this.MonhocID = MonhocID;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String TenMH) {
        this.TenMH = TenMH;
    }
}
