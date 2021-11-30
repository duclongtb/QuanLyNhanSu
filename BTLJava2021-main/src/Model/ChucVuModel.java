package Model;

/**
 *
 * @author Iroha
 */
public class ChucVuModel {
    private String MaCV, tenCV;
    private double PhuCap;
    private int BacLuong;

    public ChucVuModel() {
    }

    public ChucVuModel(String MaCV, String tenCV, double PhuCap, int BacLuong) {
        this.MaCV = MaCV;
        this.tenCV = tenCV;
        this.PhuCap = PhuCap;
        this.BacLuong = BacLuong;
    }

    public double getPhuCap() {
        return PhuCap;
    }

    public void setPhuCap(double PhuCap) {
        this.PhuCap = PhuCap;
    }

    public String getMaCV() {
        return MaCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setMaCV(String MaCV) {
        this.MaCV = MaCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public int getBacLuong() {
        return BacLuong;
    }

    public void setBacLuong(int BacLuong) {
        this.BacLuong = BacLuong;
    }
    
    
}
