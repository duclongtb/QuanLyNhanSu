/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.implement.NhanSu;
import Model.ChucVuModel;
import Model.ComboItem;
import Model.NhanSuModel;
import Model.PhongBanModel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

/**
 *
 * @author Iroha
 */
public class detailInfor extends javax.swing.JDialog {

    hosoPanel previousPanel;
    NhanSuModel acc;
    ButtonGroup btn_gioitinh = new ButtonGroup();
    ButtonGroup btn_congchuc = new ButtonGroup();
    List<ChucVuModel> cv = new DAO.implement.ChucVu().getChucVu();
    List<PhongBanModel> pb = new DAO.implement.PhongBan().getPhongBan();
    String filename;
    String tenUser;
    boolean imageChange = false;
    
    /**
     * Creates new form detailInfor
     */
    public detailInfor(hosoPanel pF, String MaNS, java.awt.Frame parent, boolean modal, String tenTK) {
        super(parent, modal);
        initComponents();
        acc = new NhanSu().getNhanSu(MaNS);
        previousPanel = pF;
        tenUser = tenTK;
        prepare();
    }
    
    public detailInfor(String MaNS, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        acc = new NhanSu().getNhanSu(MaNS);
        txt_chinhtri.setLineWrap(true);
        txt_doanthe.setLineWrap(true);
        prepare();
    }
    
