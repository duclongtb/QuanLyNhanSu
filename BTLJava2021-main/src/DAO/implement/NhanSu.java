package DAO.implement;

import DAO.INhanSu;
import Mapper.NhanSuMapper;
import Mapper.NhanSuMapperNoAvatar;
import Model.NhanSuModel;
import java.util.List;

/**
 *
 * @author Iroha
 */
public class NhanSu extends DataSource<NhanSuModel> implements INhanSu{

    @Override
    public List<NhanSuModel> getListNhanSu() {
        String sql = "Select MaNS, HoTen, NgaySinh, QueQuan, GioiTinh, DanToc, SoDienThoai, TrinhDoHocVan, ChuyenNganh, MaPB, MaCV, ChinhTri, DoanThe, NgayGiaNhap, SuaCuoi, CanCuoc, LoaiCongChuc, HanHopDong from NhanSu";
        return excute(sql, new NhanSuMapperNoAvatar());
    }

    @Override
    public boolean updateNS(String maNS, String hoTen, String ngaySinh, String queQuan, String danToc, String soDienThoai, String trinhDoHocVan, String ChuyenNganh, String maPB, String maCV, String chinhTri, String doanThe, boolean gioiTinh, byte[] anh, String thayDoiCuoi, String canCuoc, boolean congChuc, String hanHopDong) {
        String sql = "UPDATE NhanSu SET HoTen=?, NgaySinh=?, QueQuan=?, GioiTinh=?, DanToc=?, SoDienThoai=?, TrinhDoHocVan=?, ChuyenNganh=?, MaPB=?, MaCV = ?, ChinhTri=?, DoanThe=?, CanCuoc=?, Anh=?, SuaCuoi=?, LoaiCongChuc=?, HanHopDong=? WHERE MaNS=?";
        return update(sql, hoTen, ngaySinh, queQuan, gioiTinh, danToc, soDienThoai, trinhDoHocVan, ChuyenNganh, maPB, maCV, chinhTri, doanThe, canCuoc, anh, thayDoiCuoi, congChuc, hanHopDong, maNS);
    }

    @Override
    public NhanSuModel getNhanSu(String MaNS) {
        String sql = "Select * from NhanSu WHERE MaNS=?";
        List<NhanSuModel> result = excute(sql, new NhanSuMapper(), MaNS);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean updateAVT(String MaNS, byte[] anh) {
        String sql = "Update NhanSu SET Anh=? WHERE MaNS=?";
        return update(sql, anh, MaNS);
    }

    @Override
    public boolean addNS(String NgayThamGia, String maNS, String hoTen, String ngaySinh, String queQuan, String danToc, String soDienThoai, String trinhDoHocVan, String ChuyenNganh, String maPB, String maCV, String chinhTri, String doanThe, boolean gioiTinh, byte[] anh, String thayDoiCuoi, String canCuoc, boolean congChuc, String hanHopDong) {
        String sql = "INSERT INTO NhanSu VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return insert(sql, maNS, hoTen, ngaySinh, queQuan, gioiTinh, danToc, soDienThoai, trinhDoHocVan, ChuyenNganh, maPB, maCV, chinhTri, doanThe, anh, NgayThamGia, thayDoiCuoi, canCuoc, congChuc, hanHopDong);
    }

    @Override
    public boolean deleteNS(String MaNS) {
        String sql = "Delete from NhanSu Where MaNS=?";
        return update(sql, MaNS);
    }

    @Override
    public boolean updatePhongBan(String MaNS, String MaPB, String ThayDoiCuoi) {
        String sql = "UPDATE NhanSu SET MaPB=?, SuaCuoi=? WHERE MaNS=?";
        return update(sql, MaPB, ThayDoiCuoi, MaNS);
    }

    @Override
    public boolean updateChucVu(String MaNS, String MaCV, String ThayDoiCuoi) {
        String sql = "UPDATE NhanSu SET MaCV=?, SuaCuoi=? WHERE MaNS=?";
        return update(sql, MaCV, ThayDoiCuoi, MaNS);
    }
    
}
