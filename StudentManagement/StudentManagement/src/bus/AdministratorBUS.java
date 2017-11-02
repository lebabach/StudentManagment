/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bus;

import dao.AdministratorDAO;
import dto.AdministratorDTO;
import java.util.Vector;

/**
 *
 * @author PC
 */
public class AdministratorBUS {
    // Phuong thuc nay de kiem tra dang nhap cua Admin
    public static int CheckAdminLogin(String MaAdmin,String pass){
        return AdministratorDAO.CheckAdminLogin(MaAdmin, pass);
    }

    
}
