package DAO.implement;

import DAO.IChucVu;
import Mapper.ChucVuMapper;
import Model.ChucVuModel;
import java.util.List;

/**
 *
 * @author Iroha
 */
public class ChucVu extends DataSource<ChucVuModel> implements IChucVu{

    @Override
    public String TimTenChucVu(String MaCV) {
        String sql = "Select * from ChucVu WHERE MaCV = ?";
        List<ChucVuModel> result = excute(sql, new ChucVuMapper(), MaCV);
        return result.isEmpty() ? null : result.get(0).getTenCV();
    }

    @Override
    public List<ChucVuModel> getChucVu() {
        String sql = "Select * from ChucVu";
        return excute(sql, new ChucVuMapper());
    }

    @Override
    public boolean xoaCV(String MaCV) {
        String sql = "Delete from ChucVu WHERE MaCV = ?";
        return update(sql, MaCV);
    }

    @Override
    public boolean suaCV(String maCV, String tenCV, double phuCap, int BacLuong) {
        String sql = "Update ChucVu SET TenCV = ?, PhuCap=?, BacLuong =? WHERE MaCV=?";
        return update(sql, tenCV, phuCap, BacLuong, maCV);
    }

    @Override
    public boolean themCV(String maCV, String tenCV, double phuCap, int BacLuong) {
        String sql = "Insert into ChucVu VALUES (?,?,?,?)";
        return update(sql, maCV, tenCV, phuCap, BacLuong);
    }

    @Override
    public double TimPhuCapChucVu(String MaCV) {
        String sql = "Select * from ChucVu WHERE MaCV = ?";
        List<ChucVuModel> result = excute(sql, new ChucVuMapper(), MaCV);
        return result.isEmpty() ? null : result.get(0).getPhuCap();
    }

    @Override
    public int TimBacLuongChucVu(String MaCV) {
        String sql = "Select * from ChucVu WHERE MaCV = ?";
        List<ChucVuModel> result = excute(sql, new ChucVuMapper(), MaCV);
        return result.isEmpty() ? null : result.get(0).getBacLuong();
    }

    @Override
    public boolean TimChucVuTheoBacLuong(int BacLuong) {
        String sql = "Select * from ChucVu WHERE BacLuong = ?";
        List<ChucVuModel> result = excute(sql, new ChucVuMapper(), BacLuong);
        return result.isEmpty() ? false : true;
    }
    
}
