/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.implement.NhanSu;
import Model.CustomTable.BangLuongTableModel;
import Model.NhanSuModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

/**
 *
 * @author Iroha
 */
public class luongPanel extends javax.swing.JPanel {

    /**
     * Creates new form luongPanel
     */
    java.awt.Frame bb;
    public luongPanel(java.awt.Frame mainFrame) {
        initComponents();
        bb=mainFrame;
        prepare();
    }
    
    private void prepare(){
        table_bangluong.getTableHeader().setDefaultRenderer(new luongPanel.HeaderColor());
//        table_bangluong.getTableHeader().setBackground(new Color(82,147,255));
        table_bangluong.getTableHeader().setBackground(new Color(32, 136, 203));
        table_bangluong.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table_bangluong.getColumnModel().getColumn(0).setPreferredWidth(100);
        table_bangluong.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_bangluong.getColumnModel().getColumn(2).setPreferredWidth(110);
        table_bangluong.getColumnModel().getColumn(3).setPreferredWidth(158);
        table_bangluong.getColumnModel().getColumn(4).setPreferredWidth(130);
        table_bangluong.getColumnModel().getColumn(5).setPreferredWidth(100);
        table_bangluong.getColumnModel().getColumn(6).setPreferredWidth(150);
        table_bangluong.getColumnModel().getColumn(7).setPreferredWidth(90);
        table_bangluong.getColumnModel().getColumn(8).setPreferredWidth(90);
        table_bangluong.getColumnModel().getColumn(9).setPreferredWidth(130);
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
    
    public class InforLuong{
        private String mans, hoten, namcongtac, chucvu, luongcoban, hesoluong, thuongthamnien, phucap, baohiem, thuclinh;

        public InforLuong() {
        }

        public InforLuong(String mans, String hoten, String namcongtac, String chucvu, String luongcoban, String hesoluong, String thuongthamnien, String phucap, String baohiem, String thuclinh) {
            this.mans = mans;
            this.hoten = hoten;
            this.namcongtac = namcongtac;
            this.chucvu = chucvu;
            this.luongcoban = luongcoban;
            this.hesoluong = hesoluong;
            this.thuongthamnien = thuongthamnien;
            this.phucap = phucap;
            this.baohiem = baohiem;
            this.thuclinh = thuclinh;
        }

        public String getMans() {
            return mans;
        }

        public void setMans(String mans) {
            this.mans = mans;
        }

        public String getHoten() {
            return hoten;
        }

        public void setHoten(String hoten) {
            this.hoten = hoten;
        }

        public String getNamcongtac() {
            return namcongtac;
        }

        public void setNamcongtac(String namcongtac) {
            this.namcongtac = namcongtac;
        }

        public String getChucvu() {
            return chucvu;
        }

        public void setChucvu(String chucvu) {
            this.chucvu = chucvu;
        }

        public String getLuongcoban() {
            return luongcoban;
        }

        public void setLuongcoban(String luongcoban) {
            this.luongcoban = luongcoban;
        }

        public String getHesoluong() {
            return hesoluong;
        }

        public void setHesoluong(String hesoluong) {
            this.hesoluong = hesoluong;
        }

        public String getThuongthamnien() {
            return thuongthamnien;
        }

        public void setThuongthamnien(String thuongthamnien) {
            this.thuongthamnien = thuongthamnien;
        }

        public String getPhucap() {
            return phucap;
        }

        public void setPhucap(String phucap) {
            this.phucap = phucap;
        }

        public String getBaohiem() {
            return baohiem;
        }

        public void setBaohiem(String baohiem) {
            this.baohiem = baohiem;
        }

        public String getThuclinh() {
            return thuclinh;
        }

        public void setThuclinh(String thuclinh) {
            this.thuclinh = thuclinh;
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

        btn_print = new javax.swing.JButton();
        btn_calculate = new javax.swing.JButton();
        btn_setting = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_bangluong = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 140, 50));

        btn_calculate.setBackground(new java.awt.Color(24, 98, 151));
        btn_calculate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_calculate.setForeground(new java.awt.Color(255, 255, 255));
        btn_calculate.setText("Tính lương");
        btn_calculate.setBorder(null);
        btn_calculate.setBorderPainted(false);
        btn_calculate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calculateActionPerformed(evt);
            }
        });
        add(btn_calculate, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 140, 50));

        btn_setting.setBackground(new java.awt.Color(24, 98, 151));
        btn_setting.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_setting.setForeground(new java.awt.Color(255, 255, 255));
        btn_setting.setText("Chỉnh sửa thông số tính lương");
        btn_setting.setBorder(null);
        btn_setting.setBorderPainted(false);
        btn_setting.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_setting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_settingActionPerformed(evt);
            }
        });
        add(btn_setting, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 310, 50));

        table_bangluong.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        table_bangluong.setModel(new BangLuongTableModel());
        table_bangluong.setFocusable(false);
        table_bangluong.setGridColor(new java.awt.Color(204, 204, 204));
        table_bangluong.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table_bangluong.setRowHeight(25);
        table_bangluong.setSelectionBackground(new java.awt.Color(255, 102, 102));
        table_bangluong.setShowVerticalLines(false);
        table_bangluong.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_bangluong);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1230, 540));

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/panelDefault.png"))); // NOI18N
        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 650));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_settingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_settingActionPerformed
        salaryCalculatorEdit editluong = new salaryCalculatorEdit(bb, true);
        editluong.setVisible(true);
    }//GEN-LAST:event_btn_settingActionPerformed

    private void btn_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calculateActionPerformed
        try{
            if(!new File(System.getProperty("user.dir") + "\\settings.json").exists()){
            throw new Exception("Vui lòng chỉnh sửa thống số tính lương trước!");
            }
            LoadLuongTable();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi\n"+e.getMessage());
        }
    }//GEN-LAST:event_btn_calculateActionPerformed

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
                        List<InforLuong> ls = new ArrayList<>();
                        for(int i=0; i<table_bangluong.getRowCount(); i++){
                            InforLuong a = new InforLuong();
                            a.setMans(table_bangluong.getValueAt(i, 0).toString());
                            a.setHoten(table_bangluong.getValueAt(i, 1).toString());
                            a.setNamcongtac(table_bangluong.getValueAt(i, 2).toString());
                            a.setChucvu(table_bangluong.getValueAt(i, 3).toString());
                            a.setLuongcoban(table_bangluong.getValueAt(i, 4).toString());
                            a.setHesoluong(table_bangluong.getValueAt(i, 5).toString());
                            a.setThuongthamnien(table_bangluong.getValueAt(i, 6).toString());
                            a.setPhucap(table_bangluong.getValueAt(i, 7).toString());
                            a.setBaohiem(table_bangluong.getValueAt(i, 8).toString());
                            a.setThuclinh(table_bangluong.getValueAt(i, 9).toString());
                            ls.add(a);
                        }
                        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(ls);
                        Map<String, Object> parameters = new HashMap<String, Object>();
                        parameters.put("DataSource", itemsJRBean);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(System.getProperty("user.dir") + "\\ReportTemplates\\bangluong.jasper", parameters, new JREmptyDataSource());
            //            OutputStream outputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "\\ReportTemplates\\test.pdf"));
            //            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                        JRDocxExporter exporter = new JRDocxExporter();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, savePath);
                        exporter.exportReport();
                        JOptionPane.showMessageDialog(this, "Xuất file thành công!");
                    }
                }
                else{
                    List<InforLuong> ls = new ArrayList<>();
                    for(int i=0; i<table_bangluong.getRowCount(); i++){
                        InforLuong a = new InforLuong();
                        a.setMans(table_bangluong.getValueAt(i, 0).toString());
                        a.setHoten(table_bangluong.getValueAt(i, 1).toString());
                        a.setNamcongtac(table_bangluong.getValueAt(i, 2).toString());
                        a.setChucvu(table_bangluong.getValueAt(i, 3).toString());
                        a.setLuongcoban(table_bangluong.getValueAt(i, 4).toString());
                        a.setHesoluong(table_bangluong.getValueAt(i, 5).toString());
                        a.setThuongthamnien(table_bangluong.getValueAt(i, 6).toString());
                        a.setPhucap(table_bangluong.getValueAt(i, 7).toString());
                        a.setBaohiem(table_bangluong.getValueAt(i, 8).toString());
                        a.setThuclinh(table_bangluong.getValueAt(i, 9).toString());
                        ls.add(a);
                    }
                    JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(ls);
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("DataSource", itemsJRBean);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(System.getProperty("user.dir") + "\\ReportTemplates\\bangluong.jasper", parameters, new JREmptyDataSource());
        //            OutputStream outputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "\\ReportTemplates\\test.pdf"));
        //            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                    JRDocxExporter exporter = new JRDocxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, savePath);
                    exporter.exportReport();
                    JOptionPane.showMessageDialog(this, "Xuất file thành công!");
                }
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Xuất file thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_printActionPerformed
    
    private void LoadLuongTable(){
        List<NhanSuModel> ls = new NhanSu().getListNhanSu();
        table_bangluong.setModel(new BangLuongTableModel(ls));
        table_bangluong.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table_bangluong.getColumnModel().getColumn(0).setPreferredWidth(100);
        table_bangluong.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_bangluong.getColumnModel().getColumn(2).setPreferredWidth(110);
        table_bangluong.getColumnModel().getColumn(3).setPreferredWidth(158);
        table_bangluong.getColumnModel().getColumn(4).setPreferredWidth(130);
        table_bangluong.getColumnModel().getColumn(5).setPreferredWidth(100);
        table_bangluong.getColumnModel().getColumn(6).setPreferredWidth(150);
        table_bangluong.getColumnModel().getColumn(7).setPreferredWidth(90);
        table_bangluong.getColumnModel().getColumn(8).setPreferredWidth(90);
        table_bangluong.getColumnModel().getColumn(9).setPreferredWidth(130);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btn_calculate;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_setting;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_bangluong;
    // End of variables declaration//GEN-END:variables
}
