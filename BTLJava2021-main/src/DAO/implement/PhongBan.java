package DAO.implement;

import DAO.IPhongBan;
import Mapper.NhanSuPBCVMapper;
import Mapper.PhongBanMapper;
import Model.NhanSuPBCVModel;
import Model.PhongBanModel;
import java.util.List;

/**
 *
 * @author Iroha
 */
public class PhongBan extends DataSource<PhongBanModel> implements IPhongBan{

    @Override
    public String TimTenPB(String maPB) {
        String sql = "Select * from PhongBan WHERE MaPB = ?";
        List<PhongBanModel> result = excute(sql, new PhongBanMapper(), maPB);
        return result.isEmpty() ? null : result.get(0).getTenPB();
    }

    @Override
    public List<PhongBanModel> getPhongBan() {
        String sql = "Select * from PhongBan";
        return excute(sql, new PhongBanMapper());
    }

    @Override
    public List<NhanSuPBCVModel> getListNS() {
        String sql = "Select MaNS, HoTen, MaPB, MaCV from NhanSu";
        return excute(sql, new NhanSuPBCVMapper());
    }

    @Override
    public boolean themPB(String maPB, String tenPB, String sdt) {
        String sql = "INSERT INTO PhongBan VALUES (?,?,?)";
        return insert(sql, maPB, tenPB, sdt);
    }

    @Override
    public boolean xoaPB(String maPB) {
        String sql = "Delete from PhongBan WHERE MaPB = ?";
        return update(sql, maPB);
    }

    @Override
    public boolean suaPB(String maPB, String tenPB, String sdt) {
        String sql = "Update PhongBan SET TenPB=?, SoDienThoaiPB=? WHERE MaPB=?";
        return update(sql, tenPB, sdt, maPB);
    }
    
}
