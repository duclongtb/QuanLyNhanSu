/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.implement.ChucVu;
import DAO.implement.NhanSu;
import DAO.implement.PhongBan;
import Model.BacLuongModel;
import Model.ChucVuModel;
import Model.ComboItem;
import Model.NhanSuPBCVModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Iroha
 */
public class chucvuPanel extends javax.swing.JPanel {

    /**
     * Creates new form phongbanModel
     */
    java.awt.Frame bb;
    ArrayList<ChucVuModel> ls = new ArrayList<>();
    ArrayList<NhanSuPBCVModel> listNS = new ArrayList<>();
    ArrayList<String> listIn = new ArrayList<>();
    ArrayList<String> listOut = new ArrayList<>();
    DefaultListModel<ComboItem> ls1;
    DefaultListModel<ComboItem> ls2;
    String tenUser;
    public chucvuPanel(java.awt.Frame mainFrame, String tenTK) {
        initComponents();
        bb=mainFrame;
        tenUser=tenTK;
        loadData();
    }

    public void loadData(){
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
        List<BacLuongModel> bl = new DAO.implement.BacLuong().getBacLuong();
        for(BacLuongModel s:bl)
            txt_bacluong.addItem(s.getBacLuong());
        reLoad();
        jList1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    String s = ((ComboItem)ls1.getElementAt(index)).getValue();
                    listOut.add(s);
                        if(listIn.contains(s))
                            listIn.remove(s);
                    ls2.addElement(ls1.getElementAt(index));
                    ls1.removeElementAt(index);
                    jList1.setModel(ls1);
                    jList2.setModel(ls2);  
                    txt_sl.setText(String.valueOf((Integer.valueOf(txt_sl.getText()))-1));
                }
            }
        });
        jList2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    String s = ((ComboItem)ls2.getElementAt(index)).getValue();
                    listIn.add(s);
                        if(listOut.contains(s))
                            listOut.remove(s);
                    ls1.addElement(ls2.getElementAt(index));
                    ls2.removeElementAt(index);
                    jList1.setModel(ls1);
                    jList2.setModel(ls2);
                    txt_sl.setText(String.valueOf((Integer.valueOf(txt_sl.getText()))+1));
                }
            }
        });
        jList1.addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if ( SwingUtilities.isRightMouseButton(e) ) {      
                   jList1.setSelectedIndex(jList1.locationToIndex(e.getPoint()));

                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem seeDetail = new JMenuItem("Xem hồ sơ");
                    seeDetail.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        detailInforNotEdit z = new detailInforNotEdit(jList1.getSelectedValue().toString().split(" - ")[0],bb,true);
                        //z.setAlwaysOnTop(true);
                        z.setVisible(true);
                        }
                    });
//                    JMenuItem editDetail = new JMenuItem("Sửa hồ sơ");
//                    editDetail.addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent e) {
//                        detailInfor z = new detailInfor(jList1.getSelectedValue().toString().split(" - ")[0],bb,true);
//                        z.setAlwaysOnTop(true);
//                        z.setVisible(true);
//                        }
//                    });
                    JMenuItem outAll = new JMenuItem("Bỏ toàn bộ");
                    outAll.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int slAll = ls1.getSize();
                            int dem = 0;
                            for(int i=0;i<slAll;i++){
                                String s = ((ComboItem)ls1.getElementAt(0)).getValue();
                                listOut.add(s);
                                    if(listIn.contains(s))
                                        listIn.remove(s);
                                ls2.addElement(ls1.getElementAt(0));
                                ls1.removeElementAt(0);
                                dem++;
                            }
                            jList1.setModel(ls1);
                            jList2.setModel(ls2);
                            txt_sl.setText(String.valueOf((Integer.valueOf(txt_sl.getText()))-dem));
                        }
                    });
                    menu.add(seeDetail);
//                    menu.add(editDetail);
                    menu.add(outAll);
                    menu.show(jList1, e.getPoint().x, e.getPoint().y);            
                }
            }
        });
        jList2.addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if ( SwingUtilities.isRightMouseButton(e) ) {      
                   jList2.setSelectedIndex(jList2.locationToIndex(e.getPoint()));

                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem seeDetail = new JMenuItem("Xem hồ sơ");
                    seeDetail.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        detailInforNotEdit z = new detailInforNotEdit(jList2.getSelectedValue().toString().split(" - ")[0],bb,true);
                        //z.setAlwaysOnTop(true);
                        z.setVisible(true);
                        }
                    });
