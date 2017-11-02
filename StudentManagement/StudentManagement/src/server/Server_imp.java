package server;

import InterfaceImplement.*;
import RMI_Object.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.sql.Connection;
import java.sql.Statement;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author dell
 */
public class Server_imp {
    //Connect database with user and pass, Execute listen on port 1099s

    public static void Start(String username, String pass) throws RemoteException, AlreadyBoundException, MalformedURLException {
        Statement st = null;
        Connection cn = null;
        try {
            LocateRegistry.createRegistry(1099);
            InterfaceAdministrator Administrator = new AdministratorImpl();
            Naming.rebind("Administrator", Administrator);

            InterfaceDiem Diem = (InterfaceDiem) new DiemImpl();
            Naming.rebind("Diem", Diem);

            InterfaceGiaoVien GiaoVien = (InterfaceGiaoVien) new GiaoVienImpl();
            Naming.rebind("GiaoVien", GiaoVien);

            InterfaceHocSinh hocSinh = (InterfaceHocSinh) new HocSinhImpl();
            Naming.rebind("HocSinh", hocSinh);

            InterfaceHocky Hocky = (InterfaceHocky) new HockyImpl();
            Naming.rebind("HocKy", Hocky);

            InterfaceLop Lop = (InterfaceLop) new LopImpl();
            Naming.rebind("Lop", Lop);

            InterfaceMonhoc Monhoc = (InterfaceMonhoc) new MonhocImpl();
            Naming.rebind("Monhoc", Monhoc);

            System.out.println("Ready  ");
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }
}
