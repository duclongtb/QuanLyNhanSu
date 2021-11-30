package Model;

/**
 *
 * @author Iroha
 */
public class NhanSuModel {
    private String maNS, hoTen, ngaySinh, queQuan, danToc, soDienThoai, trinhDoHocVan, ChuyenNganh, maPB, maCV, chinhTri, doanThe, NgayThamGia, ThayDoiCuoi, canCuoc, hanHopDong;
    private boolean gioiTinh, congChuc;
    private byte[] anh;

    public NhanSuModel() {
    }

    public NhanSuModel(String NgayThamGia, String maNS, String hoTen, String ngaySinh, String queQuan, String danToc, String soDienThoai, String trinhDoHocVan, String ChuyenNganh, String maPB, String maCV, String chinhTri, String doanThe, boolean gioiTinh, byte[] anh, String ThayDoiCuoi, String canCuoc, boolean congChuc, String hanHopDong) {
        this.congChuc = congChuc;
        this.NgayThamGia = NgayThamGia;
        this.maNS = maNS;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.queQuan = queQuan;
        this.danToc = danToc;
        this.soDienThoai = soDienThoai;
        this.trinhDoHocVan = trinhDoHocVan;
        this.ChuyenNganh = ChuyenNganh;
        this.maPB = maPB;
        this.maCV = maCV;
        this.chinhTri = chinhTri;
        this.doanThe = doanThe;
        this.gioiTinh = gioiTinh;
        this.anh = anh;
        this.ThayDoiCuoi = ThayDoiCuoi;
        this.canCuoc = canCuoc;
        this.hanHopDong = hanHopDong;
    }

    public String getNgayThamGia() {
        return NgayThamGia;
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

    public String getMaPB() {
        return maPB;
    }

    public String getMaCV() {
        return maCV;
    }

    public String getChinhTri() {
        return chinhTri;
    }

    public String getDoanThe() {
        return doanThe;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setNgayThamGia(String NgayThamGia) {
        this.NgayThamGia = NgayThamGia;
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

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public void setChinhTri(String chinhTri) {
        this.chinhTri = chinhTri;
    }

    public void setDoanThe(String doanThe) {
        this.doanThe = doanThe;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public String getThayDoiCuoi() {
        return ThayDoiCuoi;
    }

    public void setThayDoiCuoi(String ThayDoiCuoi) {
        this.ThayDoiCuoi = ThayDoiCuoi;
    }

    public String getCanCuoc() {
        return canCuoc;
    }

    public boolean isCongChuc() {
        return congChuc;
    }

    public void setCanCuoc(String canCuoc) {
        this.canCuoc = canCuoc;
    }

    public void setCongChuc(boolean congChuc) {
        this.congChuc = congChuc;
    }

    public void setHanHopDong(String hanHopDong) {
        this.hanHopDong = hanHopDong;
    }

    public String getHanHopDong() {
        return hanHopDong;
    }
    
}
