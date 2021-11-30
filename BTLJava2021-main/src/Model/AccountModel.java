package Model;

/**
 *
 * @author Iroha
 */
public class AccountModel {
    private int accountID;
    private String username;
    private String password;
    private String ten;
    private byte[] anh;

    public AccountModel() {
    }

    public AccountModel(int accountID, String username, String password, String ten, byte[] anh) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.ten = ten;
        this.anh = anh;
    }

    public AccountModel(AccountModel tk) {
        this.accountID = tk.accountID;
        this.username = tk.username;
        this.password = tk.password;
        this.ten = tk.ten;
        this.anh = tk.anh;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
    
}
