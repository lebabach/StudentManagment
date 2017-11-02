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
public class SinhvienDTO implements Serializable{
    private String MaSV;
    private String pass;
    private String tentruong;
    private String tenkhoa;
    private String tenlop;
    private String tenSV;
    private String gioitinh;
    private String email;
    private String diachi;
    private String avatar;
    private String tinhtrang;
    private String makhoa;

    public String getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(String makhoa) {
        this.makhoa = makhoa;
    }
    /**
     * @return the MaSV
     */
    public String getMaSV() {
        return MaSV;
    }

    /**
     * @param MaSV the MaSV to set
     */
    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the tentruong
     */
    public String getTentruong() {
        return tentruong;
    }

    /**
     * @param tentruong the tentruong to set
     */
    public void setTentruong(String tentruong) {
        this.tentruong = tentruong;
    }

    /**
     * @return the tenkhoa
     */
    public String getTenkhoa() {
        return tenkhoa;
    }

    /**
     * @param tenkhoa the tenkhoa to set
     */
    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    /**
     * @return the tenlop
     */
    public String getTenlop() {
        return tenlop;
    }

    /**
     * @param tenlop the tenlop to set
     */
    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    /**
     * @return the tenSV
     */
    public String getTenSV() {
        return tenSV;
    }

    /**
     * @param tenSV the tenSV to set
     */
    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    /**
     * @return the gioitinh
     */
    public String getGioitinh() {
        return gioitinh;
    }

    /**
     * @param gioitinh the gioitinh to set
     */
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    /**
     * @return the diachi
     */
    public String getDiachi() {
        return diachi;
    }

    /**
     * @param diachi the diachi to set
     */
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the tinhtrang
     */
    public String getTinhtrang() {
        return tinhtrang;
    }

    /**
     * @param tinhtrang the tinhtrang to set
     */
    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
