package DAO;

import Model.ChucVuModel;
import java.util.List;

/**
 *
 * @author Iroha
 */
public interface IChucVu {
    public String TimTenChucVu(String MaCV);
    public double TimPhuCapChucVu(String MaCV);
    public int TimBacLuongChucVu(String MaCV);
    public boolean TimChucVuTheoBacLuong(int BacLuong);
    public List<ChucVuModel> getChucVu();
    public boolean xoaCV(String MaCV);
    public boolean suaCV(String maCV, String tenCV, double phuCap, int BacLuong);
    public boolean themCV(String maCV, String tenCV, double phuCap, int BacLuong);
}
