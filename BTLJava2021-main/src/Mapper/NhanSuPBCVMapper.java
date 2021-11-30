/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapper;

import Model.NhanSuPBCVModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Iroha
 */
public class NhanSuPBCVMapper implements IMapper<NhanSuPBCVModel>{

    @Override
    public NhanSuPBCVModel mapRowToObject(ResultSet rs) {
        try{
            NhanSuPBCVModel ns = new NhanSuPBCVModel();
            ns.setMaNS(rs.getString("MaNS"));
            ns.setTenNS(rs.getString("HoTen"));
            ns.setMaPB(rs.getString("MaPB"));
            ns.setMaCV(rs.getString("MaCV"));
            return ns;
        } catch (SQLException ex) {
            return null;
        }
    }
    
}
