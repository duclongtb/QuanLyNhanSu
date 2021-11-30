package Model;

/**
 *
 * @author Iroha
 */
public class PhongBanModel {
    private String maPB,tenPB,sdt;

    public PhongBanModel() {
    }

    public PhongBanModel(String maPB, String tenPB, String sdt) {
        this.maPB = maPB;
        this.tenPB = tenPB;
        this.sdt = sdt;
    }

    public String getMaPB() {
        return maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
}
