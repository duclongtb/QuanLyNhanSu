package Mapper;

import Model.NhanSuModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Iroha
 */
public class NhanSuMapper implements IMapper<NhanSuModel>{

    @Override
    public NhanSuModel mapRowToObject(ResultSet rs) {
        try{
            NhanSuModel ns = new NhanSuModel();
            ns.setMaNS(rs.getString("MaNS"));
            ns.setHoTen(rs.getString("HoTen"));
            ns.setNgaySinh(rs.getString("NgaySinh"));
            ns.setQueQuan(rs.getString("QueQuan"));
            ns.setGioiTinh(rs.getBoolean("GioiTinh"));
            ns.setDanToc(rs.getString("DanToc"));
            ns.setSoDienThoai(rs.getString("SoDienThoai"));
            ns.setTrinhDoHocVan(rs.getString("TrinhDoHocVan"));
            ns.setChuyenNganh(rs.getString("ChuyenNganh"));
            ns.setMaPB(rs.getString("MaPB"));
            ns.setMaCV(rs.getString("MaCV"));
            ns.setChinhTri(rs.getString("ChinhTri"));
            ns.setDoanThe(rs.getString("DoanThe"));
            ns.setAnh(rs.getBytes("Anh"));
            ns.setNgayThamGia(rs.getString("NgayGiaNhap"));
            ns.setThayDoiCuoi(rs.getString("SuaCuoi"));
            ns.setCanCuoc(rs.getString("CanCuoc"));
            ns.setCongChuc(rs.getBoolean("LoaiCongChuc"));
            ns.setHanHopDong(rs.getString("HanHopDong"));
            return ns;
        } catch (SQLException ex) {
            return null;
        }
    }
}
