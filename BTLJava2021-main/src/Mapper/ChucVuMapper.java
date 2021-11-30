package Mapper;

import Model.ChucVuModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Iroha
 */
public class ChucVuMapper implements IMapper<ChucVuModel>{

    @Override
    public ChucVuModel mapRowToObject(ResultSet rs) {
        try{
            ChucVuModel cv = new ChucVuModel();
            cv.setMaCV(rs.getString("MaCV"));
            cv.setTenCV(rs.getString("TenCV"));
            cv.setPhuCap(rs.getDouble("PhuCap"));
            cv.setBacLuong(rs.getInt("BacLuong"));
            return cv;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
