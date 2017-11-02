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
public class TruongDTO implements Serializable{
    private int matruong;
    private String tentruong;

    /**
     * @return the matruong
     */
    public int getMatruong() {
        return matruong;
    }

    /**
     * @param matruong the matruong to set
     */
    public void setMatruong(int matruong) {
        this.matruong = matruong;
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
}
