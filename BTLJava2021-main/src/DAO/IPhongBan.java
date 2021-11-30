package DAO;

import Model.NhanSuPBCVModel;
import Model.PhongBanModel;
import java.util.List;

/**
 *
 * @author Iroha
 */
public interface IPhongBan {
    public String TimTenPB(String maPB);
    public List<PhongBanModel> getPhongBan();
    public List<NhanSuPBCVModel> getListNS();
    public boolean themPB(String maPB, String tenPB, String sdt);
    public boolean xoaPB(String maPB);
    public boolean suaPB(String maPB, String tenPB, String sdt);
}
