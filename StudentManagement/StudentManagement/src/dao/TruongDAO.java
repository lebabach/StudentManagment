/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.TruongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.SqlDataAccessHelper;

/**
 *
 * @author PC
 */
public class TruongDAO {

   public static Vector<TruongDTO> TruongAll() {
        Vector<TruongDTO> vec = new Vector<TruongDTO>();
        String sql = "Select * from Truong";
        try {
            ResultSet rs = SqlDataAccessHelper.executequery(sql);
            while (rs.next()) {
                TruongDTO truong = new TruongDTO();
                truong.setMatruong(Integer.valueOf(rs.getString(1)));
                truong.setTentruong(rs.getString(2));
                vec.add(truong);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TruongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    public static Vector<TruongDTO> GetAllTruong_() {
        Vector<TruongDTO> vec = new Vector<TruongDTO>();
        try {            
            String query = "EXECUTE GetAllTruong";
            ResultSet rs = SqlDataAccessHelper.executequery(query);
            while (rs.next()) {
                TruongDTO truong = new TruongDTO();
                truong.setMatruong(Integer.valueOf(rs.getString(1)));
                truong.setTentruong(rs.getString(2));
                vec.add(truong);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TruongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Them moi truong
    public static int InsertTruong(String name){
        String query = "EXECUTE InsertTruong "+"N'"+name+"'";
        return SqlDataAccessHelper.execute__query(query);
    }

    //Cap nhat thong tin cho truong
    public static int UpdateTruong(int id, String name) {
        String query = "EXECUTE UpdateTruong " + id + "," + "N'" + name + "'";
        return SqlDataAccessHelper.execute__query(query);
    }

    //Xoa truong theo id
    public static int DeleteTruongByID(int id) {
        String query = "EXECUTE DeleteTruongByID " + id;
        return SqlDataAccessHelper.execute__query(query);
    }

    //Xoa truong theo name
    public static int DeleteTruongByName(String name) {
        String query = "EXECUTE DeleteTruongByName " + "N'" + name + "'";
        return SqlDataAccessHelper.execute__query(query);
    }

    //Tim kiem truong theo id
    public static Vector<TruongDTO> SearchTruongById(int id) {
        Vector<TruongDTO> vec = new Vector<TruongDTO>();
        try {
            String sql = "EXECUTE SearchTruongById " + id;
            ResultSet rs = SqlDataAccessHelper.executequery(sql);
            while (rs.next()) {
                TruongDTO truong = new TruongDTO();
                truong.setMatruong(Integer.valueOf(rs.getString(1)));
                truong.setTentruong(rs.getString(2));
                vec.add(truong);
            }
        } //Tim kiem truong theo name
        catch (SQLException ex) {
            Logger.getLogger(TruongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    //Tim kiem truong theo name
    public static Vector<TruongDTO> SearchTruongByName(String name) {
        Vector<TruongDTO> vec = new Vector<TruongDTO>();;
        try {
            String sql = "EXECUTE SearchTruongByName " + "N'"+name+"'";
            ResultSet rs = SqlDataAccessHelper.executequery(sql);
            while (rs.next()) {
                TruongDTO truong = new TruongDTO();
                truong.setMatruong(Integer.valueOf(rs.getString(1)));
                truong.setTentruong(rs.getString(2));
                vec.add(truong);
            }
        } //Tim kiem truong theo name
        catch (SQLException ex) {
            Logger.getLogger(TruongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }
}