    public void prepare(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        jPanel1.setBackground(new java.awt.Color(255, 255, 255, 0));
        this.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_chinhtri.setLineWrap(true);
        txt_doanthe.setLineWrap(true);
        congchuc_yes.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    jdate_hopdong.getCalendarButton().setEnabled(false);
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    jdate_hopdong.getCalendarButton().setEnabled(true);
                }
            }
            
        });
        btn_gioitinh.add(gioitinh_nam);
        btn_gioitinh.add(gioitinh_nu);
        btn_congchuc.add(congchuc_yes);
        btn_congchuc.add(congchuc_no);
        jdate_ngaysinh.setDateFormatString("yyyy-MM-dd");
        jdate_hopdong.setDateFormatString("yyyy-MM-dd");
        //add thong tin.
        txt_mans.setText(acc.getMaNS());
        txt_chinhtri.setText(acc.getChinhTri());
        txt_doanthe.setText(acc.getDoanThe());
        txt_chuyennganh.setText(acc.getChuyenNganh());
        txt_dantoc.setText(acc.getDanToc());
        txt_hoten.setText(acc.getHoTen());
        txt_cancuoc.setText(acc.getCanCuoc());
        txt_quequan.setText(acc.getQueQuan());
        txt_sdt.setText(acc.getSoDienThoai());
        txt_trinhdo.setText(acc.getTrinhDoHocVan());
        lb_lastEdit.setText("Sửa đổi cuối bởi: " + acc.getThayDoiCuoi());
        if(acc.isGioiTinh())
            gioitinh_nam.setSelected(true);
        else
            gioitinh_nu.setSelected(true);
        if(acc.isCongChuc())
        {
            congchuc_yes.setSelected(true);
        }
        else
        {
            congchuc_no.setSelected(true);
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = formatter.parse(acc.getHanHopDong());
                jdate_hopdong.setDate(date);
            } catch (ParseException ex) {
                Logger.getLogger(detailInfor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txt_phongban.addItem(new ComboItem("Không có",null));
        for(PhongBanModel s:pb)
        {
            txt_phongban.addItem(new ComboItem(s.getTenPB(),s.getMaPB()));
        }
        int i = 1;
        if(acc.getMaPB()==null){
            txt_phongban.setSelectedIndex(0);
        }
        else{
            for(PhongBanModel s:pb)
            {
                if(s.getMaPB().equals(acc.getMaPB()))
                    txt_phongban.setSelectedIndex(i);
                i++;
            }
        }
        txt_chucvu.addItem(new ComboItem("Không có",null));
        for(ChucVuModel s:cv)
            txt_chucvu.addItem(new ComboItem(s.getTenCV(),s.getMaCV()));
        i=1;
        if(acc.getMaCV()==null){
            txt_chucvu.setSelectedIndex(0);
        }
        else{
            for(ChucVuModel s:cv)
            {
                if(s.getMaCV().equals(acc.getMaCV()))
                    txt_chucvu.setSelectedIndex(i);
                i++;
            }
        }
        
        //ngaythang
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = formatter.parse(acc.getNgaySinh());
            jdate_ngaysinh.setDate(date);
            
            Date dateToday = new Date();
            LocalDate d1 = LocalDate.parse(acc.getNgayThamGia(), DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate d2 = LocalDate.parse((String)formatter.format(dateToday), DateTimeFormatter.ISO_LOCAL_DATE);
            Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
            long diffDays = diff.toDays();
            txt_namcongtac.setText(String.valueOf(diffDays/365) + " năm (" + acc.getNgayThamGia() + ")");
        } catch (ParseException ex) {
            Logger.getLogger(detailInfor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(acc.getAnh()).getImage().getScaledInstance(lb_avt.getWidth(), lb_avt.getHeight(), Image.SCALE_SMOOTH));
            lb_avt.setIcon(imageIcon);
        }
        catch(Exception e){
            
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_cancuoc = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        btn_changeAvatar = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        lb_avt = new javax.swing.JLabel();
        txt_chuyennganh = new javax.swing.JTextField();
        txt_mans = new javax.swing.JTextField();
        txt_dantoc = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        txt_trinhdo = new javax.swing.JTextField();
        txt_quequan = new javax.swing.JTextField();
        txt_namcongtac = new javax.swing.JTextField();
        txt_hoten = new javax.swing.JTextField();
        txt_phongban = new javax.swing.JComboBox<>();
        txt_chucvu = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        congchuc_yes = new javax.swing.JRadioButton();
        congchuc_no = new javax.swing.JRadioButton();
        gioitinh_nu = new javax.swing.JRadioButton();
        gioitinh_nam = new javax.swing.JRadioButton();
        jdate_hopdong = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jdate_ngaysinh = new com.toedter.calendar.JDateChooser();
        lb_lastEdit = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_chinhtri = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_doanthe = new javax.swing.JTextArea();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 240, -1));

        txt_cancuoc.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_cancuoc.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_cancuoc.setForeground(new java.awt.Color(255, 255, 255));
        txt_cancuoc.setBorder(null);
        txt_cancuoc.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_cancuoc.setOpaque(false);
        jPanel1.add(txt_cancuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 240, 20));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Số CCND:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        btn_print.setBackground(new java.awt.Color(24, 98, 151));
        btn_print.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setText("Xuất DOC");
        btn_print.setBorder(null);
        btn_print.setBorderPainted(false);
        btn_print.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jPanel1.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, 110, 50));

        btn_changeAvatar.setBackground(new java.awt.Color(24, 98, 151));
        btn_changeAvatar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_changeAvatar.setForeground(new java.awt.Color(255, 255, 255));
        btn_changeAvatar.setText("Thay Avatar");
        btn_changeAvatar.setBorder(null);
        btn_changeAvatar.setBorderPainted(false);
        btn_changeAvatar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_changeAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_changeAvatarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_changeAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 140, 40));

        btn_save.setBackground(new java.awt.Color(24, 98, 151));
        btn_save.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_save.setForeground(new java.awt.Color(255, 255, 255));
        btn_save.setText("Lưu");
        btn_save.setBorder(null);
        btn_save.setBorderPainted(false);
        btn_save.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        jPanel1.add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 500, 110, 50));

        btn_cancel.setBackground(new java.awt.Color(24, 98, 151));
        btn_cancel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_cancel.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancel.setText("Huỷ");
        btn_cancel.setBorder(null);
        btn_cancel.setBorderPainted(false);
        btn_cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 500, 110, 50));

        lb_avt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lb_avt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 180, 240));

        txt_chuyennganh.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_chuyennganh.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_chuyennganh.setForeground(new java.awt.Color(255, 255, 255));
        txt_chuyennganh.setBorder(null);
        txt_chuyennganh.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_chuyennganh.setOpaque(false);
        jPanel1.add(txt_chuyennganh, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 220, -1));

        txt_mans.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_mans.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_mans.setForeground(new java.awt.Color(255, 255, 255));
        txt_mans.setBorder(null);
        txt_mans.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_mans.setEnabled(false);
        txt_mans.setOpaque(false);
        jPanel1.add(txt_mans, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 220, 20));

        txt_dantoc.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_dantoc.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_dantoc.setForeground(new java.awt.Color(255, 255, 255));
        txt_dantoc.setBorder(null);
        txt_dantoc.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_dantoc.setOpaque(false);
        jPanel1.add(txt_dantoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 250, 20));

        txt_sdt.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_sdt.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_sdt.setForeground(new java.awt.Color(255, 255, 255));
        txt_sdt.setBorder(null);
        txt_sdt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_sdt.setOpaque(false);
        jPanel1.add(txt_sdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 220, 20));

        txt_trinhdo.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_trinhdo.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_trinhdo.setForeground(new java.awt.Color(255, 255, 255));
        txt_trinhdo.setBorder(null);
        txt_trinhdo.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_trinhdo.setOpaque(false);
        jPanel1.add(txt_trinhdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 200, 260, -1));

        txt_quequan.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_quequan.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_quequan.setForeground(new java.awt.Color(255, 255, 255));
        txt_quequan.setBorder(null);
        txt_quequan.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_quequan.setOpaque(false);
        jPanel1.add(txt_quequan, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 240, 20));

        txt_namcongtac.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_namcongtac.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_namcongtac.setForeground(new java.awt.Color(255, 255, 255));
        txt_namcongtac.setBorder(null);
        txt_namcongtac.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_namcongtac.setEnabled(false);
        txt_namcongtac.setOpaque(false);
        jPanel1.add(txt_namcongtac, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, 190, -1));

        txt_hoten.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_hoten.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_hoten.setForeground(new java.awt.Color(255, 255, 255));
        txt_hoten.setBorder(null);
        txt_hoten.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_hoten.setOpaque(false);
        jPanel1.add(txt_hoten, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 260, 20));

        txt_phongban.setBackground(new java.awt.Color(255, 204, 204));
        txt_phongban.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_phongban.setOpaque(false);
        jPanel1.add(txt_phongban, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 240, -1));

        txt_chucvu.setBackground(new java.awt.Color(255, 204, 204));
        txt_chucvu.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_chucvu.setOpaque(false);
        jPanel1.add(txt_chucvu, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 260, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 260, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 240, -1));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 250, -1));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 220, -1));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, 260, -1));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 250, 220, -1));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, 190, -1));
        jPanel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 220, -1));

        congchuc_yes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        congchuc_yes.setForeground(new java.awt.Color(255, 255, 255));
        congchuc_yes.setText("Công chức");
        congchuc_yes.setOpaque(false);
        jPanel1.add(congchuc_yes, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, -1, -1));

        congchuc_no.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        congchuc_no.setForeground(new java.awt.Color(255, 255, 255));
        congchuc_no.setText("Hợp đồng");
        congchuc_no.setOpaque(false);
        jPanel1.add(congchuc_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 290, -1, -1));

        gioitinh_nu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        gioitinh_nu.setForeground(new java.awt.Color(255, 255, 255));
        gioitinh_nu.setText("Nữ");
        gioitinh_nu.setOpaque(false);
        jPanel1.add(gioitinh_nu, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, -1, -1));

        gioitinh_nam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        gioitinh_nam.setForeground(new java.awt.Color(255, 255, 255));
        gioitinh_nam.setText("Nam");
        gioitinh_nam.setOpaque(false);
        jPanel1.add(gioitinh_nam, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, -1));

        jdate_hopdong.setBackground(new java.awt.Color(255, 255, 255, 0));
        jdate_hopdong.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jdate_hopdong.setOpaque(false);
        jPanel1.add(jdate_hopdong, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, 220, 30));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Hạn hợp đồng:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 320, -1, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ngày Sinh:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        jdate_ngaysinh.setBackground(new java.awt.Color(255, 255, 255, 0));
        jdate_ngaysinh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jdate_ngaysinh.setOpaque(false);
        jPanel1.add(jdate_ngaysinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 230, 30));

        lb_lastEdit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_lastEdit.setForeground(new java.awt.Color(255, 255, 255));
        lb_lastEdit.setText("Sửa đổi cuối bởi:");
        jPanel1.add(lb_lastEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, -1, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("HỒ SƠ NHÂN SỰ");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 210, 40));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Họ tên:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quê quán:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Giới tính:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dân tộc:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số điện thoại:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Trình độ:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Chuyên ngành:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phòng ban:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Đoàn thể:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mã nhân sự:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, -1, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Chức vụ:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, -1, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Loại nhân sự:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Thời gian công tác:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 260, -1, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Chính trị:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, -1, -1));

        txt_chinhtri.setColumns(20);
        txt_chinhtri.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_chinhtri.setRows(5);
        txt_chinhtri.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txt_chinhtri);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 240, 90));

        txt_doanthe.setColumns(20);
        txt_doanthe.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_doanthe.setRows(5);
        txt_doanthe.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txt_doanthe);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 350, 250, 120));

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/detailInfor.png"))); // NOI18N
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1000, 500));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        try{
            if(txt_hoten.getText().equals("")){
                txt_hoten.requestFocus();
                throw new Exception("Họ tên không được để trống!");
            }
            if(txt_cancuoc.getText().equals("")){
                txt_cancuoc.requestFocus();
                throw new Exception("Số căn cước không được để trống!");
            }
            if(!txt_cancuoc.getText().matches("[0-9]+")){
                txt_cancuoc.requestFocus();
                throw new Exception("Số căn cước chỉ bao gồm số!");
            }
            if(jdate_ngaysinh.getDate()==null)
                throw new Exception("Ngày sinh không được để trống!");
            if(txt_quequan.getText().equals("")){
                txt_quequan.requestFocus();
                throw new Exception("Quê quán không được để trống!");
            }
            if(txt_dantoc.getText().equals("")){
                txt_dantoc.requestFocus();
                throw new Exception("Dân tộc không được để trống!");
            }
            if(txt_sdt.getText().equals("")){
                txt_sdt.requestFocus();
                throw new Exception("Số điện thoại không được để trống!");
            }
            if(!txt_sdt.getText().matches("[0-9]+")){
                txt_sdt.requestFocus();
                throw new Exception("Số điện thoại chỉ bao gồm số!");
            }
            if(txt_trinhdo.getText().equals("")){
                txt_trinhdo.requestFocus();
                throw new Exception("Trình độ không được để trống!");
            }
            if(txt_chuyennganh.getText().equals("")){
                txt_chuyennganh.requestFocus();
                throw new Exception("Chuyên ngành không được để trống!");
            }
            if(congchuc_no.isSelected())
                if(jdate_hopdong.getDate()==null)
                throw new Exception("Hạn hợp đồng không được để trống!");
            if(lb_avt.getIcon()==null){
                throw new Exception("Hình đại diện không được để trống!");
            }
            boolean isOK;
            byte[] person_image;
            if(imageChange){
                File image = new File(filename);
                FileInputStream fis = new FileInputStream(image);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for(int readNum; (readNum=fis.read(buf))!=-1;){
                    bos.write(buf,0,readNum);
            }
            person_image=bos.toByteArray();
            }
            else
            {
                person_image=acc.getAnh();
            }
            new NhanSu().updateNS(txt_mans.getText(),
                    txt_hoten.getText(),
                    ((JTextField)jdate_ngaysinh.getDateEditor().getUiComponent()).getText(),
                    txt_quequan.getText(),
                    txt_dantoc.getText(),
                    txt_sdt.getText(),
                    txt_trinhdo.getText(),
                    txt_chuyennganh.getText(),
                    ((ComboItem)txt_phongban.getSelectedItem()).getValue(),
                    ((ComboItem)txt_chucvu.getSelectedItem()).getValue(),
                    txt_chinhtri.getText(),
                    txt_doanthe.getText(),
                    gioitinh_nam.isSelected()?true:false,
                    person_image,
                    tenUser,
                    txt_cancuoc.getText(),
                    congchuc_yes.isSelected()?true:false,
                    congchuc_no.isSelected()?((JTextField)jdate_hopdong.getDateEditor().getUiComponent()).getText():"1900-01-01");
            JOptionPane.showMessageDialog(this,"Sửa thành công");
            previousPanel.LoadData();
            this.dispose();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Sửa thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_changeAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_changeAvatarActionPerformed
        try{
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);
            File f = chooser.getSelectedFile();
            if(f!=null)
            {
                String filenamez = f.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(filenamez).getImage().getScaledInstance(lb_avt.getWidth(), lb_avt.getHeight(), Image.SCALE_SMOOTH));
                lb_avt.setIcon(imageIcon);
                imageChange = true;
                filename = filenamez;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_changeAvatarActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        try{
            JFileChooser chonChoLuu = new JFileChooser();
            //chonChoLuu.setCurrentDirectory(new File(System.getProperty("user.dir")));
            chonChoLuu.setSelectedFile(new File("unname.docx"));
            int reponse = chonChoLuu.showSaveDialog(null);
            if(reponse == JFileChooser.APPROVE_OPTION){
                String savePath = chonChoLuu.getSelectedFile().getAbsolutePath();
                if (chonChoLuu.getSelectedFile().getName().length()>5) 
                {
                    if(!savePath.substring(savePath.length() - 5).equals(".docx"))
                        savePath =  savePath + ".docx";
                }
                else{
                    savePath =  savePath + ".docx";
                }
                if(new File(savePath).exists()){
                    if(JOptionPane.showConfirmDialog (null, "Đã tồn tại file này, bạn có muốn ghi đè?","Bạn chắc chứ?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        JasperReport jasperReport = JasperCompileManager.compileReport(System.getProperty("user.dir") + "\\ReportTemplates\\hoso.jrxml");
                        JRDataSource dataSource = new JREmptyDataSource();
                        HashMap parameters = new HashMap();
                        parameters.put("hoten",txt_hoten.getText());
                        parameters.put("quequan",txt_quequan.getText());
                        parameters.put("gioitinh",gioitinh_nam.isSelected()?"Nam":"Nữ");
                        parameters.put("dantoc",txt_dantoc.getText());
                        parameters.put("ngaysinh",((JTextField)jdate_ngaysinh.getDateEditor().getUiComponent()).getText());
                        parameters.put("sodienthoai",txt_sdt.getText());
                        parameters.put("phongban",((ComboItem)txt_phongban.getSelectedItem()).getKey());
                        parameters.put("chucvu",((ComboItem)txt_chucvu.getSelectedItem()).getKey());
                        parameters.put("trinhdo",txt_trinhdo.getText());
                        parameters.put("chuyennganh",txt_chuyennganh.getText());
                        parameters.put("chinhtri",txt_chinhtri.getText());
                        parameters.put("doanthe",txt_doanthe.getText());
                        parameters.put("loainhansu",congchuc_yes.isSelected()?"Công chức":"Hợp đồng");
                        parameters.put("hanhopdong", congchuc_no.isSelected()?((JTextField)jdate_hopdong.getDateEditor().getUiComponent()).getText():"Không có");
                        parameters.put("logo", System.getProperty("user.dir") + "\\ReportTemplates\\logo.jpg");
                        //lay anh
                        String imagePath = System.getProperty("user.dir") + "\\ReportTemplates\\avatar.jpg";
                        ImageIcon img = (ImageIcon)lb_avt.getIcon();
                        BufferedImage bi = new BufferedImage(img.getIconWidth(), img.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics g = bi.createGraphics();
                        img.paintIcon(null, g, 0,0);
                        g.dispose();
                        ImageIO.write(bi, "jpg", new File(imagePath));
                        parameters.put("image", imagePath);
                        //xuat file
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
                        JRDocxExporter exporter = new JRDocxExporter();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, savePath);
                        exporter.exportReport();
                        //JasperExportManager.exportReportToPdfFile(jasperPrint, "holy.pdf");
                        JOptionPane.showMessageDialog(this, "Xuất file thành công!");
                    }
                }
                else{
                    JasperReport jasperReport = JasperCompileManager.compileReport(System.getProperty("user.dir") + "\\ReportTemplates\\hoso.jrxml");
                    JRDataSource dataSource = new JREmptyDataSource();
                    HashMap parameters = new HashMap();
                    parameters.put("hoten",txt_hoten.getText());
                    parameters.put("quequan",txt_quequan.getText());
                    parameters.put("gioitinh",gioitinh_nam.isSelected()?"Nam":"Nữ");
                    parameters.put("dantoc",txt_dantoc.getText());
                    parameters.put("ngaysinh",((JTextField)jdate_ngaysinh.getDateEditor().getUiComponent()).getText());
                    parameters.put("sodienthoai",txt_sdt.getText());
                    parameters.put("phongban",((ComboItem)txt_phongban.getSelectedItem()).getKey());
                    parameters.put("chucvu",((ComboItem)txt_chucvu.getSelectedItem()).getKey());
                    parameters.put("trinhdo",txt_trinhdo.getText());
                    parameters.put("chuyennganh",txt_chuyennganh.getText());
                    parameters.put("chinhtri",txt_chinhtri.getText());
                    parameters.put("doanthe",txt_doanthe.getText());
                    parameters.put("loainhansu",congchuc_yes.isSelected()?"Công chức":"Hợp đồng");
                    parameters.put("hanhopdong", congchuc_no.isSelected()?((JTextField)jdate_hopdong.getDateEditor().getUiComponent()).getText():"Không có");
                    parameters.put("logo", System.getProperty("user.dir") + "\\ReportTemplates\\logo.jpg");
                    //lay anh
                    String imagePath = System.getProperty("user.dir") + "\\ReportTemplates\\avatar.jpg";
                    ImageIcon img = (ImageIcon)lb_avt.getIcon();
                    BufferedImage bi = new BufferedImage(img.getIconWidth(), img.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics g = bi.createGraphics();
                    img.paintIcon(null, g, 0,0);
                    g.dispose();
                    ImageIO.write(bi, "jpg", new File(imagePath));
                    parameters.put("image", imagePath);
                    //xuat file
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
                    JRDocxExporter exporter = new JRDocxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, savePath);
                    exporter.exportReport();
                    //JasperExportManager.exportReportToPdfFile(jasperPrint, "holy.pdf");
                    JOptionPane.showMessageDialog(this, "Xuất file thành công!");
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Xuất file thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_printActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(detailInfor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(detailInfor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(detailInfor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(detailInfor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                detailInfor dialog = new detailInfor(new hosoPanel(new java.awt.Frame(), null), null, new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_changeAvatar;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_save;
    private javax.swing.JRadioButton congchuc_no;
    private javax.swing.JRadioButton congchuc_yes;
    private javax.swing.JRadioButton gioitinh_nam;
    private javax.swing.JRadioButton gioitinh_nu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private com.toedter.calendar.JDateChooser jdate_hopdong;
    private com.toedter.calendar.JDateChooser jdate_ngaysinh;
    private javax.swing.JLabel lb_avt;
    private javax.swing.JLabel lb_lastEdit;
    private javax.swing.JTextField txt_cancuoc;
    private javax.swing.JTextArea txt_chinhtri;
    private javax.swing.JComboBox<ComboItem> txt_chucvu;
    private javax.swing.JTextField txt_chuyennganh;
    private javax.swing.JTextField txt_dantoc;
    private javax.swing.JTextArea txt_doanthe;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_mans;
    private javax.swing.JTextField txt_namcongtac;
    private javax.swing.JComboBox<ComboItem> txt_phongban;
    private javax.swing.JTextField txt_quequan;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_trinhdo;
    // End of variables declaration//GEN-END:variables
}
