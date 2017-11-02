/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bus;

import dao.HocKyDAO;
import dto.HocKyDTO;
import java.util.Vector;

/**
 *
 * @author PC
 */
public class HockyBUS {
    // Phuong thuc nay de lay hoc ky dua vao truong
    public static Vector<HocKyDTO> GetHocKyByTruong(String tentruong){
        return HocKyDAO.GetHocKyByTruong(tentruong);
    }
}
