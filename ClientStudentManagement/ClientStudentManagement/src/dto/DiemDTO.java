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
public class DiemDTO implements Serializable {

    private int DiemID;
    private String TenHocSinh;
    private String TenLop;
    private String TenKhoi;
    private float DiemMieng;
    private float Diem15Lan1;
    private float Diem15Lan2;
    private float Diem1TietLan1;
    private float Diem1TietLan2;
    private float DiemThiCuoiKi;
    private String TenMH;
    private int HeSo;
    String TenHocKy;
    private float TBMon;
    int HocSinhID;

    public int getHocSinhID() {
        return HocSinhID;
    }

    public void setHocSinhID(int HocSinhID) {
        this.HocSinhID = HocSinhID;
    }

    public float getTBMon() {
        return TBMon;
    }

    public void setTBMon(float TBMon) {
        this.TBMon = TBMon;
    }

    public String getTenHocKy() {
        return TenHocKy;
    }

    public void setTenHocKy(String TenHocKy) {
        this.TenHocKy = TenHocKy;
    }

    public int getHeSo() {
        return HeSo;
    }

    public void setHeSo(int HeSo) {
        this.HeSo = HeSo;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String TenMH) {
        this.TenMH = TenMH;
    }

    public String getTenKhoi() {
        return TenKhoi;
    }

    public void setTenKhoi(String TenKhoi) {
        this.TenKhoi = TenKhoi;
    }

    public float getDiem15Lan1() {
        return Diem15Lan1;
    }

    public void setDiem15Lan1(float Diem15Lan1) {
        this.Diem15Lan1 = Diem15Lan1;
    }

    public float getDiem15Lan2() {
        return Diem15Lan2;
    }

    public void setDiem15Lan2(float Diem15Lan2) {
        this.Diem15Lan2 = Diem15Lan2;
    }

    public float getDiem1TietLan1() {
        return Diem1TietLan1;
    }

    public void setDiem1TietLan1(float Diem1TietLan1) {
        this.Diem1TietLan1 = Diem1TietLan1;
    }

    public float getDiem1TietLan2() {
        return Diem1TietLan2;
    }

    public void setDiem1TietLan2(float Diem1TietLan2) {
        this.Diem1TietLan2 = Diem1TietLan2;
    }

    public int getDiemID() {
        return DiemID;
    }

    public void setDiemID(int DiemID) {
        this.DiemID = DiemID;
    }

    public float getDiemMieng() {
        return DiemMieng;
    }

    public void setDiemMieng(float DiemMieng) {
        this.DiemMieng = DiemMieng;
    }

    public float getDiemThiCuoiKi() {
        return DiemThiCuoiKi;
    }

    public void setDiemThiCuoiKi(float DiemThiCuoiKi) {
        this.DiemThiCuoiKi = DiemThiCuoiKi;
    }

    public String getTenHocSinh() {
        return TenHocSinh;
    }

    public void setTenHocSinh(String TenHocSinh) {
        this.TenHocSinh = TenHocSinh;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }


}
