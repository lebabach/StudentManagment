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
public class LopDTO implements Serializable{
    private int LopID;
    private String TenKhoi;
    private String Tenlop;
    private int siso;
    private String NamHoc;

    public String getTenKhoi() {
        return TenKhoi;
    }

    public void setTenKhoi(String TenKhoi) {
        this.TenKhoi = TenKhoi;
    }

    
    public int getLopID() {
        return LopID;
    }

    public void setLopID(int LopID) {
        this.LopID = LopID;
    }

    public String getNamHoc() {
        return NamHoc;
    }

    public void setNamHoc(String NamHoc) {
        this.NamHoc = NamHoc;
    }

    public String getTenlop() {
        return Tenlop;
    }

    public void setTenlop(String Tenlop) {
        this.Tenlop = Tenlop;
    }

    public int getSiso() {
        return siso;
    }

    public void setSiso(int siso) {
        this.siso = siso;
    }

    /**
     * @return the malop
     */
   
}
