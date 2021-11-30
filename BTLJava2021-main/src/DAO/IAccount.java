package DAO;

import Model.AccountModel;
import java.util.List;

/**
 *
 * @author Iroha
 */
public interface IAccount {
    public AccountModel loginAccount(String username, String password);
    public boolean createAccount(String username, String password, String name, byte[] anh);
    public boolean checkAccount(String username);
    public boolean updateName(String username, String name);
    public boolean updateAvatar(String username, byte[] anh);
    public boolean updatePassword(String username, String password);
    public boolean updateAccount(String username, String name, byte[] anh);
    public byte[] getAvatar(String username);
    public boolean deleteAccount(String username);
    public List<AccountModel> getAccount();
}