//                    JMenuItem editDetail = new JMenuItem("Sửa hồ sơ");
//                    editDetail.addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent e) {
//                        detailInfor z = new detailInfor(jList2.getSelectedValue().toString().split(" - ")[0],bb,true);
//                        z.setAlwaysOnTop(true);
//                        z.setVisible(true);
//                        }
//                    });
                    JMenuItem inAll = new JMenuItem("Thêm toàn bộ");
                    inAll.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int slAll = ls2.getSize();
                            int dem = 0;
                            for(int i=0;i<slAll;i++){
                                String s = ((ComboItem)ls2.getElementAt(0)).getValue();
                                listIn.add(s);
                                    if(listOut.contains(s))
                                        listOut.remove(s);
                                ls1.addElement(ls2.getElementAt(0));
                                ls2.removeElementAt(0);
                                dem++;
                            }
                            jList1.setModel(ls1);
                            jList2.setModel(ls2);
                            txt_sl.setText(String.valueOf((Integer.valueOf(txt_sl.getText()))+dem));
                        }
                    });
                    menu.add(seeDetail);
//                    menu.add(editDetail);
                    menu.add(inAll);
                    menu.show(jList2, e.getPoint().x, e.getPoint().y);            
                }
            }
        });
    }
    
    public void reLoad(){
        ls = new ArrayList<>(new ChucVu().getChucVu());
        listNS = new ArrayList<>(new PhongBan().getListNS());
        txt_chucvu.removeAllItems();
        for(ChucVuModel z: ls)
        {
            txt_chucvu.addItem(new ComboItem(z.getTenCV(),z.getMaCV()));
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

        txt_bacluong = new javax.swing.JComboBox<>();
        txt_chucvu = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        txt_macv = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txt_tencv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_phucap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txt_sl = new javax.swing.JTextField();
        isFree = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_save = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_bacluong.setBackground(new java.awt.Color(255, 204, 204));
        txt_bacluong.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txt_bacluong.setOpaque(false);
        add(txt_bacluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 120, -1));

        txt_chucvu.setBackground(new java.awt.Color(255, 204, 204));
        txt_chucvu.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txt_chucvu.setOpaque(false);
        txt_chucvu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txt_chucvuItemStateChanged(evt);
            }
        });
        add(txt_chucvu, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 290, 50));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 350, -1));

        txt_macv.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_macv.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_macv.setForeground(new java.awt.Color(255, 255, 255));
        txt_macv.setBorder(null);
        txt_macv.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_macv.setEnabled(false);
        txt_macv.setOpaque(false);
        add(txt_macv, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 350, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã chức vụ:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 340, -1));

        txt_tencv.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_tencv.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_tencv.setForeground(new java.awt.Color(255, 255, 255));
        txt_tencv.setBorder(null);
        txt_tencv.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_tencv.setOpaque(false);
        add(txt_tencv, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 340, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên chức vụ:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 380, -1));

        txt_phucap.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_phucap.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_phucap.setForeground(new java.awt.Color(255, 255, 255));
        txt_phucap.setBorder(null);
        txt_phucap.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_phucap.setOpaque(false);
        add(txt_phucap, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 380, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Phụ cấp:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 270, -1));

        txt_sl.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_sl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_sl.setForeground(new java.awt.Color(255, 255, 255));
        txt_sl.setBorder(null);
        txt_sl.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_sl.setEnabled(false);
        txt_sl.setOpaque(false);
        add(txt_sl, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 270, -1));

        isFree.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        isFree.setForeground(new java.awt.Color(255, 255, 255));
        isFree.setText("Chưa chỉ định");
        isFree.setBorder(null);
        isFree.setOpaque(false);
        isFree.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                isFreeItemStateChanged(evt);
            }
        });
        add(isFree, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số người đảm nhiệm:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Những người đang đảm nhiệm:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Danh sách tất cả nhân sự:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bậc lương:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, -1, -1));

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
        add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 40, 100, 50));

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
        add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 110, 100, 50));

        btn_add.setBackground(new java.awt.Color(24, 98, 151));
        btn_add.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_add.setText("Thêm");
        btn_add.setBorder(null);
        btn_add.setBorderPainted(false);
        btn_add.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 40, 100, 50));

        btn_edit.setBackground(new java.awt.Color(24, 98, 151));
        btn_edit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_edit.setText("Sửa");
        btn_edit.setBorder(null);
        btn_edit.setBorderPainted(false);
        btn_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 110, 100, 50));

        btn_delete.setBackground(new java.awt.Color(24, 98, 151));
        btn_delete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("Xoá");
        btn_delete.setBorder(null);
        btn_delete.setBorderPainted(false);
        btn_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 180, 100, 50));

        jList2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jScrollPane2.setViewportView(jList2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 350, 310));

        jList1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jScrollPane1.setViewportView(jList1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 350, 310));

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/panelDefault.png"))); // NOI18N
        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 650));
    }// </editor-fold>//GEN-END:initComponents

    private void txt_chucvuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txt_chucvuItemStateChanged
        if(txt_chucvu.getSelectedItem()!=null){
            listIn = new ArrayList<>();
            listOut = new ArrayList<>();
            String choose = ((ComboItem)txt_chucvu.getSelectedItem()).getValue();
            ls1 = new DefaultListModel<ComboItem>();
            ls2 = new DefaultListModel<ComboItem>();
            int dem=0;
            for(NhanSuPBCVModel z:listNS){
                if(z.getMaCV()!=null&&z.getMaCV().equals(choose)){
                    ls1.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
                    dem++;
                }
                else{
                    if(isFree.isSelected()){
                        if(z.getMaCV()==null)
                            ls2.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
                    }
                    else{
                        ls2.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
                    }
                }
            }
            jList1.setModel(ls1);
            jList2.setModel(ls2);
            for(ChucVuModel z: ls)
                if(choose.equals(z.getMaCV())){
                    txt_macv.setText(z.getMaCV());
                    txt_tencv.setText(z.getTenCV());
                    txt_phucap.setText(String.valueOf(String.valueOf(new DecimalFormat("#0").format(z.getPhuCap()))));
                    if(z.getBacLuong()==0)
                        txt_bacluong.setSelectedIndex(-1);
                    else{
                        txt_bacluong.setSelectedItem(z.getBacLuong());
                    }
                    txt_sl.setText(String.valueOf(dem));
               }
        }
    }//GEN-LAST:event_txt_chucvuItemStateChanged

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        int choose = txt_chucvu.getSelectedIndex();
        try{
            if(txt_tencv.getText().equals("")){
                txt_tencv.requestFocus();
                throw new Exception("Tên chức vụ không được để trống!");
            }
            if(txt_phucap.getText().equals("")){
                txt_phucap.requestFocus();
                throw new Exception("Phụ cấp không được để trống!");
            }
            if(!txt_phucap.getText().matches("[0-9\\.]+")){
                txt_phucap.requestFocus();
                throw new Exception("Phụ cấp chỉ được để số!");
            }
            
            String maCV = txt_macv.getText();
            String tenCV = txt_tencv.getText();
            Double phuCap = Double.valueOf(txt_phucap.getText());
            int BacLuong = (Integer)txt_bacluong.getSelectedItem();
            new ChucVu().suaCV(maCV, tenCV, phuCap, BacLuong);
            NhanSu nhansuDAO = new NhanSu();
            String mapb = ((ComboItem)txt_chucvu.getSelectedItem()).getValue();
            for(String z : listIn)
                nhansuDAO.updateChucVu(z, mapb, tenUser);
            for(String z : listOut)
                nhansuDAO.updateChucVu(z, null, tenUser);
            reLoad();
            txt_chucvu.setSelectedIndex(choose);
            JOptionPane.showMessageDialog(background, "Sửa thành công!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(background, e.getMessage(), "Sửa thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_editActionPerformed

    public class ComboItemX{
        private String key, value;

        public ComboItemX(ComboItem a)
        {
            this.key = a.getKey();
            this.value = a.getValue();
        }

        public String getKey()
        {
            return key;
        }

        public String getValue()
        {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            int a = this.getValue().compareTo(((ComboItemX)obj).getValue());
            if (a==0)
                return true;
            else
                return false;
        }
    }
    
    private void isFreeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_isFreeItemStateChanged
        ListModel<ComboItem> listTamThoi = jList1.getModel();
        ArrayList<ComboItemX> listSoSanh = new ArrayList<>();
        for(int i = 0; i <listTamThoi.getSize();i++)
            listSoSanh.add(new ComboItemX(listTamThoi.getElementAt(i)));
        if(txt_chucvu.getSelectedItem()==null)
        {
            ls2 = new DefaultListModel<ComboItem>();
            for(NhanSuPBCVModel z:listNS){
                ComboItem item = new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS());
                if(!listSoSanh.contains(new ComboItemX(item))){
                    if(isFree.isSelected()){
                        if(z.getMaCV()==null)
                            ls2.addElement(item);
                        }
                    else{
                         ls2.addElement(item);
                    }
                }
            }
            jList2.setModel(ls2);
        }
        else{
            String choose = ((ComboItem)txt_chucvu.getSelectedItem()).getValue();
            ls2 = new DefaultListModel<ComboItem>();
            for(NhanSuPBCVModel z:listNS){
                ComboItem item = new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS());
                if(!listSoSanh.contains(new ComboItemX(item))){
                    if(isFree.isSelected()){
                        if(z.getMaCV()==null||z.getMaCV().equals(choose))
                            ls2.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
                    }
                    else{
                        ls2.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
                    }
                }
            }
            jList2.setModel(ls2);
        }
    }//GEN-LAST:event_isFreeItemStateChanged

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        getStartAdd();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        txt_chucvu.setEnabled(true);
        txt_chucvu.setSelectedIndex(0);
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
        btn_edit.setEnabled(true);
        btn_delete.setEnabled(true);
        btn_add.setEnabled(true);
        txt_macv.setEnabled(false);
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        try{
            if(txt_macv.getText().equals("")){
                txt_macv.requestFocus();
                throw new Exception("Mã chức vụ không được để trống!");
            }
            if(txt_tencv.getText().equals("")){
                txt_tencv.requestFocus();
                throw new Exception("Tên chức vụ không được để trống!");
            }
            if(txt_phucap.getText().equals("")){
                txt_phucap.requestFocus();
                throw new Exception("Phụ cấp không được để trống!");
            }
            if(!txt_phucap.getText().matches("[0-9\\.]+")){
                txt_phucap.requestFocus();
                throw new Exception("Phụ cấp phải là số!");
            }
            if(txt_bacluong.getSelectedIndex()==-1){
                throw new Exception("Vui lòng chọn bậc lương!");
            }
            if(new ChucVu().TimTenChucVu(txt_macv.getText())!=null){
                txt_macv.requestFocus();
                throw new Exception("Mã chức vụ này đã tồn tại!");
            }
            
            String maCV = txt_macv.getText();
            String tenCV = txt_tencv.getText();
            Double phuCap = Double.valueOf(txt_phucap.getText());
            int BacLuong = (Integer)txt_bacluong.getSelectedItem();
            new ChucVu().themCV(maCV, tenCV, phuCap, BacLuong);
            NhanSu a = new NhanSu();
            for(String z : listIn)
                a.updateChucVu(z, maCV,tenUser);
            reLoad();
            txt_chucvu.setEnabled(true);
            txt_chucvu.setSelectedIndex(0);
            btn_save.setVisible(false);
            btn_cancel.setVisible(false);
            btn_edit.setEnabled(true);
            btn_delete.setEnabled(true);
            btn_add.setEnabled(true);
            txt_macv.setEnabled(false);
            JOptionPane.showMessageDialog(background, "Thêm thành công!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(background, e.getMessage(), "Thêm thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if(JOptionPane.showConfirmDialog (null, "Hành động này sẽ xoá vĩnh viễn phòng ban","Bạn chắc chứ?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            try{
                if(new ChucVu().xoaCV(((ComboItem)txt_chucvu.getSelectedItem()).getValue())){
                    JOptionPane.showMessageDialog(background, "Xoá thành công!");
                    reLoad();
                }
                else{
                    JOptionPane.showMessageDialog(background, "Xoá thất bại!");
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(background, e.getMessage(), "Xoá thất bại!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void getStartAdd(){
        txt_macv.setEnabled(true);
        btn_save.setVisible(true);
        btn_cancel.setVisible(true);
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_add.setEnabled(false);
        txt_chucvu.setEnabled(false);
        txt_chucvu.setSelectedIndex(-1);
        txt_macv.setText("");
        txt_tencv.setText("");
        txt_phucap.setText("");
        txt_bacluong.setSelectedIndex(-1);
        txt_sl.setText("0");
        ls1 = new DefaultListModel<ComboItem>();
        jList1.setModel(ls1);
        ls2 = new DefaultListModel<ComboItem>();
        for(NhanSuPBCVModel z:listNS){
            if(isFree.isSelected()){
                if(z.getMaCV()==null)
                    ls2.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
            }
            else{
                 ls2.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
            }
        }
        jList2.setModel(ls2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_save;
    private javax.swing.JCheckBox isFree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<ComboItem> jList1;
    private javax.swing.JList<ComboItem> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JComboBox<Integer> txt_bacluong;
    private javax.swing.JComboBox<ComboItem> txt_chucvu;
    private javax.swing.JTextField txt_macv;
    private javax.swing.JTextField txt_phucap;
    private javax.swing.JTextField txt_sl;
    private javax.swing.JTextField txt_tencv;
    // End of variables declaration//GEN-END:variables
}
