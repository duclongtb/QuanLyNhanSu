package Mapper;

import Model.AccountModel;
import Model.PhongBanModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Iroha
 */
public class PhongBanMapper implements IMapper<PhongBanModel>{

    @Override
    public PhongBanModel mapRowToObject(ResultSet rs) {
        try{
            PhongBanModel pb = new PhongBanModel();
            pb.setMaPB(rs.getString("MaPB"));
            pb.setTenPB(rs.getString("TenPB"));
            pb.setSdt(rs.getString("SoDienThoaiPB"));
            return pb;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
