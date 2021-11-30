/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.implement.NhanSu;
import DAO.implement.PhongBan;
import Model.ComboItem;
import Model.NhanSuPBCVModel;
import Model.PhongBanModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
public class phongbanPanel extends javax.swing.JPanel {

    /**
     * Creates new form phongbanModel
     */
    java.awt.Frame bb;
    ArrayList<PhongBanModel> ls = new ArrayList<>();
    ArrayList<NhanSuPBCVModel> listNS = new ArrayList<>();
    ArrayList<String> listIn = new ArrayList<>();
    ArrayList<String> listOut = new ArrayList<>();
    DefaultListModel<ComboItem> ls1;
    DefaultListModel<ComboItem> ls2;
    String tenUser;
    public phongbanPanel(java.awt.Frame mainFrame, String tenTK) {
        initComponents();
        bb=mainFrame;
        tenUser = tenTK;
        loadData();
    }

    public void loadData(){
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
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
        ls = new ArrayList<>(new PhongBan().getPhongBan());
        listNS = new ArrayList<>(new PhongBan().getListNS());
        txt_phongban.removeAllItems();
        for(PhongBanModel z: ls)
        {
            txt_phongban.addItem(new ComboItem(z.getTenPB(),z.getMaPB()));
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

        txt_phongban = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        txt_mapb = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txt_tenpb = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_sdt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txt_sl = new javax.swing.JTextField();
        isFree = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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

        txt_phongban.setBackground(new java.awt.Color(255, 204, 204));
        txt_phongban.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txt_phongban.setOpaque(false);
        txt_phongban.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txt_phongbanItemStateChanged(evt);
            }
        });
        add(txt_phongban, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 290, 50));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 330, -1));

        txt_mapb.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_mapb.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_mapb.setForeground(new java.awt.Color(255, 255, 255));
        txt_mapb.setBorder(null);
        txt_mapb.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_mapb.setEnabled(false);
        txt_mapb.setOpaque(false);
        add(txt_mapb, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 330, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã phòng ban:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 320, -1));

        txt_tenpb.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_tenpb.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_tenpb.setForeground(new java.awt.Color(255, 255, 255));
        txt_tenpb.setBorder(null);
        txt_tenpb.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_tenpb.setOpaque(false);
        add(txt_tenpb, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 320, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên phòng ban:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 340, -1));

        txt_sdt.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_sdt.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_sdt.setForeground(new java.awt.Color(255, 255, 255));
        txt_sdt.setBorder(null);
        txt_sdt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_sdt.setOpaque(false);
        add(txt_sdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 340, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Số điện thoại:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 300, -1));

        txt_sl.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_sl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_sl.setForeground(new java.awt.Color(255, 255, 255));
        txt_sl.setBorder(null);
        txt_sl.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_sl.setEnabled(false);
        txt_sl.setOpaque(false);
        add(txt_sl, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 300, -1));

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
        add(isFree, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Danh sách tất cả nhân sự:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Danh sách nhân sự của phòng:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số lượng nhân sự:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, -1, -1));

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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 350, 350));

        jList1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jScrollPane1.setViewportView(jList1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 350, 350));

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/panelDefault.png"))); // NOI18N
        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 650));
    }// </editor-fold>//GEN-END:initComponents

    private void txt_phongbanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txt_phongbanItemStateChanged
        if(txt_phongban.getSelectedItem()!=null){
            listIn = new ArrayList<>();
            listOut = new ArrayList<>();
            String choose = ((ComboItem)txt_phongban.getSelectedItem()).getValue();
            ls1 = new DefaultListModel<ComboItem>();
            ls2 = new DefaultListModel<ComboItem>();
            int dem=0;
            for(NhanSuPBCVModel z:listNS){
                if(z.getMaPB()!=null&&z.getMaPB().equals(choose)){
                    ls1.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
                    dem++;
                }
                else{
                    if(isFree.isSelected()){
                        if(z.getMaPB()==null)
                            ls2.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
                    }
                    else{
                        ls2.addElement(new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS()));
                    }
                }
            }
            jList1.setModel(ls1);
            jList2.setModel(ls2);
            for(PhongBanModel z: ls)
                if(choose.equals(z.getMaPB())){
                    txt_mapb.setText(z.getMaPB());
                    txt_tenpb.setText(z.getTenPB());
                    txt_sdt.setText(String.valueOf(z.getSdt()));
                    txt_sl.setText(String.valueOf(dem));
            }
        }
    }//GEN-LAST:event_txt_phongbanItemStateChanged

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        int choose = txt_phongban.getSelectedIndex();
        try{
            if(txt_tenpb.getText().equals("")){
                txt_tenpb.requestFocus();
                throw new Exception("Tên phòng ban không được để trống!");
            }
            
            if(txt_sdt.getText().equals("")){
                txt_sdt.requestFocus();
                throw new Exception("Số điện thoại không được để trống!");
            }
            if(!txt_sdt.getText().matches("[0-9]+")){
                txt_sdt.requestFocus();
                throw new Exception("Số điện thoại chỉ bao gồm số!");
            }
            String maPB = txt_mapb.getText();
            String tenPB = txt_tenpb.getText();
            String sdt = txt_sdt.getText();
            new PhongBan().suaPB(maPB, tenPB, sdt);
            NhanSu nhansuDAO = new NhanSu();
            String mapb = ((ComboItem)txt_phongban.getSelectedItem()).getValue();
            for(String z : listIn)
                nhansuDAO.updatePhongBan(z, mapb, tenUser);
            for(String z : listOut)
                nhansuDAO.updatePhongBan(z, null, tenUser);
            reLoad();
            txt_phongban.setSelectedIndex(choose);
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
        if(txt_phongban.getSelectedItem()==null)
        {
            ls2 = new DefaultListModel<ComboItem>();
            for(NhanSuPBCVModel z:listNS){
                ComboItem item = new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS());
                if(!listSoSanh.contains(new ComboItemX(item))){
                    if(isFree.isSelected()){
                        if(z.getMaPB()==null)
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
            String choose = ((ComboItem)txt_phongban.getSelectedItem()).getValue();
            ls2 = new DefaultListModel<ComboItem>();
            for(NhanSuPBCVModel z:listNS){
                ComboItem item = new ComboItem(z.getMaNS()+" - "+z.getTenNS(),z.getMaNS());
                if(!listSoSanh.contains(new ComboItemX(item))){
                    if(isFree.isSelected()){
                        if(z.getMaPB()==null||z.getMaPB().equals(choose))
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
        txt_phongban.setEnabled(true);
        txt_phongban.setSelectedIndex(0);
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
        btn_edit.setEnabled(true);
        btn_delete.setEnabled(true);
        btn_add.setEnabled(true);
        txt_mapb.setEnabled(false);
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        try{
            if(txt_mapb.getText().equals("")){
                txt_mapb.requestFocus();
                throw new Exception("Mã phòng ban không được để trống!");
            }
            if(txt_tenpb.getText().equals("")){
                txt_tenpb.requestFocus();
                throw new Exception("Tên phòng ban không được để trống!");
            }
            if(txt_sdt.getText().equals("")){
                txt_sdt.requestFocus();
                throw new Exception("Số điện thoại không được để trống!");
            }
            if(!txt_sdt.getText().matches("[0-9]+")){
                txt_sdt.requestFocus();
                throw new Exception("Số điện thoại chỉ bao gồm số!");
            }
            if(new PhongBan().TimTenPB(txt_mapb.getText())!=null){
                txt_mapb.requestFocus();
                throw new Exception("Mã phòng ban này đã tồn tại!");
            }
            
            String maPB = txt_mapb.getText();
            String tenPB = txt_tenpb.getText();
            String sdt = txt_sdt.getText();
            new PhongBan().themPB(maPB, tenPB, sdt);
            NhanSu a = new NhanSu();
            for(String z : listIn)
                a.updatePhongBan(z, maPB, tenUser);
            reLoad();
            txt_phongban.setEnabled(true);
            txt_phongban.setSelectedIndex(0);
            btn_save.setVisible(false);
            btn_cancel.setVisible(false);
            btn_edit.setEnabled(true);
            btn_delete.setEnabled(true);
            btn_add.setEnabled(true);
            txt_mapb.setEnabled(false);
            JOptionPane.showMessageDialog(background, "Thêm thành công!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(background, e.getMessage(), "Thêm thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if(JOptionPane.showConfirmDialog (null, "Hành động này sẽ xoá vĩnh viễn phòng ban","Bạn chắc chứ?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            try{
                if(new PhongBan().xoaPB(((ComboItem)txt_phongban.getSelectedItem()).getValue())){
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
        txt_mapb.setEnabled(true);
        btn_save.setVisible(true);
        btn_cancel.setVisible(true);
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_add.setEnabled(false);
        txt_phongban.setEnabled(false);
        txt_phongban.setSelectedIndex(-1);
        txt_mapb.setText("");
        txt_tenpb.setText("");
        txt_sdt.setText("");
        txt_sl.setText("0");
        ls1 = new DefaultListModel<ComboItem>();
        jList1.setModel(ls1);
        ls2 = new DefaultListModel<ComboItem>();
        for(NhanSuPBCVModel z:listNS){
            if(isFree.isSelected()){
                if(z.getMaPB()==null)
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
    private javax.swing.JList<ComboItem> jList1;
    private javax.swing.JList<ComboItem> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField txt_mapb;
    private javax.swing.JComboBox<ComboItem> txt_phongban;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_sl;
    private javax.swing.JTextField txt_tenpb;
    // End of variables declaration//GEN-END:variables
}
