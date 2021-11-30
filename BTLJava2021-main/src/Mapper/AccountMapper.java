/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapper;

import Model.AccountModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Iroha
 */
public class AccountMapper implements IMapper<AccountModel>{

    @Override
    public AccountModel mapRowToObject(ResultSet rs) {
        try{
            AccountModel acc = new AccountModel();
            acc.setUsername(rs.getString("TenTaiKhoan"));
            acc.setPassword(rs.getString("MatKhau"));
            acc.setTen(rs.getString("Ten"));
            acc.setAnh(rs.getBytes("Anh"));
            return acc;
        } catch (SQLException ex) {
            return null;
        }
    }
}
