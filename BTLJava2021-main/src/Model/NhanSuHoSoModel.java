package Model;

import DAO.implement.ChucVu;
import DAO.implement.PhongBan;

/**
 *
 * @author Iroha
 */
public class NhanSuHoSoModel {
    private String maNS, hoTen, ngaySinh, queQuan, danToc, soDienThoai, trinhDoHocVan, ChuyenNganh, PhongBan, ChucVu, gioiTinh;

    public NhanSuHoSoModel() {
    }
    
    public NhanSuHoSoModel(NhanSuModel a) {
        this.maNS = a.getMaNS();
        this.hoTen = a.getHoTen()==null?"":a.getHoTen();
        this.ngaySinh = a.getNgaySinh()==null?"":a.getNgaySinh();
        this.gioiTinh = a.isGioiTinh()?"Nam":"Nữ";
        this.queQuan = a.getQueQuan()==null?"":a.getQueQuan();
        this.danToc = a.getDanToc()==null?"":a.getDanToc();
        this.soDienThoai = a.getSoDienThoai()==null?"":a.getSoDienThoai();
        this.trinhDoHocVan = a.getTrinhDoHocVan()==null?"":a.getTrinhDoHocVan();
        this.ChuyenNganh = a.getChuyenNganh()==null?"":a.getHoTen();
        String pb = new PhongBan().TimTenPB(a.getMaPB());
        String cv = new ChucVu().TimTenChucVu(a.getMaCV());
        this.PhongBan = pb==null?"Không có":pb;
        this.ChucVu = cv==null?"Không có":cv;
    }

    public String getMaNS() {
        return maNS;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getTrinhDoHocVan() {
        return trinhDoHocVan;
    }

    public String getChuyenNganh() {
        return ChuyenNganh;
    }

    public String getPhongBan() {
        return PhongBan;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setMaNS(String maNS) {
        this.maNS = maNS;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setTrinhDoHocVan(String trinhDoHocVan) {
        this.trinhDoHocVan = trinhDoHocVan;
    }

    public void setChuyenNganh(String ChuyenNganh) {
        this.ChuyenNganh = ChuyenNganh;
    }

    public void setPhongBan(String PhongBan) {
        this.PhongBan = PhongBan;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
}
