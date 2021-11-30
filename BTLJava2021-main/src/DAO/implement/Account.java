package DAO.implement;

import DAO.IAccount;
import Mapper.AccountMapper;
import Model.AccountModel;
import java.util.List;

/**
 *
 * @author Iroha
 */
public class Account extends DataSource<AccountModel> implements IAccount{

    @Override
    public AccountModel loginAccount(String username, String password) {
        String sql = "Select * from TaiKhoan where TenTaiKhoan = ? AND MatKhau = ?";
        List<AccountModel> result = excute(sql, new AccountMapper(), username, password);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean createAccount(String username, String password, String name, byte[] anh) {
        String sql = "insert into TaiKhoan values (?,?,?,?)";
        return insert(sql, username, password, name, anh);
    }

    @Override
    public boolean updateAvatar(String username, byte[] anh) {
        String sql = "Update TaiKhoan SET Anh = ? WHERE TenTaiKhoan = ?";
        return update(sql, anh, username);
    }

    @Override
    public boolean updatePassword(String username, String password) {
        String sql = "Update TaiKhoan SET MatKhau = ? WHERE TenTaiKhoan = ?";
        return update(sql, password, username);
    }

    @Override
    public List<AccountModel> getAccount() {
        String sql = "Select * from TaiKhoan";
        return excute(sql, new AccountMapper());
    }

    @Override
    public byte[] getAvatar(String username) {
        String sql = "Select * from TaiKhoan WHERE TenTaiKhoan = ?";
        List<AccountModel> result = excute(sql, new AccountMapper(), username);
        return result.isEmpty() ? null : result.get(0).getAnh();
    }

    @Override
    public boolean updateName(String username, String name) {
        String sql = "Update TaiKhoan SET Ten = ? WHERE TenTaiKhoan = ?";
        return update(sql, name, username);
    }

    @Override
    public boolean checkAccount(String username) {
        String sql = "Select * from TaiKhoan where TenTaiKhoan = ? ";
        List<AccountModel> result = excute(sql, new AccountMapper(), username);
        return result.isEmpty() ? false:true;
    }

    @Override
    public boolean updateAccount(String username, String name, byte[] anh) {
        String sql = "Update TaiKhoan SET Ten=?, Anh=? WHERE TenTaiKhoan=?";
        return update(sql, name, anh, username);
    }

    @Override
    public boolean deleteAccount(String username) {
        String sql = "Delete from TaiKhoan Where TenTaiKhoan=?";
        return update(sql, username);
    }
}
