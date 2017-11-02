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
public class AdministratorDTO implements Serializable {

    private String AdministratorID;
    private String UserName;
    private String pass;

    public String getAdministratorID() {
        return AdministratorID;
    }

    public void setAdministratorID(String AdministratorID) {
        this.AdministratorID = AdministratorID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
