package View;

import DAO.implement.NhanSu;
import Model.ComboItem;
import Model.CustomTable.HoSoTableModel;
import Model.NhanSuModel;
import Model.NhanSuHoSoModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Iroha
 */
public class hosoPanel extends javax.swing.JPanel {
    
    java.awt.Frame bb;
    hosoPanel aa;
    String tenUser;
    ArrayList<NhanSuHoSoModel> dsHoSo = new ArrayList<>();;
    boolean isort[] = {true,true,true,true,true,true,true,true,true,true,true};
    public hosoPanel(java.awt.Frame mainFrame, String tenTK) {
        initComponents();
        aa = this;
        bb = mainFrame;
        tenUser=tenTK;
        prepare();
        
    }
    
    public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            setOpaque(false);
            setFont(new Font("Times New Roman", Font.BOLD, 16));
            setForeground(new Color(255,255,255));
            setPreferredSize(new Dimension(1230, 35));
            return this;
        }

    }
    
    public void prepare(){
        for(int i = 0; i < new HoSoTableModel().getColumnCount();i++){
            txt_searchOption.addItem(new ComboItem(new HoSoTableModel().getColumnName(i),String.valueOf(i)));
        }
        table_ns.getTableHeader().setDefaultRenderer(new HeaderColor());
//        table_ns.getTableHeader().setBackground(new Color(82,147,255));
        table_ns.getTableHeader().setBackground(new Color(32, 136, 203));
        table_ns.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    String a = (String)table_ns.getValueAt(table_ns.getSelectedRow(), 0);
                    detailInfor z = new detailInfor(aa,a,bb,true,tenUser);
                    //z.setAlwaysOnTop(true);
                    z.setVisible(true);
                }
            }
        });
            table_ns.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table_ns.columnAtPoint(e.getPoint());
                sortData(col);
            }
        });
            jScrollPane1.getViewport().setBackground(Color.white);
            LoadData();
    }
    
    public void LoadData(){
//        jScrollPane1.setOpaque(false);
//        jScrollPane1.getViewport().setOpaque(false);
//        table_ns.setOpaque(false);
//        ((DefaultTableCellRenderer)table_ns.getDefaultRenderer(Object.class)).setOpaque(false);
        List<NhanSuModel> list = new NhanSu().getListNhanSu();
        dsHoSo = new ArrayList<>();
        for(int i = 0; i< list.size();i++)
        {
            NhanSuHoSoModel a =  new NhanSuHoSoModel(list.get(i));
            dsHoSo.add(a);
        }
        reloadTable();
    }
    
    public void reloadTable(){
        table_ns.setModel(new HoSoTableModel(dsHoSo));
        table_ns.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table_ns.getColumnModel().getColumn(0).setPreferredWidth(60);
        table_ns.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_ns.getColumnModel().getColumn(2).setPreferredWidth(100);
        table_ns.getColumnModel().getColumn(3).setPreferredWidth(80);
        table_ns.getColumnModel().getColumn(4).setPreferredWidth(110);
        table_ns.getColumnModel().getColumn(5).setPreferredWidth(130);
        table_ns.getColumnModel().getColumn(6).setPreferredWidth(90);
        table_ns.getColumnModel().getColumn(7).setPreferredWidth(110);
        table_ns.getColumnModel().getColumn(8).setPreferredWidth(138);
        table_ns.getColumnModel().getColumn(9).setPreferredWidth(130);
        table_ns.getColumnModel().getColumn(10).setPreferredWidth(130);
    }
    
    public void sortData(int index){
        if(isort[index])
        {
            switch(index){
            case 0:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getMaNS().compareTo(o1.getMaNS());
                    }
                });
                break;
            case 1:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getHoTen().compareTo(o1.getHoTen());
                    }
                });
                break;
            case 2:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getNgaySinh().compareTo(o1.getNgaySinh());
                    }
                });
                break;
            case 3:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getGioiTinh().compareTo(o1.getGioiTinh());
                    }
                });
                break;
            case 4:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getSoDienThoai().compareTo(o1.getSoDienThoai());
                    }
                });
                break;
            case 5:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getQueQuan().compareTo(o1.getQueQuan());
                    }
                });
                break;
            case 6:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getDanToc().compareTo(o1.getDanToc());
                    }
                });
                break;
            case 7:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getTrinhDoHocVan().compareTo(o1.getTrinhDoHocVan());
                    }
                });
                break;
            case 8:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getChuyenNganh().compareTo(o1.getChuyenNganh());
                    }
                });
                break;
            case 9:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getPhongBan().compareTo(o1.getPhongBan());
                    }
                });
                break;
            case 10:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o2.getChucVu().compareTo(o1.getChucVu());
                    }
                });
                break;
            default:
                break;
            }
            isort[index]=false;
        }
        else{
            switch(index){
            case 0:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getMaNS().compareTo(o2.getMaNS());
                    }
                });
                break;
            case 1:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getHoTen().compareTo(o2.getHoTen());
                    }
                });
                break;
            case 2:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getNgaySinh().compareTo(o2.getNgaySinh());
                    }
                });
                break;
            case 3:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getGioiTinh().compareTo(o2.getGioiTinh());
                    }
                });
                break;
            case 4:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getSoDienThoai().compareTo(o2.getSoDienThoai());
                    }
                });
                break;
            case 5:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getQueQuan().compareTo(o2.getQueQuan());
                    }
                });
                break;
            case 6:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getDanToc().compareTo(o2.getDanToc());
                    }
                });
                break;
            case 7:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getTrinhDoHocVan().compareTo(o2.getTrinhDoHocVan());
                    }
                });
                break;
            case 8:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getChuyenNganh().compareTo(o2.getChuyenNganh());
                    }
                });
                break;
            case 9:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getPhongBan().compareTo(o2.getPhongBan());
                    }
                });
                break;
            case 10:
                Collections.sort(dsHoSo, new Comparator<NhanSuHoSoModel>(){
                    @Override
                    public int compare(NhanSuHoSoModel o1, NhanSuHoSoModel o2) {
                       return o1.getChucVu().compareTo(o2.getChucVu());
                    }
                });
                break;
            default:
                break;
            }
            isort[index]=true;
        }
        reloadTable();
        table_ns.revalidate();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_export = new javax.swing.JButton();
        reload = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        txt_searchOption = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        txt_searchkey = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_ns = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1250, 650));
        setPreferredSize(new java.awt.Dimension(1250, 650));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_export.setBackground(new java.awt.Color(24, 98, 151));
        btn_export.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_export.setForeground(new java.awt.Color(255, 255, 255));
        btn_export.setText("Xuất EXCEL");
        btn_export.setBorder(null);
        btn_export.setBorderPainted(false);
        btn_export.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportActionPerformed(evt);
            }
        });
        add(btn_export, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 130, 50));

        reload.setBackground(new java.awt.Color(24, 98, 151));
        reload.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        reload.setForeground(new java.awt.Color(255, 255, 255));
        reload.setText("Tải lại");
        reload.setBorder(null);
        reload.setBorderPainted(false);
        reload.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadActionPerformed(evt);
            }
        });
        add(reload, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 100, 50));

        btn_delete.setBackground(new java.awt.Color(24, 98, 151));
        btn_delete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("Xóa");
        btn_delete.setBorder(null);
        btn_delete.setBorderPainted(false);
        btn_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 100, 50));

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
        add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, 100, 50));

        txt_searchOption.setBackground(new java.awt.Color(255, 204, 204));
        txt_searchOption.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_searchOption.setOpaque(false);
        add(txt_searchOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 180, 30));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 170, -1));

        txt_searchkey.setBackground(new java.awt.Color(255, 255, 255, 0));
        txt_searchkey.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_searchkey.setForeground(new java.awt.Color(255, 255, 255));
        txt_searchkey.setText("Nhập từ khoá để tìm...");
        txt_searchkey.setBorder(null);
        txt_searchkey.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_searchkey.setOpaque(false);
        txt_searchkey.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchkeyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_searchkeyFocusLost(evt);
            }
        });
        txt_searchkey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchkeyKeyReleased(evt);
            }
        });
        add(txt_searchkey, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 170, -1));

        table_ns.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        table_ns.setModel(new HoSoTableModel());
        table_ns.setFocusable(false);
        table_ns.setGridColor(new java.awt.Color(204, 204, 204));
        table_ns.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table_ns.setRowHeight(25);
        table_ns.setSelectionBackground(new java.awt.Color(255, 102, 102));
        table_ns.setShowVerticalLines(false);
        table_ns.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_ns);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1230, 540));

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/panelDefault.png"))); // NOI18N
        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 650));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        detailInforAdd z = new detailInforAdd(aa,bb,true,tenUser);
        //z.setAlwaysOnTop(true);
        z.setVisible(true);
    }//GEN-LAST:event_btn_addActionPerformed

    private void reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadActionPerformed
        txt_searchkey.setText("Nhập từ khoá...");
        txt_searchOption.setSelectedIndex(0);
        LoadData();
    }//GEN-LAST:event_reloadActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        try{
            int[] index = table_ns.getSelectedRows();
            if(JOptionPane.showConfirmDialog (null, "Hành động này sẽ xoá vĩnh viễn " + index.length + " hồ sơ","Bạn chắc chứ?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                int count=0;
                for(int a:index)
                {
                    if (new NhanSu().deleteNS((String) table_ns.getValueAt(a, 0)))
                        count++;
                }
                JOptionPane.showMessageDialog(this,"Đã xoá thành công "+count+" hồ sơ!");
                LoadData();
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Xoá hồ sơ thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void txt_searchkeyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchkeyFocusGained
        if(txt_searchkey.getText().equals("Nhập từ khoá để tìm..."))
            txt_searchkey.setText("");
    }//GEN-LAST:event_txt_searchkeyFocusGained

    private void txt_searchkeyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchkeyFocusLost
        if(txt_searchkey.getText().equals(""))
            txt_searchkey.setText("Nhập từ khoá để tìm...");
    }//GEN-LAST:event_txt_searchkeyFocusLost

    private void txt_searchkeyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchkeyKeyReleased
        if(!txt_searchkey.getText().equals("")){
            String key = txt_searchkey.getText().toLowerCase();
            int searchIndex = Integer.valueOf(((ComboItem)txt_searchOption.getSelectedItem()).getValue());
            ArrayList<NhanSuHoSoModel> dsHoSoz = new ArrayList<>();
            for(NhanSuHoSoModel z:dsHoSo)
                switch(searchIndex){
                    case 0:
                        if(z.getMaNS().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case 1:
                        if(z.getHoTen().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case 2:
                        if(z.getNgaySinh().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case 3:
                        if(z.getGioiTinh().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case 4:
                        if(z.getSoDienThoai().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case 5:
                        if(z.getQueQuan().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case 6:
                        if(z.getDanToc().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case 7:
                        if(z.getTrinhDoHocVan().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case  8:
                        if(z.getChuyenNganh().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case 9:
                        if(z.getPhongBan().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                    case 10:
                        if(z.getChucVu().toLowerCase().contains(key))
                            dsHoSoz.add(z);
                        break;
                }
            table_ns.setModel(new HoSoTableModel(dsHoSoz));
        }
        else{
            table_ns.setModel(new HoSoTableModel(dsHoSo));
        }
        table_ns.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table_ns.getColumnModel().getColumn(0).setPreferredWidth(60);
        table_ns.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_ns.getColumnModel().getColumn(2).setPreferredWidth(100);
        table_ns.getColumnModel().getColumn(3).setPreferredWidth(80);
        table_ns.getColumnModel().getColumn(4).setPreferredWidth(110);
        table_ns.getColumnModel().getColumn(5).setPreferredWidth(130);
        table_ns.getColumnModel().getColumn(6).setPreferredWidth(90);
        table_ns.getColumnModel().getColumn(7).setPreferredWidth(110);
        table_ns.getColumnModel().getColumn(8).setPreferredWidth(138);
        table_ns.getColumnModel().getColumn(9).setPreferredWidth(130);
        table_ns.getColumnModel().getColumn(10).setPreferredWidth(130);
        table_ns.revalidate();
    }//GEN-LAST:event_txt_searchkeyKeyReleased

    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
        try{
            JFileChooser chonChoLuu = new JFileChooser();
            //chonChoLuu.setCurrentDirectory(new File(System.getProperty("user.dir")));
            chonChoLuu.setSelectedFile(new File("unname.xlsx"));
            int reponse = chonChoLuu.showSaveDialog(null);
            if(reponse == JFileChooser.APPROVE_OPTION){
                String savePath = chonChoLuu.getSelectedFile().getAbsolutePath();
                if (chonChoLuu.getSelectedFile().getName().length()>5) 
                {
                    if(!savePath.substring(savePath.length() - 5).equals(".xlsx"))
                        savePath =  savePath + ".xlsx";
                }
                else{
                    savePath =  savePath + ".xlsx";
                }
                if(new File(savePath).exists()){
                    if(JOptionPane.showConfirmDialog (null, "Đã tồn tại file này, bạn có muốn ghi đè?","Bạn chắc chứ?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                        writeToExcell(table_ns, Paths.get(savePath));
                        JOptionPane.showMessageDialog(this, "Xuất file thành công!");
                    }
                }
                else{
                    writeToExcell(table_ns, Paths.get(savePath));
                    JOptionPane.showMessageDialog(this, "Xuất file thành công!");
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Xuất file thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_exportActionPerformed

    private void writeToExcell(JTable table, Path path) throws FileNotFoundException, IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(2);
        TableModel model = table.getModel();


        Row headerRow = sheet.createRow(0);
        for(int headings = 0; headings < model.getColumnCount(); headings++){
            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));
        }

        for(int rows = 0; rows < model.getRowCount(); rows++){
            for(int cols = 0; cols < table.getColumnCount(); cols++){
                row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString());
            }

            
            row = sheet.createRow((rows + 3)); 
        }
        wb.write(new FileOutputStream(path.toString()));   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_export;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton reload;
    private javax.swing.JTable table_ns;
    private javax.swing.JComboBox<ComboItem> txt_searchOption;
    private javax.swing.JTextField txt_searchkey;
    // End of variables declaration//GEN-END:variables
}
