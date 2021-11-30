package Mapper;

import Model.BacLuongModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Iroha
 */
public class BacLuongMapper implements IMapper<BacLuongModel>{

    @Override
    public BacLuongModel mapRowToObject(ResultSet rs) {
        try{
            BacLuongModel bl = new BacLuongModel();
            bl.setBacLuong(rs.getInt("BacLuong"));
            bl.setHeSoLuong(rs.getDouble("HeSoLuong"));
            return bl;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
