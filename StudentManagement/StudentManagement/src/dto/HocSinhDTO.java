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
public class HocSinhDTO implements Serializable {

    int HocSinhID;
    String Tenlop;
    String Ten;
    String TenKhoi;
    String GioiTinh;
    String Diachi;
    String SoDienThoai;
    String Avatar;
    int KhoiID;

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

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public int getHocSinhID() {
        return HocSinhID;
    }

    public void setHocSinhID(int HocSinhID) {
        this.HocSinhID = HocSinhID;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getTenlop() {
        return Tenlop;
    }

    public void setTenlop(String Tenlop) {
        this.Tenlop = Tenlop;
    }
}
