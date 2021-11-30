/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CustomTable;

import DAO.implement.BacLuong;
import DAO.implement.ChucVu;
import Model.BangLuongModel;
import Model.NhanSuModel;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Iroha
 */
public class BangLuongTableModel extends AbstractTableModel{
    private String name[] = {"Mã nhân sự", "Họ tên", "Năm công tác", "Chức vụ", "Lương cơ bản", "Hệ số lương", "Thưởng thâm niên", "Phụ cấp", "Bảo hiểm", "Thực lĩnh"};
    private Class classes[] = {String.class, String.class, String.class, String.class, String.class, String.class, String.class,String.class, String.class, String.class};
    ArrayList<NhanSuModel> ds = new ArrayList<>();
    ArrayList<BangLuongModel> dsBacLuong = new ArrayList<>();
    int luongcoban, baohiem;
    double thuongthamnien, thuongthamnienthem, namthamnien;
    int rowCount=0;
    NumberFormat nf = NumberFormat.getInstance(Locale.US);

    public BangLuongTableModel() {
    }
    
    public BangLuongTableModel(List<NhanSuModel> ls) {
        getSetting();
        ds = new ArrayList<>(ls);
        prepare();
    }
    
    private void prepare(){
        for(NhanSuModel s:ds){
            BangLuongModel a = new BangLuongModel();
            a.setMaNS(s.getMaNS());
            a.setHoTen(s.getHoTen());
            a.setNamCongTac(TinhNamCongTac(s.getNgayThamGia()));
            a.setChucVu(new ChucVu().TimTenChucVu(s.getMaCV()));
            if(a.getChucVu()!=null){
                rowCount++;
                a.setHeSoLuong(new BacLuong().getHeSoLuong(new ChucVu().TimBacLuongChucVu(s.getMaCV())));
                a.setThuongThamNien(TinhLuongThamNien(a.getNamCongTac(), a.getHeSoLuong()));
                a.setPhuCap(new ChucVu().TimPhuCapChucVu(s.getMaCV()));
                a.setThucLinh(luongcoban*a.getHeSoLuong()+a.getThuongThamNien()+a.getPhuCap()-baohiem);
                dsBacLuong.add(a);
            }
        }
    }
    
    private void getSetting(){
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(System.getProperty("user.dir") + "\\settings.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            luongcoban = Integer.valueOf(String.valueOf(jsonObject.get("LuongCoBan")));
            namthamnien = Double.valueOf(String.valueOf(jsonObject.get("NamThamNien")));
            thuongthamnien = Double.valueOf(String.valueOf(jsonObject.get("ThuongThamNien")));
            thuongthamnienthem = Double.valueOf(String.valueOf(jsonObject.get("ThuongThemThamNien")));
            baohiem = Integer.valueOf(String.valueOf(jsonObject.get("BaoHiem")));
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(BangLuongTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return classes.length;
    }
    
    private double TinhNamCongTac(String ngay){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToday = new Date();
        LocalDate d1 = LocalDate.parse(ngay, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse((String)formatter.format(dateToday), DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        return Double.valueOf(diffDays/365);
    }
    
    private double TinhLuongThamNien(double ngay, double hsl){
        double thuong = 0;
        int nam = (int)(ngay - namthamnien);
        if(nam>0){
            thuong += (luongcoban*hsl)*((thuongthamnien+nam*thuongthamnienthem)/100);
        }
        return thuong;
            
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return dsBacLuong.get(rowIndex).getMaNS();
            case 1: return dsBacLuong.get(rowIndex).getHoTen();
            case 2: return String.valueOf(nf.format(dsBacLuong.get(rowIndex).getNamCongTac()));
            case 3: return dsBacLuong.get(rowIndex).getChucVu();
            case 4: return String.valueOf(nf.format(luongcoban));
            case 5: return String.valueOf(nf.format(dsBacLuong.get(rowIndex).getHeSoLuong()));
            case 6: return String.valueOf(nf.format(dsBacLuong.get(rowIndex).getThuongThamNien()));
            case 7: return String.valueOf(nf.format(dsBacLuong.get(rowIndex).getPhuCap()));
            case 8: return String.valueOf(nf.format(baohiem));
            case 9: return String.valueOf(nf.format(dsBacLuong.get(rowIndex).getThucLinh()));
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return name[column];
    }
    
}
