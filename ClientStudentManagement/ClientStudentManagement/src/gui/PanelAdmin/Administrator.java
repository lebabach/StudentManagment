/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Administrator.java
 *
 * Created on Mar 3, 2011, 8:49:27 AM
 */
package gui.PanelAdmin;

import RMI_Object.*;
import gui.LoginGUI;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import javax.swing.UIManager;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dto.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

//excel
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DecimalFormat;
import java.util.Iterator;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 *
 * @author PC
 */
public class Administrator extends javax.swing.JFrame {

    InterfaceGiaoVien GiaoVien;
    InterfaceHocSinh HocSinh;
    InterfaceHocky Hocky;
    InterfaceKhoi Khoi;
    InterfaceLop Lop;
    InterfaceMonhoc Monhoc;
    InterfaceDiem Diem;
    public String pathImages;
    public String nameImages;
    public Vector<HocSinhDTO> HS = null;
    public Vector<LopDTO> lophoc = null;
    public Vector<DiemDTO> diem = null;
    public Vector<MonhocDTO> monhoc = null;
    public Vector<GiaoVienDTO> gv = null;

    /** Creates new form Administrator */
    public Administrator(Registry registry) throws RemoteException, NotBoundException, MalformedURLException, FileNotFoundException {
        try {
            //UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            initComponents();
            this.setLocation(0, 0);
            System.out.println("truoc khi lookup");
            //GiaoVien = (InterfaceGiaoVien) registry.lookup("GiaoVien");
            Diem = (InterfaceDiem) registry.lookup("Diem");
            Lop = (InterfaceLop) registry.lookup("Lop");
            Hocky = (InterfaceHocky) registry.lookup("HocKy");
            Monhoc = (InterfaceMonhoc) registry.lookup("Monhoc");
            HocSinh = (InterfaceHocSinh) registry.lookup("HocSinh");
            GiaoVien = (InterfaceGiaoVien) registry.lookup("GiaoVien");
            //Hoc Sinh
            cboLopMoiHS.setEnabled(false);
            ButtonGroup g = new ButtonGroup();
            g.add(radNu);
            g.add(radNam);
            radNam.setSelected(true);
            GetAllHS("", "", "");
            //lớp
            getAllLop("", "");
            //môn học
            getAllMonhoc("", "");
            //điểm
            getAllDiem("", "", "", "", "");

            //giáo viên
            URL filename = getClass().getResource("");
            String file1 = filename.toString().substring(6, filename.toString().length());
            int i = file1.indexOf("build");
            String fileNew = file1.substring(0, i);
            pathImages = fileNew + "src/Images/Avatar/NoAvatarSV.jpg";
            nameImages = "NoAvatarSV.jpg";
            ButtonGroup g1 = new ButtonGroup();
            g1.add(radNuGV);
            g1.add(radNamGV);
            radNamGV.setSelected(true);
            getAllGV("", "", "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void GetAllHS(String khoi, String tenlop, String tenHS) throws RemoteException {
        try {
            HS = HocSinh.searchHocSinhByTenKhoiTenLopTenHocSinh(khoi, tenlop, tenHS);
            System.out.println("size: " + HS.size());
            displayTableHocSinh(HS);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    public void displayTableHocSinh(Vector<HocSinhDTO> HS) {
        String[][] data = {{"", "", "", "", "", ""}};
        String[] columnNames = {"Tên Khối", "Tên Lớp", "ID Học sinh", "Tên Học Sinh", "Giới Tính", "Địa Chỉ", "Số Điện Thoại"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        for (int i = 0; i < HS.size(); i++) {
            model.insertRow(i, new Object[]{HS.get(i).getTenKhoi(),
                        HS.get(i).getTenlop(),
                        HS.get(i).getHocSinhID(),
                        HS.get(i).getTen(),
                        HS.get(i).getGioiTinh(),
                        HS.get(i).getDiachi(),
                        HS.get(i).getSoDienThoai()});

        }
        tblResultHS.setModel(model);
    }

    public void GetLopByKhoi(String Khoi) {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tabPanelAdministrator = new javax.swing.JTabbedPane();
        QLHocSinh = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        txtSearchHS = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnThemmoiHS = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblResultHS = new javax.swing.JTable();
        btnTimkiemHS = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblMaSvSV = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenHS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDiachiHS = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        radNu = new javax.swing.JRadioButton();
        radNam = new javax.swing.JRadioButton();
        lblAvatarHS = new javax.swing.JLabel();
        btnCapnhatHS = new javax.swing.JButton();
        btnThemHS = new javax.swing.JButton();
        jLabelAvatar = new javax.swing.JLabel();
        txtAvatarHS = new javax.swing.JTextField();
        btnBrowseSV = new javax.swing.JButton();
        lblNotifyKhoiHS = new javax.swing.JLabel();
        lblNotifyLopHS = new javax.swing.JLabel();
        lblNotifyTenHS = new javax.swing.JLabel();
        lblNotifyDiachiHS = new javax.swing.JLabel();
        lblResultHS = new javax.swing.JLabel();
        cboTenKhoiMoiHS = new javax.swing.JComboBox();
        cboLopMoiHS = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        txtSoDthHS = new javax.swing.JTextField();
        lblNotifySoDthHS = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtHocsinhID = new javax.swing.JTextField();
        cboTenKhoiHS = new javax.swing.JComboBox();
        cboTenLopHS = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        btnPrintHS = new javax.swing.JButton();
        lblNotifyPrintHocSinh = new javax.swing.JLabel();
        QlLop = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtMaLop = new javax.swing.JTextField();
        txtTenMoiLop = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnCapnhatLop = new javax.swing.JButton();
        btnThemLop = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtNamHocLop = new javax.swing.JTextField();
        lblMakhoa = new javax.swing.JLabel();
        cboTenKhoiMoiLop = new javax.swing.JComboBox();
        lblKhoiLop = new javax.swing.JLabel();
        lblTenLop = new javax.swing.JLabel();
        lblNamHocLop = new javax.swing.JLabel();
        lblNotifyLop = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtsisolop = new javax.swing.JTextField();
        lblSisoLop = new javax.swing.JLabel();
        btnDelteLop = new javax.swing.JButton();
        btnThemmoiLop = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblResultLop = new javax.swing.JTable();
        cboTenKhoiLop = new javax.swing.JComboBox();
        cboTenLopLop = new javax.swing.JComboBox();
        QLMonHoc = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblResultMonHoc = new javax.swing.JTable();
        txtSearchMonHoc = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        lblMaMonHoc = new javax.swing.JLabel();
        txtMaMH = new javax.swing.JTextField();
        txtTenMH = new javax.swing.JTextField();
        lblTenMH = new javax.swing.JLabel();
        btnThemMonHoc = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtHeSoMH = new javax.swing.JTextField();
        btnupdateMH = new javax.swing.JButton();
        lblNotifyMaMH = new javax.swing.JLabel();
        lblNotifyTenMH = new javax.swing.JLabel();
        lblNotifyHeSoMH = new javax.swing.JLabel();
        lblNotifyResultMH = new javax.swing.JLabel();
        lblMaMonHoc1 = new javax.swing.JLabel();
        cboKhoimoiMonhoc = new javax.swing.JComboBox();
        lblNotifykhoiMonhoc = new javax.swing.JLabel();
        btnDeleteMH = new javax.swing.JButton();
        btnTimkiemMonHoc = new javax.swing.JButton();
        btnThemmoiMonHoc = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        lblSearchLop = new javax.swing.JLabel();
        cboTenKhoiMonhoc = new javax.swing.JComboBox();
        QLGiaoVien = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblResultGV = new javax.swing.JTable();
        txtSearchTenGV = new javax.swing.JTextField();
        btnTimkiemTenGV = new javax.swing.JButton();
        cboTenKhoiGV = new javax.swing.JComboBox();
        btnThemmoiGV = new javax.swing.JButton();
        cboMonhocGV = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblMaSvSV1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDiachiGV = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        radNuGV = new javax.swing.JRadioButton();
        radNamGV = new javax.swing.JRadioButton();
        lblAvatarGV = new javax.swing.JLabel();
        btnCapnhatGV = new javax.swing.JButton();
        btnThemGV = new javax.swing.JButton();
        jLabelAvatar1 = new javax.swing.JLabel();
        txtAvatarGV = new javax.swing.JTextField();
        btnBrowseGV = new javax.swing.JButton();
        lblNotifyKhoiGV = new javax.swing.JLabel();
        lblNotifyDiachiGV = new javax.swing.JLabel();
        lblNotifyTenGV = new javax.swing.JLabel();
        cboTenKhoiMoiGV = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        txtTenGV = new javax.swing.JTextField();
        lblNotifydthGV = new javax.swing.JLabel();
        txtGiaoVienID = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtSoDthGV = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        lblNotifyResultGV = new javax.swing.JLabel();
        cboMonhocMoiGV = new javax.swing.JComboBox();
        lblNotifyMonhocGV = new javax.swing.JLabel();
        QLDiem = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        cboMonhocDiem = new javax.swing.JComboBox();
        cboTenLopDiem = new javax.swing.JComboBox();
        txtSearchHSDiem = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtDiem15pLan1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        btnCapnhatDiem = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtDiemMiengDiem = new javax.swing.JTextField();
        txtDiemCKDiem = new javax.swing.JTextField();
        btnDeleteDiem = new javax.swing.JButton();
        txtTenKhoiDiem = new javax.swing.JTextField();
        txtTenLopDiem = new javax.swing.JTextField();
        txtTenMHDiem = new javax.swing.JTextField();
        txtTenHocKyDiem = new javax.swing.JTextField();
        txtTenHSDiem = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtDiem1TietLan1 = new javax.swing.JTextField();
        txtDiem15pLan2 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtDiem1TietLan2 = new javax.swing.JTextField();
        lbnotifyResultDiem = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblResultDiem = new javax.swing.JTable();
        btnTimkiemDiem = new javax.swing.JButton();
        cboTenKhoiDiem = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        btnPrintDiem = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        lblSearchDiem = new javax.swing.JLabel();
        cboHocKyDiem = new javax.swing.JComboBox();
        lblPrintDiem = new javax.swing.JLabel();
        ImportExcel = new javax.swing.JButton();
        txtImportDiem = new javax.swing.JTextField();
        btnImportDiem = new javax.swing.JButton();
        lbNotifyImportExcel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        XepLoai = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jPanel16 = new javax.swing.JPanel();
        cboTenLopXepLoai = new javax.swing.JComboBox();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblXepLoai = new javax.swing.JTable();
        cboTenKhoiXeploai = new javax.swing.JComboBox();
        jLabel55 = new javax.swing.JLabel();
        btnPrintDiem1 = new javax.swing.JButton();
        lblPrintxeploai = new javax.swing.JLabel();
        lbNotifyImportExcel1 = new javax.swing.JLabel();
        lbnotifyXeploai = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrator");
        setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );

        tabPanelAdministrator.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabPanelAdministrator.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabPanelAdministrator.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabPanelAdministrator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabPanelAdministratorKeyPressed(evt);
            }
        });

        QLHocSinh.setAutoscrolls(true);

        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setInheritsPopupMenu(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(889, 750));
        jScrollPane1.setWheelScrollingEnabled(false);

        jPanel3.setAutoscrolls(true);
        jPanel3.setPreferredSize(new java.awt.Dimension(870, 785));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("QUẢN LÝ HỌC SINH");

        btnThemmoiHS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OKShield.png"))); // NOI18N
        btnThemmoiHS.setText("Thêm Mới Học Sinh");
        btnThemmoiHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemmoiHSActionPerformed(evt);
            }
        });

        tblResultHS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblResultHS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultHSMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblResultHS);

        btnTimkiemHS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search2.png"))); // NOI18N
        btnTimkiemHS.setText("Tìm Kiếm");
        btnTimkiemHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemHSActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 255))); // NOI18N

        lblMaSvSV.setText("Khối :");

        jLabel2.setText("Lớp :");

        jLabel4.setText("Tên Học Sinh :");

        jLabel5.setText("Giới Tính :");

        jLabel6.setText("Địa chỉ :");

        radNu.setText("Nữ");

        radNam.setText("Nam");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(radNam)
                .addGap(10, 10, 10)
                .addComponent(radNu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radNam)
                    .addComponent(radNu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblAvatarHS.setBorder(javax.swing.BorderFactory.createTitledBorder("Avatar"));

        btnCapnhatHS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/21.png"))); // NOI18N
        btnCapnhatHS.setText("Cập Nhật");
        btnCapnhatHS.setEnabled(false);
        btnCapnhatHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatHSActionPerformed(evt);
            }
        });

        btnThemHS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/45.png"))); // NOI18N
        btnThemHS.setText("Thêm Mới");
        btnThemHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHSActionPerformed(evt);
            }
        });

        jLabelAvatar.setText("Avatar :");

        txtAvatarHS.setEditable(false);
        txtAvatarHS.setEnabled(false);

        btnBrowseSV.setText("Browse");
        btnBrowseSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseSVActionPerformed(evt);
            }
        });

        lblNotifyKhoiHS.setForeground(new java.awt.Color(255, 0, 0));

        lblNotifyLopHS.setForeground(new java.awt.Color(255, 0, 0));

        lblNotifyTenHS.setForeground(new java.awt.Color(255, 0, 0));

        lblNotifyDiachiHS.setForeground(new java.awt.Color(255, 0, 0));

        lblResultHS.setForeground(new java.awt.Color(255, 0, 0));

        cboTenKhoiMoiHS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));
        cboTenKhoiMoiHS.setPreferredSize(new java.awt.Dimension(34, 20));
        cboTenKhoiMoiHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKhoiMoiHSActionPerformed(evt);
            }
        });

        cboLopMoiHS.setPreferredSize(new java.awt.Dimension(34, 20));
        cboLopMoiHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLopMoiHSActionPerformed(evt);
            }
        });

        jLabel19.setText("Số Điện Thoại: ");

        lblNotifySoDthHS.setForeground(new java.awt.Color(255, 0, 0));

        jLabel20.setText("ID Học sinh");

        txtHocsinhID.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(lblMaSvSV)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTenHS, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                            .addComponent(cboTenKhoiMoiHS, 0, 181, Short.MAX_VALUE)
                                            .addComponent(cboLopMoiHS, 0, 181, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNotifyTenHS, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNotifyKhoiHS, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNotifyLopHS, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabelAvatar))
                                        .addGap(78, 78, 78)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtAvatarHS)
                                            .addComponent(txtDiachiHS, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20))
                                        .addGap(46, 46, 46)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHocsinhID, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                            .addComponent(txtSoDthHS, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBrowseSV)
                                    .addComponent(lblNotifyDiachiHS, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNotifySoDthHS, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(59, 59, 59)
                        .addComponent(lblAvatarHS, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnThemHS)
                        .addGap(16, 16, 16)
                        .addComponent(btnCapnhatHS)
                        .addGap(18, 18, 18)
                        .addComponent(lblResultHS, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMaSvSV)
                            .addComponent(lblNotifyKhoiHS, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTenKhoiMoiHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(lblNotifyLopHS, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLopMoiHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNotifyTenHS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTenHS)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(txtHocsinhID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel19)
                            .addComponent(txtSoDthHS)
                            .addComponent(lblNotifySoDthHS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtDiachiHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNotifyDiachiHS, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAvatarHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAvatar)
                            .addComponent(btnBrowseSV))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCapnhatHS)
                                .addComponent(btnThemHS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblResultHS, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblAvatarHS, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cboTenKhoiHS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));
        cboTenKhoiHS.setPreferredSize(new java.awt.Dimension(34, 20));
        cboTenKhoiHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKhoiHSActionPerformed(evt);
            }
        });

        cboTenLopHS.setEnabled(false);
        cboTenLopHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenLopHSActionPerformed(evt);
            }
        });

        jLabel9.setText("Tên Học Sinh :");

        btnPrintHS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Printsmall.jpg"))); // NOI18N
        btnPrintHS.setText("Print");
        btnPrintHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintHSActionPerformed(evt);
            }
        });

        lblNotifyPrintHocSinh.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThemmoiHS)
                        .addGap(10, 10, 10)
                        .addComponent(btnPrintHS, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNotifyPrintHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(cboTenKhoiHS, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboTenLopHS, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearchHS, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimkiemHS, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jLabel3))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTenKhoiHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenLopHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiemHS)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemmoiHS)
                        .addComponent(btnPrintHS))
                    .addComponent(lblNotifyPrintHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout QLHocSinhLayout = new javax.swing.GroupLayout(QLHocSinh);
        QLHocSinh.setLayout(QLHocSinhLayout);
        QLHocSinhLayout.setHorizontalGroup(
            QLHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 852, Short.MAX_VALUE)
            .addGroup(QLHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE))
        );
        QLHocSinhLayout.setVerticalGroup(
            QLHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
            .addGroup(QLHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(QLHocSinhLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(83, Short.MAX_VALUE)))
        );

        tabPanelAdministrator.addTab("  Quản Lý Học Sinh                  ", new javax.swing.ImageIcon(getClass().getResource("/Images/Profile.png")), QLHocSinh); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 255))); // NOI18N

        jLabel12.setText("Khối :");

        txtMaLop.setEditable(false);

        jLabel13.setText("Tên Lớp :");

        btnCapnhatLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/21.png"))); // NOI18N
        btnCapnhatLop.setText("Cập Nhật");
        btnCapnhatLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatLopActionPerformed(evt);
            }
        });

        btnThemLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/45.png"))); // NOI18N
        btnThemLop.setText("Thêm Mới");
        btnThemLop.setEnabled(false);
        btnThemLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLopActionPerformed(evt);
            }
        });

        jLabel14.setText("Năm Học :");

        lblMakhoa.setText("Mã Lớp :");

        cboTenKhoiMoiLop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));

        lblKhoiLop.setForeground(new java.awt.Color(255, 0, 0));

        lblTenLop.setForeground(new java.awt.Color(255, 0, 0));

        lblNamHocLop.setForeground(new java.awt.Color(255, 0, 0));

        lblNotifyLop.setForeground(new java.awt.Color(255, 0, 0));

        jLabel16.setText("Sĩ số :");

        lblSisoLop.setForeground(new java.awt.Color(255, 0, 0));

        btnDelteLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/01.png"))); // NOI18N
        btnDelteLop.setText("Xóa Lớp");
        btnDelteLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelteLopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMakhoa)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtsisolop, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamHocLop, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenMoiLop, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboTenKhoiMoiLop, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaLop, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
                            .addComponent(jLabel16)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnThemLop)
                                .addGap(18, 18, 18)
                                .addComponent(btnCapnhatLop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelteLop)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNamHocLop, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSisoLop, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblKhoiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(158, 158, 158))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblNotifyLop, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMakhoa)
                            .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cboTenKhoiMoiLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblKhoiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtTenMoiLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTenLop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNamHocLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(lblNamHocLop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtsisolop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addComponent(lblSisoLop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblNotifyLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCapnhatLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelteLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnThemmoiLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OKShield.png"))); // NOI18N
        btnThemmoiLop.setText("Thêm Mới Lớp");
        btnThemmoiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemmoiLopActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("QUẢN LÝ LỚP");

        tblResultLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblResultLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultLopMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblResultLop);

        cboTenKhoiLop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));
        cboTenKhoiLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKhoiLopActionPerformed(evt);
            }
        });

        cboTenLopLop.setEnabled(false);
        cboTenLopLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenLopLopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(cboTenKhoiLop, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cboTenLopLop, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(326, 326, 326))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemmoiLop)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel15)
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTenKhoiLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenLopLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnThemmoiLop)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel8);

        javax.swing.GroupLayout QlLopLayout = new javax.swing.GroupLayout(QlLop);
        QlLop.setLayout(QlLopLayout);
        QlLopLayout.setHorizontalGroup(
            QlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
        );
        QlLopLayout.setVerticalGroup(
            QlLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
        );

        tabPanelAdministrator.addTab("  Quản lý Lớp                           ", new javax.swing.ImageIcon(getClass().getResource("/Images/schools.png")), QlLop); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("QUẢN LÝ MÔN HỌC");

        tblResultMonHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblResultMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultMonHocMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblResultMonHoc);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 255))); // NOI18N

        lblMaMonHoc.setText("Mã Môn Học :");

        txtMaMH.setEditable(false);

        lblTenMH.setText("Tên Môn Học :");

        btnThemMonHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/45.png"))); // NOI18N
        btnThemMonHoc.setText("Thêm Mới");
        btnThemMonHoc.setEnabled(false);
        btnThemMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonHocActionPerformed(evt);
            }
        });

        jLabel22.setText("Hệ Số :");

        btnupdateMH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/21.png"))); // NOI18N
        btnupdateMH.setText("Cập Nhật");
        btnupdateMH.setEnabled(false);
        btnupdateMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateMHActionPerformed(evt);
            }
        });

        lblNotifyResultMH.setForeground(new java.awt.Color(255, 0, 51));

        lblMaMonHoc1.setText("Khối :");

        cboKhoimoiMonhoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));

        btnDeleteMH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/01.png"))); // NOI18N
        btnDeleteMH.setText("Xóa Môn học");
        btnDeleteMH.setEnabled(false);
        btnDeleteMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnThemMonHoc)
                                .addGap(24, 24, 24)
                                .addComponent(btnupdateMH)
                                .addGap(29, 29, 29)
                                .addComponent(btnDeleteMH)
                                .addGap(27, 27, 27)
                                .addComponent(lblNotifyResultMH, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(lblTenMH)
                                        .addGap(52, 52, 52)
                                        .addComponent(txtTenMH))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addGap(86, 86, 86)
                                        .addComponent(txtHeSoMH))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(lblMaMonHoc)
                                        .addGap(56, 56, 56)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboKhoimoiMonhoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtMaMH))))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNotifykhoiMonhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNotifyMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNotifyTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNotifyHeSoMH, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lblMaMonHoc1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaMonHoc1)
                        .addComponent(cboKhoimoiMonhoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNotifykhoiMonhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaMonHoc)
                    .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotifyMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNotifyTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTenMH)
                        .addComponent(txtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHeSoMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(lblNotifyHeSoMH, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnupdateMH)
                        .addComponent(btnDeleteMH))
                    .addComponent(btnThemMonHoc)
                    .addComponent(lblNotifyResultMH, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        btnTimkiemMonHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search2.png"))); // NOI18N
        btnTimkiemMonHoc.setText("Tìm Kiếm");
        btnTimkiemMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemMonHocActionPerformed(evt);
            }
        });

        btnThemmoiMonHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OKShield.png"))); // NOI18N
        btnThemmoiMonHoc.setText("Thêm Mới Môn Học");
        btnThemmoiMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemmoiMonHocActionPerformed(evt);
            }
        });

        jLabel23.setText("Tên Môn Học :");

        lblSearchLop.setForeground(new java.awt.Color(255, 0, 51));

        cboTenKhoiMonhoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));
        cboTenKhoiMonhoc.setPreferredSize(new java.awt.Dimension(34, 20));
        cboTenKhoiMonhoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKhoiMonhocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemmoiMonHoc)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearchLop))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(178, 178, 178)
                            .addComponent(cboTenKhoiMonhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(jLabel23)
                            .addGap(17, 17, 17)
                            .addComponent(txtSearchMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnTimkiemMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addGap(266, 266, 266))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel17)
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtSearchMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiemMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboTenKhoiMonhoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblSearchLop, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThemmoiMonHoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        jScrollPane13.setViewportView(jPanel10);

        javax.swing.GroupLayout QLMonHocLayout = new javax.swing.GroupLayout(QLMonHoc);
        QLMonHoc.setLayout(QLMonHocLayout);
        QLMonHocLayout.setHorizontalGroup(
            QLMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
        );
        QLMonHocLayout.setVerticalGroup(
            QLMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
        );

        tabPanelAdministrator.addTab("  Quản lý Môn Học                              ", new javax.swing.ImageIcon(getClass().getResource("/Images/class.jpg")), QLMonHoc); // NOI18N

        jScrollPane12.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane12.setAutoscrolls(true);
        jScrollPane12.setHorizontalScrollBar(null);
        jScrollPane12.setInheritsPopupMenu(true);
        jScrollPane12.setWheelScrollingEnabled(false);

        jPanel12.setAutoscrolls(true);
        jPanel12.setFocusCycleRoot(true);
        jPanel12.setPreferredSize(new java.awt.Dimension(870, 837));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("QUẢN LÝ GIÁO VIÊN");

        tblResultGV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblResultGV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultGVMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblResultGV);

        btnTimkiemTenGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search2.png"))); // NOI18N
        btnTimkiemTenGV.setText("Tìm Kiếm");
        btnTimkiemTenGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemTenGVActionPerformed(evt);
            }
        });

        cboTenKhoiGV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));
        cboTenKhoiGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKhoiGVActionPerformed(evt);
            }
        });

        btnThemmoiGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OKShield.png"))); // NOI18N
        btnThemmoiGV.setText("Thêm Mới Giáo Viên");
        btnThemmoiGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemmoiGVActionPerformed(evt);
            }
        });

        cboMonhocGV.setEnabled(false);
        cboMonhocGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonhocGVActionPerformed(evt);
            }
        });

        jLabel30.setText("Tên Giáo Viên :");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 255))); // NOI18N

        lblMaSvSV1.setText("Khối :");

        jLabel8.setText("Tên Môn Học :");

        jLabel10.setText("Giới Tính :");

        jLabel11.setText("Địa chỉ :");

        radNuGV.setText("Nữ");

        radNamGV.setText("Nam");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(radNamGV)
                .addGap(10, 10, 10)
                .addComponent(radNuGV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radNamGV)
                    .addComponent(radNuGV))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblAvatarGV.setBorder(javax.swing.BorderFactory.createTitledBorder("Avatar"));

        btnCapnhatGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/21.png"))); // NOI18N
        btnCapnhatGV.setText("Cập Nhật");
        btnCapnhatGV.setEnabled(false);
        btnCapnhatGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatGVActionPerformed(evt);
            }
        });

        btnThemGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/45.png"))); // NOI18N
        btnThemGV.setText("Thêm Mới");
        btnThemGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGVActionPerformed(evt);
            }
        });

        jLabelAvatar1.setText("Avatar :");

        txtAvatarGV.setEditable(false);
        txtAvatarGV.setEnabled(false);

        btnBrowseGV.setText("Browse");
        btnBrowseGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseGVActionPerformed(evt);
            }
        });

        lblNotifyKhoiGV.setForeground(new java.awt.Color(255, 0, 0));

        lblNotifyDiachiGV.setForeground(new java.awt.Color(255, 0, 0));

        lblNotifyTenGV.setForeground(new java.awt.Color(255, 0, 0));

        cboTenKhoiMoiGV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));
        cboTenKhoiMoiGV.setPreferredSize(new java.awt.Dimension(34, 20));
        cboTenKhoiMoiGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKhoiMoiGVActionPerformed(evt);
            }
        });

        jLabel18.setText("Tên Giáo Viên :");

        lblNotifydthGV.setForeground(new java.awt.Color(255, 0, 0));

        txtGiaoVienID.setEnabled(false);

        jLabel25.setText("Giáo Viên ID :");

        jLabel26.setText("Số Điện Thoại: ");

        lblNotifyResultGV.setForeground(new java.awt.Color(255, 0, 0));

        cboMonhocMoiGV.setEnabled(false);
        cboMonhocMoiGV.setPreferredSize(new java.awt.Dimension(34, 20));
        cboMonhocMoiGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonhocMoiGVActionPerformed(evt);
            }
        });

        lblNotifyMonhocGV.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(lblMaSvSV1)
                                    .addGap(85, 85, 85)
                                    .addComponent(cboTenKhoiMoiGV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(43, 43, 43)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cboMonhocMoiGV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNotifyKhoiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNotifyMonhocGV, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNotifyTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel25))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtGiaoVienID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(txtSoDthGV, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblNotifydthGV, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabelAvatar1)
                            .addComponent(btnThemGV))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnCapnhatGV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNotifyResultGV, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtAvatarGV, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiachiGV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNotifyDiachiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBrowseGV))))))
                .addGap(42, 42, 42)
                .addComponent(lblAvatarGV, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMaSvSV1)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cboTenKhoiMoiGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNotifyKhoiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvatarGV, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(cboMonhocMoiGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNotifyMonhocGV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(txtTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNotifyTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtGiaoVienID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel10))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel26)
                                .addComponent(txtSoDthGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNotifydthGV, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNotifyDiachiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtDiachiGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAvatarGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAvatar1)
                            .addComponent(btnBrowseGV))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCapnhatGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThemGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblNotifyResultGV, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(291, 291, 291)
                                .addComponent(jLabel24))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(cboTenKhoiGV, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(cboMonhocGV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimkiemTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemmoiGV)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel24)
                .addGap(35, 35, 35)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTenKhoiGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txtSearchTenGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiemTenGV)
                    .addComponent(cboMonhocGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemmoiGV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(113, 113, 113))
        );

        jScrollPane12.setViewportView(jPanel12);

        javax.swing.GroupLayout QLGiaoVienLayout = new javax.swing.GroupLayout(QLGiaoVien);
        QLGiaoVien.setLayout(QLGiaoVienLayout);
        QLGiaoVienLayout.setHorizontalGroup(
            QLGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 852, Short.MAX_VALUE)
            .addGroup(QLGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE))
        );
        QLGiaoVienLayout.setVerticalGroup(
            QLGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
            .addGroup(QLGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE))
        );

        tabPanelAdministrator.addTab("  Quản Lý Giáo Viên                   ", new javax.swing.ImageIcon(getClass().getResource("/Images/About_.jpg")), QLGiaoVien); // NOI18N

        cboMonhocDiem.setEnabled(false);
        cboMonhocDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonhocDiemActionPerformed(evt);
            }
        });

        cboTenLopDiem.setEnabled(false);
        cboTenLopDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenLopDiemActionPerformed(evt);
            }
        });

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 153, 255))); // NOI18N

        jLabel31.setText("Điểm Miệng :");

        jLabel32.setText("Điểm 15' lần 1 :");

        btnCapnhatDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/21.png"))); // NOI18N
        btnCapnhatDiem.setText("Cập Nhật");
        btnCapnhatDiem.setEnabled(false);
        btnCapnhatDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatDiemActionPerformed(evt);
            }
        });

        jLabel33.setText("Điểm Cuối Kỳ :");

        jLabel34.setText("Khối :");

        jLabel35.setText("Lớp :");

        jLabel36.setText("Môn Học :");

        jLabel37.setText("Học Kỳ :");

        jLabel38.setText("Tên Học Sinh :");

        btnDeleteDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/01.png"))); // NOI18N
        btnDeleteDiem.setText("Delete");
        btnDeleteDiem.setEnabled(false);
        btnDeleteDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDiemActionPerformed(evt);
            }
        });

        txtTenKhoiDiem.setEnabled(false);

        txtTenLopDiem.setEnabled(false);

        txtTenMHDiem.setEnabled(false);

        txtTenHocKyDiem.setEnabled(false);

        txtTenHSDiem.setEnabled(false);

        jLabel42.setText("Điểm 1 tiết lần 1 :");

        jLabel43.setText("Lần 2 :");

        jLabel44.setText("Lần 2 :");

        lbnotifyResultDiem.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenHSDiem)
                            .addComponent(txtTenHocKyDiem)
                            .addComponent(txtTenMHDiem)
                            .addComponent(txtTenLopDiem)
                            .addComponent(txtTenKhoiDiem, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(btnCapnhatDiem)
                                .addGap(30, 30, 30)
                                .addComponent(btnDeleteDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                            .addGap(115, 115, 115)
                                            .addComponent(txtDiem1TietLan1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                                        .addComponent(jLabel42)
                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                            .addComponent(jLabel33)
                                            .addGap(45, 45, 45)
                                            .addComponent(txtDiemCKDiem)))
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel44)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDiem1TietLan2))
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                            .addComponent(jLabel31)
                                            .addGap(53, 53, 53)
                                            .addComponent(txtDiemMiengDiem))
                                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                            .addGap(115, 115, 115)
                                            .addComponent(txtDiem15pLan1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel43)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDiem15pLan2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(487, 487, 487)
                        .addComponent(lbnotifyResultDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34)
                            .addComponent(txtTenKhoiDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txtTenLopDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(txtTenMHDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtDiemMiengDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(txtDiem15pLan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiem15pLan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txtDiem1TietLan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiem1TietLan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtTenHocKyDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(txtDiemCKDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtTenHSDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapnhatDiem)
                    .addComponent(btnDeleteDiem))
                .addGap(18, 18, 18)
                .addComponent(lbnotifyResultDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tblResultDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblResultDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultDiemMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblResultDiem);

        btnTimkiemDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search2.png"))); // NOI18N
        btnTimkiemDiem.setText("Tìm Kiếm");
        btnTimkiemDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemDiemActionPerformed(evt);
            }
        });

        cboTenKhoiDiem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));
        cboTenKhoiDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKhoiDiemActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 0, 0));
        jLabel40.setText("QUẢN LÝ ĐIỂM HỌC SINH");

        btnPrintDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Printsmall.jpg"))); // NOI18N
        btnPrintDiem.setText("Print");
        btnPrintDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintDiemActionPerformed(evt);
            }
        });

        jLabel41.setText("Tên Học Sinh :");

        lblSearchDiem.setForeground(new java.awt.Color(255, 0, 0));

        cboHocKyDiem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Học Kỳ ---", "Học Kỳ 1", "Học Kỳ 2" }));
        cboHocKyDiem.setEnabled(false);
        cboHocKyDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHocKyDiemActionPerformed(evt);
            }
        });

        lblPrintDiem.setForeground(new java.awt.Color(255, 0, 0));

        ImportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/excel1.jpg"))); // NOI18N
        ImportExcel.setText("Import Excel");
        ImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportExcelActionPerformed(evt);
            }
        });

        txtImportDiem.setEnabled(false);

        btnImportDiem.setText("Browse");
        btnImportDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportDiemActionPerformed(evt);
            }
        });

        lbNotifyImportExcel.setForeground(new java.awt.Color(255, 0, 0));

        jLabel21.setText("Điểm excel");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchHSDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTimkiemDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSearchDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(cboTenKhoiDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addComponent(cboTenLopDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboMonhocDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboHocKyDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnPrintDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(txtImportDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnImportDiem)
                        .addGap(28, 28, 28)
                        .addComponent(ImportExcel))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPrintDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNotifyImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel40)
                .addGap(41, 41, 41)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTenKhoiDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenLopDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMonhocDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHocKyDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel41)
                        .addComponent(txtSearchHSDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimkiemDiem))
                    .addComponent(lblSearchDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnImportDiem)
                        .addComponent(ImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtImportDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addComponent(btnPrintDiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrintDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNotifyImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jScrollPane14.setViewportView(jPanel14);

        javax.swing.GroupLayout QLDiemLayout = new javax.swing.GroupLayout(QLDiem);
        QLDiem.setLayout(QLDiemLayout);
        QLDiemLayout.setHorizontalGroup(
            QLDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
        );
        QLDiemLayout.setVerticalGroup(
            QLDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
        );

        tabPanelAdministrator.addTab("  Quản Lý Điểm                         ", new javax.swing.ImageIcon(getClass().getResource("/Images/diem.jpg")), QLDiem); // NOI18N

        cboTenLopXepLoai.setEnabled(false);
        cboTenLopXepLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenLopXepLoaiActionPerformed(evt);
            }
        });

        tblXepLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblXepLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblXepLoaiMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblXepLoai);

        cboTenKhoiXeploai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " --- Chọn Khối ---", "Khối lớp 6", "Khối lớp 7", "Khối lớp 8", "Khối lớp 9" }));
        cboTenKhoiXeploai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKhoiXeploaiActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 0, 0));
        jLabel55.setText("XẾP LOẠI HỌC SINH");

        btnPrintDiem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Printsmall.jpg"))); // NOI18N
        btnPrintDiem1.setText("Tổng Kết");
        btnPrintDiem1.setEnabled(false);
        btnPrintDiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintDiem1ActionPerformed(evt);
            }
        });

        lblPrintxeploai.setForeground(new java.awt.Color(255, 0, 0));

        lbNotifyImportExcel1.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(599, 599, 599)
                        .addComponent(lbNotifyImportExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(214, 214, 214)
                                .addComponent(cboTenKhoiXeploai, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(cboTenLopXepLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbnotifyXeploai, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(btnPrintDiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(lblPrintxeploai, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jLabel55)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel55)
                .addGap(45, 45, 45)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTenLopXepLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenKhoiXeploai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnotifyXeploai, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPrintDiem1)
                    .addComponent(lblPrintxeploai, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(lbNotifyImportExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane15.setViewportView(jPanel16);

        javax.swing.GroupLayout XepLoaiLayout = new javax.swing.GroupLayout(XepLoai);
        XepLoai.setLayout(XepLoaiLayout);
        XepLoaiLayout.setHorizontalGroup(
            XepLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15)
        );
        XepLoaiLayout.setVerticalGroup(
            XepLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
        );

        tabPanelAdministrator.addTab("Xếp Loại                                         ", new javax.swing.ImageIcon(getClass().getResource("/Images/request (2).jpg")), XepLoai); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelAdministrator)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelAdministrator, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jMenu1.setText("File");

        jMenuItem1.setText("Help");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("LogOut");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void getLopByKhoi(String khoi) throws RemoteException {
        Vector<LopDTO> vec = Lop.searchLopByTenKhoiAndTenLop(khoi, "");
        cboTenLopHS.addItem("--- Chọn Tên Lớp ---");
        for (int i = 0; i < vec.size(); i++) {
            cboTenLopHS.addItem(vec.get(i).getTenlop());
        }
    }    public String GetGioiTinh() {
        String gioitinh = null;
        if (radNam.isSelected()) {
            gioitinh = "Nam";
        } else {
            gioitinh = "Nữ";
        }
        return gioitinh;
    }

    public void SetLblNotifyHS() {
        lblNotifyKhoiHS.setText("");
        lblNotifyTenHS.setText("");
        lblNotifyDiachiHS.setText("");
    }

    public String GetAvatar() {
        String avatar;
        if (txtAvatarHS.getText().equals("")) {
            avatar = "/Images/Avatar/NoAvatarSV.jpg";
        } else {
            avatar = txtAvatarHS.getText();
        }
        System.out.println("avatar===================: " + avatar);
        return avatar;
    }

    private void save(BufferedImage image, String fileimage) {
        URL filename = getClass().getResource("");
        String file1 = filename.toString().substring(6, filename.toString().length());
        int i = file1.indexOf("build");
        String fileNew = file1.substring(0, i);
        String path = fileNew + "src/Images/Avatar/" + fileimage;
        System.out.println("path===================: " + path);
        File file = new File(path);
        try {
            ImageIO.write(image, "jpg", file);  // ignore returned boolean
        } catch (IOException e) {
            //System.out.println("Write error for " + file.getPath()
            //        + ": " + e.getMessage());
        }
    }

    private static BufferedImage toBufferedImage(Image src) {
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB;  // other options
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }    public void searchHocsinh() {
        try {
//            if (txtSearchSV.getText().equals("")) {
//                lblNotifySearchSV.setText("Nhập tên/ID sinh viên để tìm kiếm !!!");
//                GetAllSinhvienSV();
//            } else {
            if (cboTenKhoiHS.getSelectedIndex() == 0) {
                GetAllHS("", "", txtSearchHS.getText());
            } else {
                if (cboTenLopHS.getSelectedIndex() == 0) {
                    GetAllHS(cboTenKhoiHS.getSelectedItem().toString(), "", txtSearchHS.getText());
                } else {
                    GetAllHS(cboTenKhoiHS.getSelectedItem().toString(), cboTenLopHS.getSelectedItem().toString(), txtSearchHS.getText());

                }
            }
            // }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }    public void SinhvienDetails(Vector<HocSinhDTO> vec) throws RemoteException {
        cboTenKhoiMoiHS.setSelectedItem(vec.get(0).getTenKhoi());
        //getLopByKhoiMoiHS(vec.get(0).getTenKhoi());
        cboLopMoiHS.setSelectedItem(vec.get(0).getTenlop());
        txtTenHS.setText(vec.get(0).getTen());
        if (vec.get(0).getGioiTinh().equals("Nam")) {
            radNam.setSelected(true);
            radNu.setSelected(false);
        } else {
            radNam.setSelected(false);
            radNu.setSelected(true);
        }
        txtDiachiHS.setText(vec.get(0).getDiachi());
        txtAvatarHS.setText(vec.get(0).getAvatar());
        txtSoDthHS.setText(vec.get(0).getSoDienThoai());
        System.out.println("truoc getHocSinhID: " + vec.get(0).getHocSinhID());
        txtHocsinhID.setText(vec.get(0).getHocSinhID() + "");
        URL filename = getClass().getResource("");
        String file1 = filename.toString().substring(6, filename.toString().length());
        int i = file1.indexOf("build");
        String fileNew = file1.substring(0, i) + "src" + vec.get(0).getAvatar();
        lblAvatarHS.setIcon(new javax.swing.ImageIcon(fileNew));//getClass().getResource(avatar) lblAvatarHS
    }

    public void getLopByKhoiMoiHS(String khoi) throws RemoteException {
        Vector<LopDTO> vec = Lop.searchLopByTenKhoiAndTenLop(khoi, "");
        for (int i = 0; i < vec.size(); i++) {
            cboLopMoiHS.addItem(vec.get(i).getTenlop());
        }
    }
    public void getLopByKhoiLop(String khoi) throws RemoteException {
        Vector<LopDTO> vec = Lop.searchLopByTenKhoiAndTenLop(khoi, "");
        cboTenLopLop.addItem("--- Chọn Tên Lớp ---");
        for (int i = 0; i < vec.size(); i++) {
            cboTenLopLop.addItem(vec.get(i).getTenlop());
        }
    }

    public void displayTableLop(Vector<LopDTO> lop) {
        String[][] data = {{"", "", "", "", ""}};
        String[] columnNames = {"Tên Khối", "Mã Lớp", "Tên Lớp", "Sĩ số", "Năm Học"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        for (int i = 0; i < lop.size(); i++) {
            model.insertRow(i, new Object[]{
                        lop.get(i).getTenKhoi(),
                        lop.get(i).getLopID(),
                        lop.get(i).getTenlop(),
                        lop.get(i).getSiso(),
                        lop.get(i).getNamHoc()
                    });

        }
        tblResultLop.setModel(model);
    }

    public void getAllLop(String khoi, String tenlop) throws RemoteException {
        try {
            lophoc = Lop.searchLopByTenKhoiAndTenLop(khoi, tenlop);
            displayTableLop(lophoc);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }    public void monhocDetails(Vector<MonhocDTO> vec) {
        cboKhoimoiMonhoc.setSelectedItem(vec.get(0).getTenKhoi());
        txtMaMH.setText(vec.get(0).getMonhocID() + "");
        txtTenMH.setText(vec.get(0).getTenMH());
        txtHeSoMH.setText(vec.get(0).getHeSo() + "");
    }    public void getAllMonhoc(String khoi, String tenmh) {
        try {

            monhoc = Monhoc.searchMonhocKhoiTenMH(khoi, tenmh);
            System.out.println("size: " + HS.size());
            displayTableMonhoc(monhoc);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    public void displayTableMonhoc(Vector<MonhocDTO> monhoc) {
        String[][] data = {{"", "", "", ""}};
        String[] columnNames = {"Tên Khối", "Mã Môn học", "Tên môn học", "Hệ số"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        for (int i = 0; i < monhoc.size(); i++) {
            model.insertRow(i, new Object[]{
                        monhoc.get(i).getTenKhoi(),
                        monhoc.get(i).getMonhocID(),
                        monhoc.get(i).getTenMH(),
                        monhoc.get(i).getHeSo()
                    });
        }
        tblResultMonHoc.setModel(model);
    }

    public void searchMonhocByKhoiAndMonhoc(String khoi, String tenmh) {
        try {
            if (cboTenKhoiMonhoc.getSelectedIndex() == 0) {
                getAllMonhoc("", txtSearchMonHoc.getText());
            } else {
                getAllMonhoc(cboTenKhoiMonhoc.getSelectedItem().toString(), txtSearchMonHoc.getText());
            }

        } catch (Exception e) {
        }
    }

    public void searchMonhoc() {
        if (cboTenKhoiMonhoc.getSelectedIndex() == 0) {
            searchMonhocByKhoiAndMonhoc("", txtSearchMonHoc.getText());
        } else {
            searchMonhocByKhoiAndMonhoc(cboTenKhoiMonhoc.getSelectedItem().toString(), txtSearchMonHoc.getText());

        }
    }    public void giaoVienDetails(Vector<GiaoVienDTO> vec) {
        cboTenKhoiMoiGV.setSelectedItem(vec.get(0).getTenKhoi());
        cboMonhocMoiGV.addItem(vec.get(0).getTenMH());
        cboMonhocMoiGV.setSelectedItem(vec.get(0).getTenMH());
        txtTenGV.setText(vec.get(0).getTenGiaoVien());
        if (vec.get(0).getGioitinh().equals("Nam")) {
            radNamGV.setSelected(true);
            radNuGV.setSelected(false);
        } else {
            radNamGV.setSelected(false);
            radNuGV.setSelected(true);
        }
        txtDiachiGV.setText(vec.get(0).getDiachi());
        txtAvatarGV.setText(vec.get(0).getAvatar());
        txtSoDthGV.setText(vec.get(0).getSoDienThoai());
        txtGiaoVienID.setText(vec.get(0).getGiaoVienID() + "");
        URL filename = getClass().getResource("");
        String file1 = filename.toString().substring(6, filename.toString().length());
        int i = file1.indexOf("build");
        String fileNew = file1.substring(0, i) + "src" + vec.get(0).getAvatar();
        lblAvatarGV.setIcon(new javax.swing.ImageIcon(fileNew));//getClass().getResource(avatar) lblAvatarHS
    }    public void searchGiaovien() {
        try {
            if (cboTenKhoiGV.getSelectedIndex() == 0) {
                getAllGV("", "", txtSearchTenGV.getText());
            } else {
                if (cboMonhocGV.getSelectedIndex() == 0) {
                    getAllGV(cboTenKhoiGV.getSelectedItem().toString(), "", txtSearchTenGV.getText());
                } else {
                    getAllGV(cboTenKhoiGV.getSelectedItem().toString(), cboMonhocGV.getSelectedItem().toString(), txtSearchTenGV.getText());

                }
            }
        } catch (Exception e) {
        }
    }    public void displayTableGiaoVien(Vector<GiaoVienDTO> gv) {
        String[][] data = {{"", "", "", "", "", ""}};
        String[] columnNames = {"Tên Khối", "Giáo Viên ID", "Tên Giáo viên", "Tên Môn học", "Giới Tính", "Địa Chỉ", "Số Điện Thoại"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        for (int i = 0; i < gv.size(); i++) {
            model.insertRow(i, new Object[]{
                        gv.get(i).getTenKhoi(),
                        gv.get(i).getGiaoVienID(),
                        gv.get(i).getTenGiaoVien(),
                        gv.get(i).getTenMH(),
                        gv.get(i).getGioitinh(),
                        gv.get(i).getDiachi(),
                        gv.get(i).getSoDienThoai()
                    });

        }
        tblResultGV.setModel(model);
    }

    public void getAllGV(String khoi, String mh, String tengv) throws RemoteException {
        try {
            System.out.println("getAllGV================== khoi: " + khoi + " monhoc: " + mh);
            gv = GiaoVien.searchGiaovienByKhoiAndMonhoc(khoi, mh, tengv);
            System.out.println("sizeGVVVVVVVVVVVVVVVVVVVVVVVVVVV: " + gv.size());
            displayTableGiaoVien(gv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getMonhocByKhoiGV(String khoi) throws RemoteException {
        Vector<MonhocDTO> vec = Monhoc.searchMonhocKhoiTenMH(khoi, "");
        cboMonhocGV.addItem("--- Chọn Tên Môn học ---");
        for (int i = 0; i < vec.size(); i++) {
            cboMonhocGV.addItem(vec.get(i).getTenMH());
        }
    }    public void searchMonhocdiem() {
        try {
            if (cboMonhocDiem.getSelectedIndex() == 0) {
                if (cboTenLopDiem.getSelectedIndex() == 0) {
                    if (cboHocKyDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", "", "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", "", cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                } else {
                    if (cboHocKyDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), "", "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), "", cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                }
            } else {
                if (cboTenLopDiem.getSelectedIndex() == 0) {
                    if (cboHocKyDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", cboMonhocDiem.getSelectedItem().toString(), "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", cboMonhocDiem.getSelectedItem().toString(), cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                } else {
                    if (cboHocKyDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), cboMonhocDiem.getSelectedItem().toString(), "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), cboMonhocDiem.getSelectedItem().toString(), cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    public void getMonhocByKhoiTenMH(String khoi, String tenmh) throws RemoteException {
        Vector<MonhocDTO> vec = Monhoc.searchMonhocKhoiTenMH(khoi, tenmh);
        cboMonhocDiem.addItem("--- Chọn Tên Môn học ---");
        for (int i = 0; i < vec.size(); i++) {
            System.out.println("monhoc: " + vec.get(i).getTenMH());
            cboMonhocDiem.addItem(vec.get(i).getTenMH());
        }
    }

    public void searchTenLopDiem() {
        try {
            if (cboTenLopDiem.getSelectedIndex() == 0) {
                if (cboMonhocDiem.getSelectedIndex() == 0) {
                    if (cboHocKyDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", "", "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", "", cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                } else {
                    if (cboHocKyDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", cboMonhocDiem.getSelectedItem().toString(), "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", cboMonhocDiem.getSelectedItem().toString(), cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                }
            } else {
                if (cboMonhocDiem.getSelectedIndex() == 0) {
                    if (cboHocKyDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), "", "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), "", cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                } else {
                    if (cboHocKyDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), cboMonhocDiem.getSelectedItem().toString(), "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), cboMonhocDiem.getSelectedItem().toString(), cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    public void diemDetails(Vector<DiemDTO> vec) {
        txtTenKhoiDiem.setText(vec.get(0).getTenKhoi());
        txtTenLopDiem.setText(vec.get(0).getTenLop());
        txtTenMHDiem.setText(vec.get(0).getTenMH());
        txtTenHocKyDiem.setText(vec.get(0).getTenHocKy());
        txtTenHSDiem.setText(vec.get(0).getTenHocSinh() + "");
        txtDiemMiengDiem.setText(vec.get(0).getDiemMieng() + "");
        txtDiem15pLan1.setText(vec.get(0).getDiem15Lan1() + "");
        txtDiem15pLan2.setText(vec.get(0).getDiem15Lan2() + "");
        txtDiem1TietLan1.setText(vec.get(0).getDiem1TietLan1() + "");
        txtDiem1TietLan2.setText(vec.get(0).getDiem1TietLan2() + "");
        txtDiemCKDiem.setText(vec.get(0).getDiemThiCuoiKi() + "");
    }    public void searchDiembyten() {
        try {
            if (cboTenKhoiDiem.getSelectedIndex() == 0) {
                getAllDiem("", "", "", "", txtSearchHSDiem.getText());
            } else {
                if (cboTenLopDiem.getSelectedIndex() == 0) {
                    if (cboMonhocDiem.getSelectedIndex() == 0) {
                        if (cboHocKyDiem.getSelectedIndex() == 0) {
                            getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", "", "", txtSearchHSDiem.getText());
                        } else {
                            getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", "", cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                        }
                    } else {
                        if (cboHocKyDiem.getSelectedIndex() == 0) {
                            getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", cboMonhocDiem.getSelectedItem().toString(), "", txtSearchHSDiem.getText());
                        } else {
                            getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", cboMonhocDiem.getSelectedItem().toString(), cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                        }
                    }
                } else {
                    if (cboMonhocDiem.getSelectedIndex() == 0) {
                        if (cboHocKyDiem.getSelectedIndex() == 0) {
                            getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), "", "", txtSearchHSDiem.getText());
                        } else {
                            getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), "", cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                        }
                    } else {
                        if (cboHocKyDiem.getSelectedIndex() == 0) {
                            getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), cboMonhocDiem.getSelectedItem().toString(), "", txtSearchHSDiem.getText());
                        } else {
                            getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), cboMonhocDiem.getSelectedItem().toString(), cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    public void displayTableDiem(Vector<DiemDTO> diem) {
        try {
            System.out.println("vao duoc hien thi bang displayTableDiem");
            String[][] data = {{"", "", "", "", "", "", "", "", "", "", ""}};
            String[] columnNames = {"Tên Khối", "Tên Lớp", "Tên Học Sinh", "Tên Môn Học", "Điểm Miệng", "Điểm 15' Lần 1", "Điểm 15' Lần 2", "Điểm 1 Tiết Lần 1", "Điểm 1 Tiết Lần 2", "Điểm thi cuối kỳ", "TB Môn", "Học Kỳ"};
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            for (int i = 0; i < diem.size(); i++) {
                model.insertRow(i, new Object[]{
                            diem.get(i).getTenKhoi(),
                            diem.get(i).getTenLop(),
                            diem.get(i).getTenHocSinh(),
                            diem.get(i).getTenMH(),
                            diem.get(i).getDiemMieng(),
                            diem.get(i).getDiem15Lan1(),
                            diem.get(i).getDiem15Lan2(),
                            diem.get(i).getDiem1TietLan1(),
                            diem.get(i).getDiem1TietLan2(),
                            diem.get(i).getDiemThiCuoiKi(),
                            diem.get(i).getTBMon(),
                            diem.get(i).getTenHocKy()
                        });

            }
            tblResultDiem.setModel(model);
        } catch (Exception e) {
        }

    }

    public void getAllDiem(String khoi, String tenlop, String monhoc, String hocky, String tenhs) throws RemoteException {
        try {
            diem = Diem.searchDiemByTenKhoiTenLopMonhocHocKyTenHS(khoi, tenlop, monhoc, hocky, tenhs);
            displayTableDiem(diem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getLopByKhoiDiem(String khoi) throws RemoteException {
        Vector<LopDTO> vec = Lop.searchLopByTenKhoiAndTenLop(khoi, "");
        cboTenLopDiem.addItem("--- Chọn Tên Lớp ---");
        for (int i = 0; i < vec.size(); i++) {
            cboTenLopDiem.addItem(vec.get(i).getTenlop());
        }
    }    public void searchDiemByhocky() {
        try {
            if (cboHocKyDiem.getSelectedIndex() == 0) {
                if (cboTenLopDiem.getSelectedIndex() == 0) {
                    if (cboMonhocDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", "", "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", cboMonhocDiem.getSelectedItem().toString(), "", txtSearchHSDiem.getText());
                    }
                } else {
                    if (cboMonhocDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), "", "", txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), cboMonhocDiem.getSelectedItem().toString(), "", txtSearchHSDiem.getText());
                    }
                }
            } else {
                if (cboTenLopDiem.getSelectedIndex() == 0) {
                    if (cboMonhocDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", "", cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", cboMonhocDiem.getSelectedItem().toString(), cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                } else {
                    if (cboMonhocDiem.getSelectedIndex() == 0) {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), "", cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    } else {
                        getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), cboMonhocDiem.getSelectedItem().toString(), cboHocKyDiem.getSelectedItem().toString(), txtSearchHSDiem.getText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void GetKhoaByTruongQD(String tentruong) throws RemoteException {
    }

    public void GetLopByTruongKhoaQD(String tentruong, String tenkhoa) throws RemoteException {
    }
    public String getGioiTinhGV() {
        String gioitinh = null;
        if (radNamGV.isSelected()) {
            gioitinh = "Nam";
        } else {
            gioitinh = "Nữ";
        }
        return gioitinh;
    }

    public String getAvatarGV() {
        String avatar;
        if (txtAvatarGV.getText().equals("")) {
            avatar = "/Images/Avatar/NoAvatarSV.jpg";
        } else {
            avatar = txtAvatarGV.getText();
        }
        return avatar;
    }
    public void displayTableXepLoai(Vector<DiemDTO> diem) {
        try {
            String[][] data = {{"", "", "", "", ""}};
            String[] columnNames = {"Tên Khối", "Tên Lớp", "Tên Học Sinh", "Điểm TB", "Học Kỳ"};
            DefaultTableModel model = new DefaultTableModel(data, columnNames);

            float dtb = 0;
            int tongheso = 0;
            int j, i, k = 0;
            for (i = 0; i < diem.size(); i++) {
                tongheso = 0;
                dtb = 0;
                for (j = i; j < diem.size(); j++) {
                    if (diem.get(i).getHocSinhID() == diem.get(j).getHocSinhID()) {
                        tongheso += diem.get(j).getHeSo();
                        dtb += diem.get(j).getTBMon() * diem.get(j).getHeSo();
                    } else {
                        break;
                    }
                }
                dtb = dtb / tongheso;
                DecimalFormat df = new DecimalFormat("0.0");
                String dtbhocky = df.format(dtb);
                String dtbnew = dtbhocky.replaceAll(",", ".");
                i = j - 1;
                model.insertRow(k, new Object[]{
                            diem.get(i).getTenKhoi(),
                            diem.get(i).getTenLop(),
                            diem.get(i).getTenHocSinh(),
                            Float.valueOf(dtbnew),
                            diem.get(i).getTenHocKy()
                        });
                k++;
            }
            tblXepLoai.setModel(model);
        } catch (Exception e) {
        }
    }

    public void getXepLoai(String khoi, String tenlop, String monhoc, String hocky, String tenhs) throws RemoteException {
        try {
            diem = Diem.searchDiemByTenKhoiTenLopMonhocHocKyTenHS(khoi, tenlop, monhoc, hocky, tenhs);
            displayTableXepLoai(diem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    public void getLopByKhoiXepLoai(String khoi) throws RemoteException {
        Vector<LopDTO> vec = Lop.searchLopByTenKhoiAndTenLop(khoi, "");
        for (int i = 0; i < vec.size(); i++) {
            cboTenLopXepLoai.addItem(vec.get(i).getTenlop());
        }
    }    public void searchLop() {
        try {
            if (cboTenLopLop.getSelectedIndex() != 0) {
                getAllLop(cboTenKhoiLop.getSelectedItem().toString(), cboTenLopLop.getSelectedItem().toString());
            } else {
                getAllLop(cboTenKhoiLop.getSelectedItem().toString(), "");
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void tabPanelAdministratorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabPanelAdministratorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabPanelAdministratorKeyPressed

    private void btnPrintDiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintDiem1ActionPerformed
        // TODO add your handling code here:
        try {
            lblPrintxeploai.setText("");
            String t = "XepLoai_" + cboTenKhoiXeploai.getSelectedItem().toString() + "_" + "23_6_2011" + ".pdf";
            //System.out.println("t la"+t);
            Document dc = new Document();
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font font1 = new Font(bf, 36);
            font1.setColor(BaseColor.RED);
            //Font for field
            Font font = new Font(bf, 13);
            //Font for Title
            Font fontTitle = new Font(bf, 14);
            fontTitle.setStyle(Font.BOLD);

            //Create size of page PDF
            Rectangle rec = new Rectangle(2000, 2000);
            PdfWriter writer = PdfWriter.getInstance(dc, new FileOutputStream(t));
            //Set Size of Document
            dc.setPageSize(rec);
            dc.open();
            Paragraph para = new Paragraph();
            para.setFont(font);
            para.setAlignment(Paragraph.ALIGN_CENTER);
            para.add(new Paragraph("Xếp Loại" + " Lớp: " + cboTenLopXepLoai.getSelectedItem().toString(), font1));
            para.add(new Paragraph(" "));
            para.add(new Paragraph(" "));
            para.add(new Paragraph(" "));
            //Add para into Document
            dc.add(para);
            PdfPCell cell = new PdfPCell();
            PdfPTable table = new PdfPTable(11);
            // create table in file PDF
            // Them Header vao table
            table.addCell(new Paragraph("Khối", fontTitle));
            table.addCell(new Paragraph("Lớp", fontTitle));
            table.addCell(new Paragraph("Tên học sinh", fontTitle));
            table.addCell(new Paragraph("Điểm TB kỳ 1", fontTitle));
            table.addCell(new Paragraph("Điểm TB kỳ 2", fontTitle));
            table.addCell(new Paragraph("Điểm TB cả 2 Kỳ", fontTitle));
            table.addCell(new Paragraph("Học lực", fontTitle));
            table.addCell(new Paragraph("Hạnh Kiểm", fontTitle));
            table.addCell(new Paragraph("Xếp loại của giáo viên", fontTitle));
            table.addCell(new Paragraph("Nhận xét", fontTitle));
            table.addCell(new Paragraph("Tên Trường", fontTitle));
            float TBky1 = 0;
            float TBky2 = 0;
            float tongket = 0;
            float DiemTBCanam = 0;
            int k = (tblXepLoai.getModel().getRowCount() - 1) / 2;
            for (int i = 0; i < (tblXepLoai.getModel().getRowCount() - 1) / 2; i++) {
                for (int j = 0; j < tblXepLoai.getModel().getColumnCount(); j++) {
                    System.out.println("===============gia tri: " + tblXepLoai.getModel().getValueAt(i, j));
                    System.out.println("===============gia tri theo k: " + tblXepLoai.getModel().getValueAt(k, j));
                    if (j == 3) {
                        TBky1 = Float.parseFloat(tblXepLoai.getModel().getValueAt(i, j).toString());
                        TBky2 = Float.parseFloat(tblXepLoai.getModel().getValueAt(k, j).toString());

                        DecimalFormat df1 = new DecimalFormat("0.0");
                        String strTBky1 = df1.format(TBky1);
                        strTBky1 = strTBky1.replaceAll(",", ".");
                        TBky1 = Float.parseFloat(strTBky1);

                        DecimalFormat df2 = new DecimalFormat("0.0");
                        String strTBky2 = df2.format(TBky2);
                        strTBky2 = strTBky2.replaceAll(",", ".");
                        TBky2 = Float.parseFloat(strTBky2);

                        table.addCell(new Paragraph(TBky1 + "", font));
                        table.addCell(new Paragraph(TBky2 + "", font));
                        tongket = (Float.parseFloat(tblXepLoai.getModel().getValueAt(i, j).toString()) + Float.parseFloat(tblXepLoai.getModel().getValueAt(k, j).toString()) * 2) / 3;
                        DecimalFormat df = new DecimalFormat("0.0");
                        String dtbhocky = df.format(tongket);
                        String dtbnew = dtbhocky.replaceAll(",", ".");
                        DiemTBCanam = Float.parseFloat(dtbnew);
                        table.addCell(new Paragraph(dtbnew, font));

                    }
                    if (j != 3 && j != 4) {
                        table.addCell(new Paragraph(tblXepLoai.getModel().getValueAt(i, j).toString(), font));
                    }
                }
                k++;
                if (DiemTBCanam >= 9) {
                    table.addCell(new Paragraph("Xuất sắc", font));
                } else if (DiemTBCanam >= 8) {
                    table.addCell(new Paragraph("Giỏi", font));
                } else if (DiemTBCanam >= 6.5) {
                    table.addCell(new Paragraph("Khá", font));
                } else if (DiemTBCanam >= 5) {
                    table.addCell(new Paragraph("Trung Bình", font));
                } else if (DiemTBCanam >= 4) {
                    table.addCell(new Paragraph("Yếu", font));
                } else {
                    table.addCell(new Paragraph("Kém", font));
                }
                table.addCell(new Paragraph("  ", font));
                table.addCell(new Paragraph("  ", font));
                table.addCell(new Paragraph("  ", font));
                table.addCell(new Paragraph("Nguyễn Bỉnh Khiêm", font));
            }

            //Add table into Document
            dc.add(table);
            dc.close();
            lblPrintxeploai.setText("Print Successful");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnPrintDiem1ActionPerformed

    private void cboTenKhoiXeploaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKhoiXeploaiActionPerformed
        // TODO add your handling code here:
        try {
            cboTenLopXepLoai.removeAllItems();
            if (cboTenKhoiXeploai.getSelectedIndex() == 0) {
                cboTenLopXepLoai.setEnabled(false);
                btnPrintDiem1.setEnabled(false);
            } else {
                cboTenLopXepLoai.setEnabled(true);
                getLopByKhoiXepLoai(cboTenKhoiXeploai.getSelectedItem().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboTenKhoiXeploaiActionPerformed

    private void tblXepLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXepLoaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblXepLoaiMouseClicked

    private void cboTenLopXepLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenLopXepLoaiActionPerformed
        // TODO add your handling code here:
        try {
            getXepLoai(cboTenKhoiXeploai.getSelectedItem().toString(), cboTenLopXepLoai.getSelectedItem().toString(), "", "", "");
            btnPrintDiem1.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboTenLopXepLoaiActionPerformed

    private void btnImportDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportDiemActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            txtImportDiem.setText(fc.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_btnImportDiemActionPerformed

    private void ImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportExcelActionPerformed
        // TODO add your handling code here:
        try {
            lbNotifyImportExcel.setText("");
            lblPrintDiem.setText("");
            if (txtImportDiem.getText().equals("") == false) {
                boolean test = true;
                HSSFWorkbook wb = null;
                try {
                    wb = new HSSFWorkbook(new FileInputStream(txtImportDiem.getText()));
                } catch (Exception ex) {
                    lbNotifyImportExcel.setText("Chọn đúng định dạng Excel");
                }
                HSSFSheet sheet = wb.getSheet("Sheet1");
                Iterator rows1 = sheet.rowIterator();
                rows1.next();
                while (rows1.hasNext()) {
                    HSSFRow row = (HSSFRow) rows1.next();
                    //System.out.println("diem ck:++++++++++++++++++++++++++++: " + row.getCell(4).toString());

                    double diemMieng = Double.parseDouble(row.getCell(2).toString());
                    double diem15lan1 = Double.parseDouble(row.getCell(3).toString());
                    double diem15lan2 = Double.parseDouble(row.getCell(4).toString());
                    double diem1tietlan1 = Double.parseDouble(row.getCell(5).toString());
                    double diem1tietlan2 = Double.parseDouble(row.getCell(6).toString());
                    double diemthicuoiki = Double.parseDouble(row.getCell(7).toString());
                    double tenhocky = Double.parseDouble(row.getCell(8).toString());
                    String maHS = row.getCell(0).toString();
                    String maMHoc = row.getCell(1).toString();
                    if (maHS.contains(".0")) {
                        if (maMHoc.contains(".0")) {
                            int mamonhocexcel = Integer.parseInt(row.getCell(1).toString().trim().substring(0, row.getCell(1).toString().trim().indexOf(".")));
                            int maHocSinhexcel = Integer.parseInt(row.getCell(0).toString().trim().substring(0, row.getCell(0).toString().trim().indexOf(".")));
                            //System.out.println("ma mon hoc trong excel: " + mamonhocexcel);
                            if (diemMieng >= 0 && diem15lan1 >= 0 && diem15lan2 >= 0 && diem1tietlan1 >= 0 && diem1tietlan2 >= 0 && diemthicuoiki >= 0 && tenhocky > 0 && diemMieng <= 10 && diem15lan1 <= 10 && diem15lan2 <= 10 && diem1tietlan1 <= 10 && diem1tietlan2 <= 10 && diemthicuoiki <= 10 && tenhocky <= 2) {
                                //System.out.println("mã học sinh trong excel==============: " + maHocSinhexcel);
                                Vector<HocSinhDTO> vec = null;
                                vec = HocSinh.searchHocSinhByHocSinhID(maHocSinhexcel);

                                if (vec.size() > 0) {
                                    Vector<MonhocDTO> monhoc = Monhoc.searchMonhocIDByKhoiID(vec.get(0).getKhoiID());
                                    for (int k = 0; k < monhoc.size(); k++) {
                                        System.out.println("mamonhoc trong excel: " + mamonhocexcel + " ma mon hoc trong csdl: " + monhoc.get(k).getMonhocID());
                                        if (mamonhocexcel == monhoc.get(k).getMonhocID()) {
                                            test = true;
                                            break;
                                        } else {
                                            test = false;
                                        }
                                    }
                                    if (test == false) {
                                        lblPrintDiem.setText("Mã môn học không đúng với học sinh");
                                        break;
                                    }
                                } else {
                                    test = false;
                                    lblPrintDiem.setText("Xem lại mã học sinh");
                                    break;
                                }
                            } else {
                                test = false;
                                lblPrintDiem.setText("Điếm phải >= 0 và <=10,Tên học kỳ >=1 và <=2");
                                break;
                            }
                        } else {
                            test = false;
                            lblPrintDiem.setText("Mã môn học trong excel phải là số nguyên");
                            break;
                        }
                    } else {
                        test = false;
                        lblPrintDiem.setText("Mã học sinh trong excel phải là số nguyên");
                        break;
                    }
                }
                if (test == true) {
                    System.out.println("thuc hien thoi");
                    Iterator rows = sheet.rowIterator();
                    rows.next();
                    while (rows.hasNext()) {
                        HSSFRow row = (HSSFRow) rows.next();
                        float diemmieng = Float.parseFloat(row.getCell(2).toString());
                        float diem15lan1 = Float.parseFloat(row.getCell(3).toString());
                        float diem15lan2 = Float.parseFloat(row.getCell(4).toString());
                        float diem1tietlan1 = Float.parseFloat(row.getCell(5).toString());
                        float diem1tietlan2 = Float.parseFloat(row.getCell(6).toString());
                        float diemthiCK = Float.parseFloat(row.getCell(7).toString());
                        int tenhocky = Integer.parseInt(row.getCell(8).toString().trim().substring(0, row.getCell(8).toString().trim().indexOf(".")));
                        int mamonhocexcel = Integer.parseInt(row.getCell(1).toString().trim().substring(0, row.getCell(1).toString().trim().indexOf(".")));
                        int maHocSinhexcel = Integer.parseInt(row.getCell(0).toString().trim().substring(0, row.getCell(0).toString().trim().indexOf(".")));
                        Diem.InsertDiemHocSinh(maHocSinhexcel, mamonhocexcel, tenhocky, diemmieng, diem15lan1, diem15lan2, diem1tietlan1, diem1tietlan2, diemthiCK);
                        getAllDiem("", "", "", "", "");
                        lblPrintDiem.setText("Chèn điểm thành công");
                    }
                }
            } else {
                lblPrintDiem.setText("Click vào nút Browse để chọn file excel");
            }
        } catch (Exception ex1) {
            ex1.printStackTrace();
            lblPrintDiem.setText("Nhập đầy đủ điểm cho một sinh viên,Điểm phải là số thực,mã môn học và mã sinh viên là nguyên ");
        }
    }//GEN-LAST:event_ImportExcelActionPerformed

    private void cboHocKyDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHocKyDiemActionPerformed
        // TODO add your handling code here:
        searchDiemByhocky();
    }//GEN-LAST:event_cboHocKyDiemActionPerformed

    private void btnPrintDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintDiemActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //to export pdf file
            String str = "";
            //            if (cboTruongDiem.getSelectedIndex() != 0) {
                //                str += cboTruongDiem.getSelectedItem().toString();
                //            }
            //            if (cboKhoaDiem.getSelectedIndex() != 0) {
                //                str += cboKhoaDiem.getSelectedItem().toString();
                //            }
            //            if (cboHockyDiem.getSelectedIndex() != 0) {
                //                str += cboHockyDiem.getSelectedItem().toString();
                //            }
            //            if (cboMonhocDiem.getSelectedIndex() != 0) {
                //                str += cboMonhocDiem.getSelectedItem().toString();
                //            }
            //            if (cboLopDiem.getSelectedIndex() != 0) {
                //                str += cboLopDiem.getSelectedItem().toString();
                //            }
            String t = "BangDiem " + str + ".pdf";
            //System.out.println("t la"+t);
            Document dc = new Document();

            BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font font1 = new Font(bf, 36);
            font1.setColor(BaseColor.RED);
            //Font for field
            Font font = new Font(bf, 13);
            //Font for Title
            Font fontTitle = new Font(bf, 14);
            fontTitle.setStyle(Font.BOLD);

            //Create size of page PDF
            Rectangle rec = new Rectangle(2000, 2000);
            PdfWriter writer = PdfWriter.getInstance(dc, new FileOutputStream(t));
            //Set Size of Document
            dc.setPageSize(rec);
            dc.open();
            Paragraph para = new Paragraph();
            para.setFont(font);
            para.setAlignment(Paragraph.ALIGN_CENTER);
            para.add(new Paragraph("BẢNG ĐIỂM", font1));
            para.add(new Paragraph(str, font1));
            para.add(new Paragraph(" "));
            para.add(new Paragraph(" "));
            para.add(new Paragraph(" "));
            //Add para into Document
            dc.add(para);
            PdfPCell cell = new PdfPCell();
            PdfPTable table = new PdfPTable(11);
            // create table in file PDF
            // Them Header vao table
            table.addCell(new Paragraph("Tên Khối", fontTitle));
            table.addCell(new Paragraph("Tên Lớp", fontTitle));
            table.addCell(new Paragraph("Tên Học sinh", fontTitle));
            table.addCell(new Paragraph("Tên Môn học", fontTitle));
            table.addCell(new Paragraph("Điểm miệng", fontTitle));
            table.addCell(new Paragraph("Điểm 15' lần 1", fontTitle));
            table.addCell(new Paragraph("Điểm 15' lần 2", fontTitle));
            table.addCell(new Paragraph("Điểm 1 tiết lần 1", fontTitle));
            table.addCell(new Paragraph("Điểm 1 tiết lần 2", fontTitle));
            table.addCell(new Paragraph("Điểm thi cuối kỳ", fontTitle));
            table.addCell(new Paragraph("Tên học kỳ", fontTitle));
            //Them noi dung vao table
            for (int i = 0; i < diem.size(); i++) {
                //table.addCell(String.valueOf(String.valueOf(vecDiem.get(i).getMaMH())));
                table.addCell(new Paragraph(diem.get(i).getTenKhoi(), font));
                table.addCell(new Paragraph(diem.get(i).getTenLop(), font));
                table.addCell(new Paragraph(diem.get(i).getTenHocSinh(), font));
                table.addCell(new Paragraph(diem.get(i).getTenMH(), font));
                table.addCell(new Paragraph(String.valueOf(diem.get(i).getDiemMieng()), font));
                table.addCell(new Paragraph(String.valueOf(diem.get(i).getDiem15Lan1()), font));
                table.addCell(new Paragraph(String.valueOf(diem.get(i).getDiem15Lan2()), font));
                table.addCell(new Paragraph(String.valueOf(diem.get(i).getDiem1TietLan1()), font));
                table.addCell(new Paragraph(String.valueOf(diem.get(i).getDiem1TietLan2()), font));
                table.addCell(new Paragraph(String.valueOf(diem.get(i).getDiemThiCuoiKi()), font));
                table.addCell(new Paragraph(String.valueOf(diem.get(i).getTenHocKy()), font));

            }
            //Add table into Document
            dc.add(table);
            dc.close();
            lblPrintDiem.setText("Print Successful!!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnPrintDiemActionPerformed

    private void cboTenKhoiDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKhoiDiemActionPerformed
        // TODO add your handling code here:
        try {
            txtSearchHSDiem.setText("");
            cboTenLopDiem.removeAllItems();
            cboMonhocDiem.removeAllItems();
            if (cboTenKhoiDiem.getSelectedIndex() == 0) {
                cboTenLopDiem.setEnabled(false);
                cboMonhocDiem.setEnabled(false);
                cboHocKyDiem.setEnabled(false);
                //                txtSearchHSDiem.setEnabled(false);
                //                btnTimkiemDiem.setEnabled(false);
                getAllDiem("", "", "", "", "");
            } else {
                //                txtSearchHSDiem.setEnabled(true);
                //                btnTimkiemDiem.setEnabled(true);
                cboMonhocDiem.setEnabled(true);
                cboTenLopDiem.setEnabled(true);
                cboHocKyDiem.setEnabled(true);
                getMonhocByKhoiTenMH(cboTenKhoiDiem.getSelectedItem().toString(), "");
                getLopByKhoiDiem(cboTenKhoiDiem.getSelectedItem().toString());
                getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), "", "", "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboTenKhoiDiemActionPerformed

    private void btnTimkiemDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemDiemActionPerformed
        // TODO add your handling code here:
        searchDiembyten();
    }//GEN-LAST:event_btnTimkiemDiemActionPerformed

    private void tblResultDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultDiemMouseClicked
        btnCapnhatDiem.setEnabled(true);
        btnDeleteDiem.setEnabled(true);
        String tenHS = tblResultDiem.getValueAt(tblResultDiem.getSelectedRow(), 2).toString();
        String tenKhoi = tblResultDiem.getValueAt(tblResultDiem.getSelectedRow(), 0).toString();
        String tenLop = tblResultDiem.getValueAt(tblResultDiem.getSelectedRow(), 1).toString();
        String tenmh = tblResultDiem.getValueAt(tblResultDiem.getSelectedRow(), 3).toString();
        String hocky = tblResultDiem.getValueAt(tblResultDiem.getSelectedRow(), 11).toString();
        //System.out.println("khoi: "+tenKhoi+" tenlop: "+tenLop+ " ten hss: "+tenHS+" tenmh: "+tenmh+" hocky: "+hocky);
        Vector<DiemDTO> vec = null;
        try {
            vec = Diem.searchDiemByTenKhoiTenLopMonhocHocKyTenHS(tenKhoi, tenLop, tenmh, hocky, tenHS);
            diemDetails(vec);
        } catch (RemoteException ex) {
        }
    }//GEN-LAST:event_tblResultDiemMouseClicked

    private void btnDeleteDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDiemActionPerformed
        // TODO add your handling code here:
        try {
            lblPrintDiem.setText("");
            lbnotifyResultDiem.setText("");
            if (txtDiemMiengDiem.getText().compareTo("") == 0 || txtDiem15pLan1.getText().compareTo("") == 0 || txtDiem15pLan2.getText().compareTo("") == 0 || txtDiem1TietLan1.getText().compareTo("") == 0 || txtDiem1TietLan2.getText().compareTo("") == 0 || txtDiemCKDiem.getText().compareTo("") == 0) {
                lbnotifyResultDiem.setText("Nhập đầy đủ tất cả các điểm");
            } else {
                //System.out.println("khoi: "+txtTenKhoiDiem.getText()+" lop: "+txtTenLopDiem.getText()+" ten mon hoc: "+txtTenMHDiem.getText()+" hocky: "+txtTenHocKyDiem.getText()+" ten hs: "+txtTenHSDiem.getText()+" diem mieng : "+Float.parseFloat(txtDiemMiengDiem.getText())+" diem 15phut lan 1: "+ Float.parseFloat(txtDiem15pLan1.getText())+" diem 15 p lan 2: "+ Float.parseFloat(txtDiem15pLan2.getText())+" diem 1 tiet lan 1: "+Float.parseFloat(txtDiem1TietLan1.getText())+" diem 1 tiet lan 2: "+Float.parseFloat(txtDiem1TietLan2.getText())+" diem thi cuoi ki: "+Float.parseFloat(txtDiemCKDiem.getText()));
                int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa điểm này ko..?", "xóa", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    int i = Diem.deleteDiemHs(txtTenKhoiDiem.getText(), txtTenLopDiem.getText(), txtTenMHDiem.getText(), txtTenHocKyDiem.getText(), txtTenHSDiem.getText(), Float.parseFloat(txtDiemMiengDiem.getText()), Float.parseFloat(txtDiem15pLan1.getText()), Float.parseFloat(txtDiem15pLan2.getText()), Float.parseFloat(txtDiem1TietLan1.getText()), Float.parseFloat(txtDiem1TietLan2.getText()), Float.parseFloat(txtDiemCKDiem.getText()));
                    System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiii: " + i);
                    if (i > 0) {
                        lbnotifyResultDiem.setText("Xóa điểm thành công");
                        //getAllDiem("", "", "", "", "");
                        //getAllDiem(cboTenKhoiDiem.getSelectedItem().toString(), cboTenLopDiem.getSelectedItem().toString(), cboMonhocDiem.getSelectedItem().toString(), cboHocKyDiem.getSelectedItem().toString(), "");
                        searchDiembyten();
                    } else {
                        lbnotifyResultDiem.setText("Nhập điểm đúng để xóa...!!");
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteDiemActionPerformed

    private void btnCapnhatDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatDiemActionPerformed
        // TODO add your handling code here:
        lbnotifyResultDiem.setText("");
        if (txtDiemMiengDiem.getText().compareTo("") == 0 || txtDiem15pLan1.getText().compareTo("") == 0 || txtDiem15pLan2.getText().compareTo("") == 0 || txtDiem1TietLan1.getText().compareTo("") == 0 || txtDiem1TietLan2.getText().compareTo("") == 0 || txtDiemCKDiem.getText().compareTo("") == 0) {
            lbnotifyResultDiem.setText("Nhập đầy đủ tất cả các điểm");
        } else if (Float.parseFloat(txtDiemMiengDiem.getText()) < 0 || Float.parseFloat(txtDiem15pLan1.getText()) < 0 || Float.parseFloat(txtDiem15pLan2.getText()) < 0 || Float.parseFloat(txtDiem1TietLan1.getText()) < 0 || Float.parseFloat(txtDiem1TietLan2.getText()) < 0 || Float.parseFloat(txtDiemCKDiem.getText()) < 0 || Float.parseFloat(txtDiemMiengDiem.getText()) > 10 || Float.parseFloat(txtDiem15pLan1.getText()) > 10 || Float.parseFloat(txtDiem15pLan2.getText()) > 10 || Float.parseFloat(txtDiem1TietLan1.getText()) > 10 || Float.parseFloat(txtDiem1TietLan2.getText()) > 10 || Float.parseFloat(txtDiemCKDiem.getText()) > 10) {
            lbnotifyResultDiem.setText("Điểm phải là >=0 và <=10");
        } else {
            try {
                Diem.UpdateDiemHs(txtTenKhoiDiem.getText(), txtTenLopDiem.getText(), txtTenMHDiem.getText(), txtTenHocKyDiem.getText(), txtTenHSDiem.getText(), Float.parseFloat(txtDiemMiengDiem.getText()), Float.parseFloat(txtDiem15pLan1.getText()), Float.parseFloat(txtDiem15pLan2.getText()), Float.parseFloat(txtDiem1TietLan1.getText()), Float.parseFloat(txtDiem1TietLan2.getText()), Float.parseFloat(txtDiemCKDiem.getText()));
                lbnotifyResultDiem.setText("Cập nhật điểm thành công");
                searchDiembyten();
            } catch (Exception ex) {
            }
        }
    }//GEN-LAST:event_btnCapnhatDiemActionPerformed

    private void cboTenLopDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenLopDiemActionPerformed
        // TODO add your handling code here:
        searchTenLopDiem();
    }//GEN-LAST:event_cboTenLopDiemActionPerformed

    private void cboMonhocDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonhocDiemActionPerformed
        // TODO add your handling code here:
        searchMonhocdiem();
    }//GEN-LAST:event_cboMonhocDiemActionPerformed

    private void cboMonhocMoiGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonhocMoiGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMonhocMoiGVActionPerformed

    private void cboTenKhoiMoiGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKhoiMoiGVActionPerformed
        // TODO add your handling code here:
        try {
            cboMonhocMoiGV.removeAllItems();
            if (cboTenKhoiMoiGV.getSelectedIndex() == 0) {
                cboMonhocMoiGV.setEnabled(false);
            } else {
                cboMonhocMoiGV.setEnabled(true);
                Vector<MonhocDTO> vec = Monhoc.searchMonhocKhoiTenMH(cboTenKhoiMoiGV.getSelectedItem().toString(), "");
                cboMonhocMoiGV.addItem("--- Chọn Tên Môn học ---");
                for (int i = 0; i < vec.size(); i++) {
                    cboMonhocMoiGV.addItem(vec.get(i).getTenMH());
                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboTenKhoiMoiGVActionPerformed

    private void btnBrowseGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseGVActionPerformed
        // TODO add your handling code here:
        String path = "/Images/Avatar/";
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            txtAvatarGV.setText(path + fc.getSelectedFile().getName());
            pathImages = fc.getSelectedFile().getPath();
            nameImages = fc.getSelectedFile().getName();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnBrowseGVActionPerformed

    private void btnThemGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGVActionPerformed
        // TODO add your handling code here:
        try {
            String gioitinh = getGioiTinhGV();
            if (cboTenKhoiMoiGV.getSelectedIndex() == 0) {
                lblNotifyKhoiGV.setText("Chọn khối");
            } else if (cboMonhocMoiGV.getSelectedIndex() == 0) {
                lblNotifyMonhocGV.setText("Chọn Môn học");
            } else if (txtTenGV.getText().compareTo("") == 0) {
                lblNotifyTenGV.setText("Nhập tên giáo viên");
            } else if (txtSoDthGV.getText().compareTo("") == 0) {
                lblNotifydthGV.setText("Nhập số điện thoại");
            } else if (txtDiachiGV.getText().compareTo("") == 0) {
                lblNotifyDiachiGV.setText("Nhập địa chỉ giáo viên");
            } else {
                BufferedImage src = ImageIO.read(new File(pathImages));
                // Convert Image to BufferedImage if required.
                BufferedImage image = toBufferedImage(src);
                save(image, nameImages);
                GiaoVien.insertGiaoVien(cboTenKhoiMoiGV.getSelectedItem().toString(), cboMonhocMoiGV.getSelectedItem().toString(), txtTenGV.getText(), gioitinh, txtDiachiGV.getText(), txtSoDthGV.getText(), getAvatarGV());
                searchGiaovien();
                lblNotifyResultGV.setText("Thêm mới giáo viên thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnThemGVActionPerformed

    private void btnCapnhatGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatGVActionPerformed
        // TODO add your handling code here:
        try {
            lblNotifyTenGV.setText("");
            lblNotifydthGV.setText("");
            lblNotifyDiachiGV.setText("");
            lblNotifyResultGV.setText("");
            String gioitinhgv = getGioiTinhGV();
            if (txtTenGV.getText().compareTo("") == 0) {
                lblNotifyTenGV.setText("Nhập tên cho giáo viên");
            } else if (txtSoDthGV.getText().compareTo("") == 0) {
                lblNotifydthGV.setText("Nhập số điện thoại giáo viên");
            } else if (txtDiachiGV.getText().compareTo("") == 0) {
                lblNotifyDiachiGV.setText("Nhập địa chỉ cho giáo viên");
            } else {
                BufferedImage src = ImageIO.read(new File(pathImages));
                // Convert Image to BufferedImage if required.
                BufferedImage image = toBufferedImage(src);
                save(image, nameImages);
                GiaoVien.updateGiaoVien(Integer.parseInt(txtGiaoVienID.getText()), cboMonhocMoiGV.getSelectedItem().toString(), txtTenGV.getText(), gioitinhgv, txtDiachiGV.getText(), txtSoDthGV.getText(), getAvatarGV());
                lblNotifyResultGV.setText("Cập nhật thành công");
                searchGiaovien();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCapnhatGVActionPerformed

    private void cboMonhocGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonhocGVActionPerformed
        // TODO add your handling code here:
        try {
            txtSearchTenGV.setText("");
            if (cboMonhocGV.getSelectedIndex() != 0) {
                getAllGV(cboTenKhoiGV.getSelectedItem().toString(), cboMonhocGV.getSelectedItem().toString(), txtSearchTenGV.getText());
            } else {
                getAllGV(cboTenKhoiHS.getSelectedItem().toString(), "", txtSearchTenGV.getText());
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }//GEN-LAST:event_cboMonhocGVActionPerformed

    private void btnThemmoiGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemmoiGVActionPerformed
        btnThemGV.setEnabled(true);
        btnCapnhatGV.setEnabled(false);
        cboTenKhoiMoiGV.setEnabled(true);
        txtGiaoVienID.setText("");
        lblNotifyResultGV.setText("");
    }//GEN-LAST:event_btnThemmoiGVActionPerformed

    private void cboTenKhoiGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKhoiGVActionPerformed
        // TODO add your handling code here:
        try {
            txtSearchTenGV.setText("");
            cboMonhocGV.removeAllItems();
            if (cboTenKhoiGV.getSelectedIndex() == 0) {
                cboMonhocGV.setEnabled(false);
                getAllGV("", "", "");
            } else {
                cboMonhocGV.setEnabled(true);
                getMonhocByKhoiGV(cboTenKhoiGV.getSelectedItem().toString());
                getAllGV(cboTenKhoiGV.getSelectedItem().toString(), "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboTenKhoiGVActionPerformed

    private void btnTimkiemTenGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemTenGVActionPerformed
        // TODO add your handling code here:
        searchGiaovien();
    }//GEN-LAST:event_btnTimkiemTenGVActionPerformed

    private void tblResultGVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultGVMouseClicked
        // TODO add your handling code here:
        lblNotifyResultGV.setText("");
        String tenkhoi = tblResultGV.getValueAt(tblResultGV.getSelectedRow(), 0).toString();
        String monhoc = tblResultGV.getValueAt(tblResultGV.getSelectedRow(), 3).toString();
        String tengv = tblResultGV.getValueAt(tblResultGV.getSelectedRow(), 2).toString();
        Vector<GiaoVienDTO> vec = null;
        try {
            vec = GiaoVien.searchGiaovienByKhoiAndMonhoc(tenkhoi, monhoc, tengv);
            giaoVienDetails(vec);
            cboTenKhoiMoiGV.setEnabled(false);
            btnThemGV.setEnabled(false);
            btnCapnhatGV.setEnabled(true);
        } catch (RemoteException ex) {
        }
    }//GEN-LAST:event_tblResultGVMouseClicked

    private void cboTenKhoiMonhocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKhoiMonhocActionPerformed
        // TODO add your handling code here:
        txtSearchMonHoc.setText("");
        if (cboTenKhoiMonhoc.getSelectedIndex() == 0) {
            searchMonhocByKhoiAndMonhoc("", "");
        } else {
            searchMonhocByKhoiAndMonhoc(cboTenKhoiMonhoc.getSelectedItem().toString(), "");
        }
    }//GEN-LAST:event_cboTenKhoiMonhocActionPerformed

    private void btnThemmoiMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemmoiMonHocActionPerformed
        btnDeleteMH.setEnabled(false);
        btnupdateMH.setEnabled(false);
        btnThemMonHoc.setEnabled(true);
    }//GEN-LAST:event_btnThemmoiMonHocActionPerformed

    private void btnTimkiemMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemMonHocActionPerformed
        // TODO add your handling code here:
        searchMonhoc();
    }//GEN-LAST:event_btnTimkiemMonHocActionPerformed

    private void btnDeleteMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMHActionPerformed
        // TODO add your handling code here:
        try {
            int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa môn này ko..??", "Xóa", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                Monhoc.deleteMonhoc(Integer.parseInt(txtMaMH.getText()));
                lblNotifyResultMH.setText("Xóa môn học thành công");
                searchMonhoc();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDeleteMHActionPerformed

    private void btnupdateMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateMHActionPerformed
        // TODO add your handling code here:
        try {
            lblNotifyResultMH.setText("");
            lblNotifykhoiMonhoc.setText("");
            lblNotifyMaMH.setText("");
            lblNotifyTenMH.setText("");
            lblNotifyHeSoMH.setText("");
            if (cboKhoimoiMonhoc.getSelectedIndex() == 0) {
                lblNotifykhoiMonhoc.setText("Chọn khối");
            } else if (txtTenMH.getText().compareTo("") == 0) {
                lblNotifyTenMH.setText("Nhập tên môn học");
            } else if (txtHeSoMH.getText().compareTo("") == 0) {
                lblNotifyHeSoMH.setText("Nhập hệ số môn học");
            } else {
                Monhoc.updateMonhoc(Integer.parseInt(txtMaMH.getText()), cboKhoimoiMonhoc.getSelectedItem().toString(), txtTenMH.getText(), Integer.parseInt(txtHeSoMH.getText()));
                lblNotifyResultMH.setText("Update môn học thành công");
                searchMonhoc();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnupdateMHActionPerformed

    private void btnThemMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonHocActionPerformed
        // TODO add your handling code here:
        try {
            lblNotifyResultMH.setText("");
            lblNotifykhoiMonhoc.setText("");
            lblNotifyMaMH.setText("");
            lblNotifyTenMH.setText("");
            lblNotifyHeSoMH.setText("");
            if (cboKhoimoiMonhoc.getSelectedIndex() == 0) {
                lblNotifykhoiMonhoc.setText("Chọn khối");
            } else if (txtTenMH.getText().compareTo("") == 0) {
                lblNotifyTenMH.setText("Nhập tên môn học");
            } else if (txtHeSoMH.getText().compareTo("") == 0) {
                lblNotifyHeSoMH.setText("Nhập hệ số môn học");
            } else {
                Monhoc.insertMonhoc(cboKhoimoiMonhoc.getSelectedItem().toString(), txtTenMH.getText(), Integer.parseInt(txtHeSoMH.getText()));
                lblNotifyResultMH.setText("Chèn môn học thành công");
                searchMonhoc();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnThemMonHocActionPerformed

    private void tblResultMonHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultMonHocMouseClicked
        btnupdateMH.setEnabled(true);
        btnThemMonHoc.setEnabled(false);
        btnDeleteMH.setEnabled(true);
        String tenKhoi = tblResultMonHoc.getValueAt(tblResultMonHoc.getSelectedRow(), 0).toString();
        String temh = tblResultMonHoc.getValueAt(tblResultMonHoc.getSelectedRow(), 2).toString();
        Vector<MonhocDTO> vec = null;
        try {
            vec = Monhoc.searchMonhocKhoiTenMH(tenKhoi, temh);
            monhocDetails(vec);
            cboTenKhoiMoiHS.setEnabled(false);
            cboLopMoiHS.setEnabled(true);
            btnCapnhatHS.setEnabled(true);
            btnThemHS.setEnabled(false);
        } catch (RemoteException ex) {
        }
    }//GEN-LAST:event_tblResultMonHocMouseClicked

    private void cboTenLopLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenLopLopActionPerformed
        // TODO add your handling code here:
        searchLop();
    }//GEN-LAST:event_cboTenLopLopActionPerformed

    private void cboTenKhoiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKhoiLopActionPerformed
        // TODO add your handling code here:
        try {
            cboTenLopLop.removeAllItems();
            if (cboTenKhoiLop.getSelectedIndex() == 0) {
                cboTenLopLop.setEnabled(false);
                getAllLop("", "");
            } else {
                cboTenLopLop.setEnabled(true);
                getLopByKhoiLop(cboTenKhoiLop.getSelectedItem().toString());
                getAllLop(cboTenKhoiLop.getSelectedItem().toString(), "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboTenKhoiLopActionPerformed

    private void tblResultLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultLopMouseClicked
        // TODO add your handling code here:
        btnDelteLop.setEnabled(true);
        btnCapnhatLop.setEnabled(true);
        btnThemLop.setEnabled(false);
        txtMaLop.setText(tblResultLop.getValueAt(tblResultLop.getSelectedRow(), 1).toString());
        cboTenKhoiMoiLop.setSelectedItem(tblResultLop.getValueAt(tblResultLop.getSelectedRow(), 0).toString());
        txtTenMoiLop.setText(tblResultLop.getValueAt(tblResultLop.getSelectedRow(), 2).toString());
        txtNamHocLop.setText(tblResultLop.getValueAt(tblResultLop.getSelectedRow(), 4).toString());
        txtsisolop.setText(tblResultLop.getValueAt(tblResultLop.getSelectedRow(), 3).toString());
    }//GEN-LAST:event_tblResultLopMouseClicked

    private void btnThemmoiLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemmoiLopActionPerformed
        btnCapnhatLop.setEnabled(false);
        btnThemLop.setEnabled(true);
        btnDelteLop.setEnabled(false);
        txtMaLop.setText("");
    }//GEN-LAST:event_btnThemmoiLopActionPerformed

    private void btnDelteLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelteLopActionPerformed
        // TODO add your handling code here:
        try {
            int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa lớp này ko..??", "Xóa", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                Lop.deleteLop(Integer.parseInt(txtMaLop.getText()));
                getAllLop("", "");
                lblNotifyLop.setText("Xóa lớp thành công");
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDelteLopActionPerformed

    private void btnThemLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLopActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {

            lblNotifyLop.setText("");
            lblKhoiLop.setText("");
            lblTenLop.setText("");
            lblNamHocLop.setText("");
            if (cboTenKhoiMoiLop.getSelectedIndex() == 0) {
                lblKhoiLop.setText("Chọn Khối");
            } else if (txtTenMoiLop.getText().compareTo("") == 0) {
                lblTenLop.setText("Nhập tên lớp");
            } else if (txtNamHocLop.getText().compareTo("") == 0) {
                lblNamHocLop.setText("Nhập Năm học");
            } else {
                Lop.insertLop(cboTenKhoiMoiLop.getSelectedItem().toString(), txtTenMoiLop.getText(), txtNamHocLop.getText());
                getAllLop("", "");
                lblNotifyLop.setText("Thêm lớp thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemLopActionPerformed

    private void btnCapnhatLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatLopActionPerformed
        // TODO add your handling code here:
        try {

            lblNotifyLop.setText("");
            if (cboTenKhoiMoiLop.getSelectedIndex() == 0) {
                lblKhoiLop.setText("Chọn Khối");
            } else if (txtTenMoiLop.getText().compareTo("") == 0) {
                lblTenLop.setText("Nhập tên lớp");
            } else if (txtNamHocLop.getText().compareTo("") == 0) {
                lblNamHocLop.setText("Nhập Năm học");
            } else if (txtsisolop.getText().compareTo("") == 0) {
                lblSisoLop.setText("Nhập sỉ số");
            } else {
                System.out.println("vao duoc elsse=============");
                Lop.UpdateLop(Integer.parseInt(txtMaLop.getText()), cboTenKhoiMoiLop.getSelectedItem().toString(), txtTenMoiLop.getText(), Integer.parseInt(txtsisolop.getText()), txtNamHocLop.getText());
                getAllLop("", "");
                lblNotifyLop.setText("Cập nhật thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCapnhatLopActionPerformed

    private void btnPrintHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintHSActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //to export pdf file
            lblNotifyPrintHocSinh.setText("");
            lblResultHS.setText("");
            String t = "SinhvienReport.pdf";
            //System.out.println("t la"+t);
            Document dc = new Document();

            BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font font1 = new Font(bf, 36);
            font1.setColor(BaseColor.RED);
            //Font for field
            Font font = new Font(bf, 13);
            //Font for Title
            Font fontTitle = new Font(bf, 14);
            fontTitle.setStyle(Font.BOLD);

            //Create size of page PDF
            Rectangle rec = new Rectangle(2000, 2000);
            PdfWriter writer = PdfWriter.getInstance(dc, new FileOutputStream(t));
            //Set Size of Document
            dc.setPageSize(rec);
            dc.open();
            Paragraph para = new Paragraph();
            para.setFont(font);
            para.setAlignment(Paragraph.ALIGN_CENTER);
            para.add(new Paragraph("DANH SÁCH HỌC SINH", font1));
            para.add(new Paragraph(" "));
            para.add(new Paragraph(" "));
            para.add(new Paragraph(" "));
            //Add para into Document
            dc.add(para);
            PdfPCell cell = new PdfPCell();
            PdfPTable table = new PdfPTable(7);

            // create table in file PDF
            // Them Header vao table
            //"Tên Khối", "Tên Lớp", "Tên Học Sinh", "Giới Tính", "Địa Chỉ", "Số Điện Thoại"
            table.addCell(new Paragraph("Tên Trường", fontTitle));
            table.addCell(new Paragraph("Tên Khối", fontTitle));
            table.addCell(new Paragraph("Tên Lớp", fontTitle));
            table.addCell(new Paragraph("Tên Học Sinh", fontTitle));
            table.addCell(new Paragraph("Giới Tính", fontTitle));
            table.addCell(new Paragraph("Địa Chỉ", fontTitle));
            table.addCell(new Paragraph("Số Điện Thoại", fontTitle));

            //Them noi dung vao table
            for (int i = 0; i < HS.size(); i++) {
                table.addCell(new Paragraph("Nguyễn Bỉnh Khiêm", font));
                table.addCell(new Paragraph(HS.get(i).getTenKhoi(), font));
                table.addCell(new Paragraph(HS.get(i).getTenlop(), font));
                table.addCell(new Paragraph(HS.get(i).getTen(), font));
                table.addCell(new Paragraph(HS.get(i).getGioiTinh(), font));
                table.addCell(new Paragraph(HS.get(i).getDiachi(), font));
                table.addCell(new Paragraph(HS.get(i).getSoDienThoai(), font));
            }
            //Add table into Document
            dc.add(table);
            dc.close();
            lblNotifyPrintHocSinh.setText("Print Successful!!!");
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_btnPrintHSActionPerformed

    private void cboTenLopHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenLopHSActionPerformed
        // TODO add your handling code here:
        try {
            if (cboTenLopHS.getSelectedIndex() != 0) {
                GetAllHS(cboTenKhoiHS.getSelectedItem().toString(), cboTenLopHS.getSelectedItem().toString(), txtSearchHS.getText());
            } else {
                GetAllHS(cboTenKhoiHS.getSelectedItem().toString(), "", txtSearchHS.getText());
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }//GEN-LAST:event_cboTenLopHSActionPerformed

    private void cboTenKhoiHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKhoiHSActionPerformed
        // TODO add your handling code here:
        try {
            txtSearchHS.setText("");
            cboTenLopHS.removeAllItems();
            if (cboTenKhoiHS.getSelectedIndex() == 0) {
                cboTenLopHS.setEnabled(false);
                GetAllHS("", "", "");
            } else {
                cboTenLopHS.setEnabled(true);
                getLopByKhoi(cboTenKhoiHS.getSelectedItem().toString());
                GetAllHS(cboTenKhoiHS.getSelectedItem().toString(), "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboTenKhoiHSActionPerformed

    private void cboLopMoiHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLopMoiHSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLopMoiHSActionPerformed

    private void cboTenKhoiMoiHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKhoiMoiHSActionPerformed
        // TODO add your handling code here:
        try {
            cboLopMoiHS.removeAllItems();
            if (cboTenKhoiMoiHS.getSelectedIndex() == 0) {
                cboLopMoiHS.setEnabled(false);
            } else {
                cboLopMoiHS.setEnabled(true);
                getLopByKhoiMoiHS(cboTenKhoiMoiHS.getSelectedItem().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboTenKhoiMoiHSActionPerformed

    private void btnBrowseSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseSVActionPerformed
        // TODO add your handling code here:
        String path = "/Images/Avatar/";
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            txtAvatarHS.setText(path + fc.getSelectedFile().getName());
            pathImages = fc.getSelectedFile().getPath();
            nameImages = fc.getSelectedFile().getName();
        }
    }//GEN-LAST:event_btnBrowseSVActionPerformed

    private void btnThemHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHSActionPerformed
        // TODO add your handling code here:
        try {
            SetLblNotifyHS();
            String gioitinh = GetGioiTinh();
            if (cboTenKhoiMoiHS.getSelectedIndex() > 0) {
                if (txtTenHS.getText().compareTo("") == 0) {
                    lblNotifyTenHS.setText("Nhập Tên Học Sinh");
                } else {
                    if (txtSoDthHS.getText().compareTo("") == 0) {
                        lblNotifySoDthHS.setText("Nhập Số điện thoại");
                    } else {
                        if (txtDiachiHS.getText().compareTo("") == 0) {
                            lblNotifyDiachiHS.setText("Nhập Địa Chỉ");
                        } else {
                            BufferedImage src = ImageIO.read(new File(pathImages));
                            // Convert Image to BufferedImage if required.
                            BufferedImage image = toBufferedImage(src);
                            save(image, nameImages);
                            HocSinh.procInsertHocSinh(cboTenKhoiMoiHS.getSelectedItem().toString(), cboLopMoiHS.getSelectedItem().toString(), txtTenHS.getText(), gioitinh, txtDiachiHS.getText(), txtSoDthHS.getText(), GetAvatar());
                            searchHocsinh();
                            lblResultHS.setText("Thêm mới học sinh thành công");
                        }
                    }

                }
            } else {
                lblNotifyKhoiHS.setText("Chọn Khối");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnThemHSActionPerformed

    private void btnCapnhatHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatHSActionPerformed
        // TODO add your handling code here:
        try {
            SetLblNotifyHS();
            String gioitinh = GetGioiTinh();
            if (txtTenHS.getText().compareTo("") == 0) {
                lblNotifyTenHS.setText("Nhập Tên Học Sinh");
            } else {
                if (txtSoDthHS.getText().compareTo("") == 0) {
                    lblNotifySoDthHS.setText("Nhập Số điện thoại");
                } else {
                    if (txtDiachiHS.getText().compareTo("") == 0) {
                        lblNotifyDiachiHS.setText("Nhập Địa Chỉ");
                    } else {
                        BufferedImage src = ImageIO.read(new File(pathImages));
                        // Convert Image to BufferedImage if required.
                        BufferedImage image = toBufferedImage(src);
                        System.out.println("pathImagespathImagespathImagespathImagespathImages: " + pathImages);
                        save(image, nameImages);
                        HocSinh.UpdateHocSinh(Integer.parseInt(txtHocsinhID.getText()), cboTenKhoiMoiHS.getSelectedItem().toString(), cboLopMoiHS.getSelectedItem().toString(), txtTenHS.getText(), gioitinh, txtDiachiHS.getText(), txtSoDthHS.getText(), GetAvatar());
                        GetAllHS("", "", "");
                        lblResultHS.setText("Cập nhật thành công");
                    }
                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCapnhatHSActionPerformed

    private void btnTimkiemHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemHSActionPerformed
        searchHocsinh();
    }//GEN-LAST:event_btnTimkiemHSActionPerformed

    private void tblResultHSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultHSMouseClicked
        // TODO add your handling code here:
        lblNotifyPrintHocSinh.setText("");
        lblResultHS.setText("");
        String tenHS = tblResultHS.getValueAt(tblResultHS.getSelectedRow(), 3).toString();
        String tenKhoi = tblResultHS.getValueAt(tblResultHS.getSelectedRow(), 0).toString();
        String tenLop = tblResultHS.getValueAt(tblResultHS.getSelectedRow(), 1).toString();
        Vector<HocSinhDTO> vec = null;
        try {
            vec = HocSinh.searchHocSinhByTenKhoiTenLopTenHocSinh(tenKhoi, tenLop, tenHS);
            SinhvienDetails(vec);
            cboTenKhoiMoiHS.setEnabled(false);
            cboLopMoiHS.setEnabled(true);
            btnCapnhatHS.setEnabled(true);
            btnThemHS.setEnabled(false);
        } catch (RemoteException ex) {
        }
    }//GEN-LAST:event_tblResultHSMouseClicked

    private void btnThemmoiHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemmoiHSActionPerformed
        // TODO add your handling code here:
        lblNotifyPrintHocSinh.setText("");
        lblResultHS.setText("");
        txtHocsinhID.setText("");
        txtTenHS.setEnabled(true);
        btnThemHS.setEnabled(true);
        btnCapnhatHS.setEnabled(false);
        cboTenKhoiMoiHS.setEnabled(true);
        cboLopMoiHS.setEnabled(false);
        txtTenHS.setText("");
        txtAvatarHS.setText("");
    }//GEN-LAST:event_btnThemmoiHSActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new Administrator(null).setVisible(true);
                } catch (NotBoundException ex) {
                    Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                }
            }
        });
    }
    //TRUONG
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ImportExcel;
    private javax.swing.JPanel QLDiem;
    private javax.swing.JPanel QLGiaoVien;
    private javax.swing.JPanel QLHocSinh;
    private javax.swing.JPanel QLMonHoc;
    private javax.swing.JPanel QlLop;
    private javax.swing.JPanel XepLoai;
    private javax.swing.JButton btnBrowseGV;
    private javax.swing.JButton btnBrowseSV;
    private javax.swing.JButton btnCapnhatDiem;
    private javax.swing.JButton btnCapnhatGV;
    private javax.swing.JButton btnCapnhatHS;
    private javax.swing.JButton btnCapnhatLop;
    private javax.swing.JButton btnDeleteDiem;
    private javax.swing.JButton btnDeleteMH;
    private javax.swing.JButton btnDelteLop;
    private javax.swing.JButton btnImportDiem;
    private javax.swing.JButton btnPrintDiem;
    private javax.swing.JButton btnPrintDiem1;
    private javax.swing.JButton btnPrintHS;
    private javax.swing.JButton btnThemGV;
    private javax.swing.JButton btnThemHS;
    private javax.swing.JButton btnThemLop;
    private javax.swing.JButton btnThemMonHoc;
    private javax.swing.JButton btnThemmoiGV;
    private javax.swing.JButton btnThemmoiHS;
    private javax.swing.JButton btnThemmoiLop;
    private javax.swing.JButton btnThemmoiMonHoc;
    private javax.swing.JButton btnTimkiemDiem;
    private javax.swing.JButton btnTimkiemHS;
    private javax.swing.JButton btnTimkiemMonHoc;
    private javax.swing.JButton btnTimkiemTenGV;
    private javax.swing.JButton btnupdateMH;
    private javax.swing.JComboBox cboHocKyDiem;
    private javax.swing.JComboBox cboKhoimoiMonhoc;
    private javax.swing.JComboBox cboLopMoiHS;
    private javax.swing.JComboBox cboMonhocDiem;
    private javax.swing.JComboBox cboMonhocGV;
    private javax.swing.JComboBox cboMonhocMoiGV;
    private javax.swing.JComboBox cboTenKhoiDiem;
    private javax.swing.JComboBox cboTenKhoiGV;
    private javax.swing.JComboBox cboTenKhoiHS;
    private javax.swing.JComboBox cboTenKhoiLop;
    private javax.swing.JComboBox cboTenKhoiMoiGV;
    private javax.swing.JComboBox cboTenKhoiMoiHS;
    private javax.swing.JComboBox cboTenKhoiMoiLop;
    private javax.swing.JComboBox cboTenKhoiMonhoc;
    private javax.swing.JComboBox cboTenKhoiXeploai;
    private javax.swing.JComboBox cboTenLopDiem;
    private javax.swing.JComboBox cboTenLopHS;
    private javax.swing.JComboBox cboTenLopLop;
    private javax.swing.JComboBox cboTenLopXepLoai;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAvatar;
    private javax.swing.JLabel jLabelAvatar1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lbNotifyImportExcel;
    private javax.swing.JLabel lbNotifyImportExcel1;
    private javax.swing.JLabel lblAvatarGV;
    private javax.swing.JLabel lblAvatarHS;
    private javax.swing.JLabel lblKhoiLop;
    private javax.swing.JLabel lblMaMonHoc;
    private javax.swing.JLabel lblMaMonHoc1;
    private javax.swing.JLabel lblMaSvSV;
    private javax.swing.JLabel lblMaSvSV1;
    private javax.swing.JLabel lblMakhoa;
    private javax.swing.JLabel lblNamHocLop;
    private javax.swing.JLabel lblNotifyDiachiGV;
    private javax.swing.JLabel lblNotifyDiachiHS;
    private javax.swing.JLabel lblNotifyHeSoMH;
    private javax.swing.JLabel lblNotifyKhoiGV;
    private javax.swing.JLabel lblNotifyKhoiHS;
    private javax.swing.JLabel lblNotifyLop;
    private javax.swing.JLabel lblNotifyLopHS;
    private javax.swing.JLabel lblNotifyMaMH;
    private javax.swing.JLabel lblNotifyMonhocGV;
    private javax.swing.JLabel lblNotifyPrintHocSinh;
    private javax.swing.JLabel lblNotifyResultGV;
    private javax.swing.JLabel lblNotifyResultMH;
    private javax.swing.JLabel lblNotifySoDthHS;
    private javax.swing.JLabel lblNotifyTenGV;
    private javax.swing.JLabel lblNotifyTenHS;
    private javax.swing.JLabel lblNotifyTenMH;
    private javax.swing.JLabel lblNotifydthGV;
    private javax.swing.JLabel lblNotifykhoiMonhoc;
    private javax.swing.JLabel lblPrintDiem;
    private javax.swing.JLabel lblPrintxeploai;
    private javax.swing.JLabel lblResultHS;
    private javax.swing.JLabel lblSearchDiem;
    private javax.swing.JLabel lblSearchLop;
    private javax.swing.JLabel lblSisoLop;
    private javax.swing.JLabel lblTenLop;
    private javax.swing.JLabel lblTenMH;
    private javax.swing.JLabel lbnotifyResultDiem;
    private javax.swing.JLabel lbnotifyXeploai;
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNamGV;
    private javax.swing.JRadioButton radNu;
    private javax.swing.JRadioButton radNuGV;
    private javax.swing.JTabbedPane tabPanelAdministrator;
    private javax.swing.JTable tblResultDiem;
    private javax.swing.JTable tblResultGV;
    private javax.swing.JTable tblResultHS;
    private javax.swing.JTable tblResultLop;
    private javax.swing.JTable tblResultMonHoc;
    private javax.swing.JTable tblXepLoai;
    private javax.swing.JTextField txtAvatarGV;
    private javax.swing.JTextField txtAvatarHS;
    private javax.swing.JTextField txtDiachiGV;
    private javax.swing.JTextField txtDiachiHS;
    private javax.swing.JTextField txtDiem15pLan1;
    private javax.swing.JTextField txtDiem15pLan2;
    private javax.swing.JTextField txtDiem1TietLan1;
    private javax.swing.JTextField txtDiem1TietLan2;
    private javax.swing.JTextField txtDiemCKDiem;
    private javax.swing.JTextField txtDiemMiengDiem;
    private javax.swing.JTextField txtGiaoVienID;
    private javax.swing.JTextField txtHeSoMH;
    private javax.swing.JTextField txtHocsinhID;
    private javax.swing.JTextField txtImportDiem;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JTextField txtMaMH;
    private javax.swing.JTextField txtNamHocLop;
    private javax.swing.JTextField txtSearchHS;
    private javax.swing.JTextField txtSearchHSDiem;
    private javax.swing.JTextField txtSearchMonHoc;
    private javax.swing.JTextField txtSearchTenGV;
    private javax.swing.JTextField txtSoDthGV;
    private javax.swing.JTextField txtSoDthHS;
    private javax.swing.JTextField txtTenGV;
    private javax.swing.JTextField txtTenHS;
    private javax.swing.JTextField txtTenHSDiem;
    private javax.swing.JTextField txtTenHocKyDiem;
    private javax.swing.JTextField txtTenKhoiDiem;
    private javax.swing.JTextField txtTenLopDiem;
    private javax.swing.JTextField txtTenMH;
    private javax.swing.JTextField txtTenMHDiem;
    private javax.swing.JTextField txtTenMoiLop;
    private javax.swing.JTextField txtsisolop;
    // End of variables declaration//GEN-END:variables
}
/*
@COPYRIGHT
Lê Bá Bách
Lớp 07T4
Mobil:0974099981
 */
